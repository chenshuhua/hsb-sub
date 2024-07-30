<template>
  <div class="milestone">
    <!-- <div style="height:100%" v-if="logInfo === {}">加载中</div>
    <div style="height:100%" v-else> -->
      <div class="timeLine">
        <el-timeline>
          <el-timeline-item
            v-for="(log, index) in logList"
            :key="index"
            :hollow="log.hollow"
            :type="log.type"
            :timestamp="log.timestamp"
          >
            <span class="timeline-item" @click="activeLog(log)">{{ log.title }}</span>
          </el-timeline-item>
        </el-timeline>
      </div>
      <div class="updateLog">
        <div style="line-height: 45px;border-bottom: 1px dashed #e2e2e2;">程序包地址: 
          <a v-if="logInfo.programFileUrl" :href="logInfo.programFileUrl" target="_blank">{{ logInfo.programFileUrl }}</a>
          <span v-else>此版本暂无程序包</span>
        </div>
        <v-md-preview-html :html="logInfo.logHtml" preview-class="github-markdown-body" @click="photoClick($event)"></v-md-preview-html>
      </div>
    <!-- </div> -->
    <BigImg :visible="photoVisible" :url="bigImgUrl" @closeClick="()=>{photoVisible=false}"></BigImg>
  </div>
</template>

<script>
import BigImg from "../components/BigImg"

import { validatenull } from "../utils/validate";
import {
  queryLogInfo,
  queryAllUpdateLogPre,
} from "../api/log";

export default {
  name: "milestone",
  components: {
    BigImg
  },
  data() {
    return {
      photoVisible: false,
      bigImgUrl: "",
      activeLogInfo: {},
      logList: [],
      logInfo: {},
    };
  },
  methods: {
    activeLog(log) {
      this.activeLogInfo.type = "info";
      this.activeLogInfo.hollow = true;
      this.activeLogInfo = log;
      this.activeLogInfo.hollow = false;
      this.activeLogInfo.type = "primary";
      queryLogInfo(log.id).then((res) => {
        this.logInfo = res.data;
      });
    },
    photoClick(event) {
      if (event.target.nodeName === "IMG") {
        this.photoVisible = true;
        this.bigImgUrl = event.target.src;
      }
    },
  },
  mounted() {
    queryAllUpdateLogPre().then((res) => {
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
      if(!validatenull(this.logList[0])) {
        this.activeLog(this.logList[0])
      }
    });
  },
}
</script>
<style scoped>
.milestone {
  height: 100%;
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
.updateLog {
  height: 100%;
  overflow-y: auto;
  padding: 20px;
  box-sizing: border-box;
}
.github-markdown-body {
  padding: 16px 0px !important;
}

/**文章样式微调 */
:deep(.github-markdown-body>blockquote) {
  margin: 0px 0px 10px !important;
  color: rgb(106, 115, 125);
  padding: 0px 1em;
  border-left: 0.25em solid rgb(223, 226, 229);
}

:deep(.github-markdown-body>p) {
  line-height: 32px;
}

:deep(.github-markdown-body>strong) {
  line-height: 40px;
}

:deep(.github-markdown-body>strong::before) {
  content: "\▲\00A0\00A0";
}

:deep(.github-markdown-body>pre) {
  font-size: 15px;
}

:deep(.github-markdown-body>code) {
  overflow: auto;
}

:deep(.github-markdown-body>h3::before) {
  content: "\❆\00A0\00A0\00A0";
}
</style>