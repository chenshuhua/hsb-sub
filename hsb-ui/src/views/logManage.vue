<template>
  <div class="logManage">
    <div class="timeLine">
      <el-timeline>
        <el-timeline-item
          v-for="(log, index) in logList"
          :key="index"
          :hollow="log.hollow"
          :type="log.type"
          :timestamp="log.timestamp"
        >
          <span class="timeline-item" @click="activeTimelineItem(log)">{{
            log.title
          }}</span>
        </el-timeline-item>
      </el-timeline>
    </div>
    <div class="edit-panel">
      <el-row>
        <span class="edit-item-title">版本号：</span>
        <el-col :span="10"
          ><el-input
            v-model="logInfo.programVersion"
            placeholder="输入版本号"
            :disabled="isEditMode"
            :suffix-icon="Calendar"
            @blur="checkVersion"
        /></el-col>
        <span
          style="line-height: 32px;
            padding-left: 10px;
            font-size: 13px;
            letter-spacing: 2px;
            color: red;"
          v-show="showError"
          >版本号已经存在！！！</span
        >
      </el-row>
      <el-row>
        <span class="edit-item-title">程序包：</span>
        <el-col :span="10"
          ><el-upload
            class="edit-item-upload"
            drag
            action
            ref="uploadProgram"
            :http-request="doUploadFile"
            multiple="false"
            accept=".zip, .rar"
            :on-exceed="handleExceed"
            :on-remove="onRemoveFile"
            :before-remove="beforeRemoveFile"
            v-model:file-list="programFiles"
            :auto-upload="true"
            :disabled="
              logInfo.programVersion === undefined ||
              logInfo.programVersion === ''
            "
            limit="1"
          >
            <div class="el-upload__text">
              {{
                logInfo.programVersion === undefined ||
                logInfo.programVersion === ""
                  ? "请先输入版本号"
                  : "拖动文件到这里 或者 点击上传"
              }}
            </div>
            <el-progress
              stroke-linecap="square"
              :percentage="this.logInfo.uploadPercentage"
              v-show="this.showProgress"
            />
          </el-upload>
        </el-col>
        <el-col :push="8" :span="2">
          <el-button
            type="danger"
            style="width: 100%; max-width: 108px;"
            @click="cancelEdit"
            v-show="isEditMode"
            >取消</el-button
          >
        </el-col>
        <el-col :push="8" :span="2">
          <el-button
            type="primary"
            style="width: 100%; max-width: 108px; margin-left: 5px;"
            @click="submitLogInfo"
            :disabled="showError"
            >提交</el-button
          >
        </el-col>
      </el-row>
      <el-row style="height: calc(100% - 109px)">
        <div ref="mdEditorContainer" style="width: 100%; height: 100%">
          <v-md-editor
            v-model="logInfo.logMd"
            placeholder="请编辑升级日志..."
            :height="editorHeight"
            @change="changeMd"
            :disabled-menus="[]"
            @upload-image="handleUploadImage"
          ></v-md-editor>
        </div>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

import { ElMessageBox } from "element-plus";
import { markRaw } from "vue";
import { Delete } from "@element-plus/icons-vue";
import { uploadImg, uploadProgramFile } from "../api/file";
import {
  addUpdateLog,
  checkVersionIsExist,
  queryLogInfo,
  queryAllLogPre,
  updateById,
} from "../api/log";
import { validatenull } from "../utils/validate";

export default {
  name: "logManage",
  data() {
    return {
      //是否是修改已上传过的日志
      isEditMode: false,
      showError: false,
      cancelSource: axios.CancelToken.source(),
      //窗口参数
      screenWidth: "",
      screenHeight: "",
      showProgress: false,
      //当前页面操作员
      currentOp: '',
      logInfo: {
        //文件上传百分比
        uploadPercentage: 0,
      },
      activeTimelineItemInfo: {},
      programFiles: [],
      editorHeight: "700px",
      logList: [],
    };
  },
  components: {},
  methods: {
    activeTimelineItem(log) {
      this.activeTimelineItemInfo.type = "info";
      this.activeTimelineItemInfo.hollow = true;
      this.activeTimelineItemInfo = log;
      this.activeTimelineItemInfo.hollow = false;
      this.activeTimelineItemInfo.type = "primary";
      queryLogInfo(log.id).then((res) => {
        this.logInfo = res.data;
        this.$refs["uploadProgram"].clearFiles();
        this.isEditMode = true;
        this.showError = false;
        if(!validatenull(this.logInfo.programFileUrl)){
          this.logInfo.uploadPercentage = 100;
          this.showProgress = true;
          this.$nextTick(() => {
            this.programFiles.push({
              name: this.logInfo.programFileUrl.split("/").pop(),
              url: this.logInfo.programFileUrl,
            });
          });
        } else {
          this.logInfo.uploadPercentage = 0;
          this.showProgress = false;
        }
      });
    },
    checkVersion() {
      if (!this.isEditMode && !validatenull(this.logInfo.programVersion)) {
        checkVersionIsExist(this.logInfo.programVersion).then((res) => {
          this.showError = res.data;
        });
      }
    },
    //上传文件数超过限制
    handleExceed() {
      this.$message.error(`当前限制选择 1 个文件，请删除后继续上传！`);
    },
    beforeRemoveFile() {
      return ElMessageBox.confirm(
        "删除文件后将重新上传文件，是否删除?",
        "Warning",
        {
          type: "warning",
          icon: markRaw(Delete),
        }
      ).then(
        () => true,
        () => false
      );
    },
    onRemoveFile() {
      //如果在上传未完成时删除文件，则先取消上传
      if (
        this.logInfo.uploadPercentage != 0 &&
        this.logInfo.uploadPercentage != 100
      ) {
        this.cancelSource.cancel();
      }
      this.logInfo.uploadPercentage = 0;
      this.logInfo.programFileUrl = "";
      this.showProgress = false;
    },
    doUploadFile(item) {
      this.showProgress = true;
      if (validatenull(this.logInfo.programVersion)) {
        this.$message.error(`未输入程序版本号，无法上传！！！`);
        return;
      }
      const formData = new FormData();
      formData.append("programFile", item.file);
      this.cancelSource = axios.CancelToken.source();
      uploadProgramFile(
        formData,
        this.logInfo.programVersion,
        this.logInfo,
        this.cancelSource
      )
        .then((res) => {
          this.$message.success("上传成功");
          this.logInfo.programFileUrl = res.data;
        })
        .catch((error) => {
          if (axios.isCancel(error)) {
            this.$message.info("上传已被取消");
          } else {
            this.$message.error("上传失败" + error);
          }
          this.logInfo.uploadPercentage = 0;
          this.showProgress = false;
        });
    },

    changeMd(value, render) {
      this.logInfo.logHtml = render;
    },
    handleUploadImage(event, insertImage, files) {
      let formData = new FormData();
      formData.append("file", files[0]);
      uploadImg(formData).then((res) => {
        insertImage({
          url: res.data,
          desc: "desc",
        });
      });
    },

    submitLogInfo() {
      if (
        this.logInfo.uploadPercentage != 0 &&
        this.logInfo.uploadPercentage != 100
      ) {
        this.$message.error("文件上传中无法提交！！！");
        return;
      }
      if (
        validatenull(this.logInfo.programVersion) ||
        validatenull(this.logInfo.logMd)
      ) {
        this.$message.error("版本号或升级日志不能为空！！！");
        return;
      }
      this.logInfo.modifierId=this.currentOp
      if(this.isEditMode) {
        const logId = this.logInfo.id
        if(validatenull(logId)) {
          this.$message.error('数据异常无法更新')
        }
        updateById(this.logInfo, logId).then(res => {
          if(res.data) {
            this.$message.success('更新成功')
            this.resetPage()
          }
        })
      }else {
        addUpdateLog(this.logInfo).then((res) => {
          if(res.data) {
            this.$message.success('新增成功')
            this.resetPage()
            this.queryLogPreList()
          }
        });
      }
    },

    cancelEdit() {
      //如果在上传未完成时删除文件，则先取消上传
      if (this.logInfo.uploadPercentage != 0 && this.logInfo.uploadPercentage != 100) {
        this.cancelSource.cancel();
      }
      this.resetPage()
    },
    //重置页面
    resetPage() {
      this.isEditMode = false
      this.logInfo = { uploadPercentage: 0 }
      this.showProgress = false
      this.programFiles = []
      this.activeTimelineItemInfo.type = "info";
      this.activeTimelineItemInfo.hollow = true;
      this.activeTimelineItemInfo = {};
    },

    // 获取左侧的日志列表
    queryLogPreList() {
      queryAllLogPre().then((res) => {
        const logPreList = res.data;
        this.logList = [];
        logPreList.forEach((log) => {
          let logPre = {
            type: "info",
            hollow: true,
            title: log.programVersion,
            id: log.id,
          };
          this.logList.push(logPre);
        });
      });
    },
  },
  mounted() {
    this.currentOp = this.$route.query.authCode;
    this.editorHeight = this.$refs.mdEditorContainer.clientHeight - 36 + "px";
    window.onresize = () => {
      return (() => {
        this.screenWidth = document.body.clientWidth;
        this.screenHeight = document.body.clientHeight;
        this.editorHeight = this.$refs.mdEditorContainer.clientHeight - 36 + "px";
      })();
    };
    this.queryLogPreList()
  },
  watch: {
    screenWidth() {
      this.editorHeight = this.$refs.mdEditorContainer.clientHeight - 36 + "px";
    },
  },
};
</script>
<style scoped>
.logManage {
  height: 100%;
  box-sizing: border-box;
  padding: 10px;
}
.timeLine {
  width: 200px;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  float: left;
  box-sizing: border-box;
  padding: 30px 20px 0px;
  border-right: 1px solid rgb(201, 201, 201);
}
.timeline-item {
  cursor: pointer;
}
.timeline-item:hover {
  color: #0078d7;
}
.edit-panel {
  height: 100%;
  overflow: hidden;
  padding: 0 20px;
  box-sizing: border-box;
}
.el-row {
  margin-bottom: 10px;
}
.edit-item-title {
  width: 70px;
  padding-right: 5px;
  text-align: right;
  line-height: 32px;
}
:deep(.edit-item-upload > .el-upload) {
  --el-upload-dragger-padding-horizontal: 10px !important;
}
</style>