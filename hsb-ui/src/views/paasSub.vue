<template>
  <div class="paas-sub">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>公司PAAS平台使用关键点说明</span>
        </div>
      </template>
      <div>
        <h3>1 关于内部映射端口(仅限于HSB2.X及以上版本)</h3>
        <p class="ps">
          由于新增内部通信模块故需要开放两个内部通信端口，console的默认通信端口为10087、engine的默认通信端口为10086。如果端口配置有修改那这里也应做出对应调整。具体配置如下图:
        </p>
        <img src="@/assets/img/1.jpg" />

        <h3>2 如何使用本页面生成的启动命令</h3>
        <p class="ps">
          将生成的命令复制到对应程序的页面，然后启动程序即可。具体如下图：
        </p>
        <img src="@/assets/img/2.jpg" />
      </div>
    </el-card>

    <el-card class="box-card" style="margin-top: 10px;">
      <template #header>
        <div class="card-header">
          <span>HSB2.X+ PAAS平台命令生成器</span>
        </div>
      </template>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-card class="box-card" style=" height: 1856px; overflow-y: auto; position: relative;">
            <!-- <div> -->
              <p class="custom-title">CONSOLE启动命令生成器</p>
              <el-form
                label-position="left"
                label-width="160px"
                ref="console2FormRef"
                :model="console2Form"
              >
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##启动占用端口</p>
                  </el-alert>
                  <el-form-item
                    label="server_port"
                    prop="server_port"
                    :rules="[{ required: true, message: '请输入启动占用端口', trigger: 'blur' },]"
                  >
                    <el-input v-model="console2Form.server_port" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##日志目录（一般情况不用修改）</p>
                  </el-alert>
                  <el-form-item 
                    label="logging_dir"
                    prop="logging_dir"
                    :rules="[{ required: true, message: '请输入日志目录', trigger: 'blur' },]"
                  >
                    <el-input v-model="console2Form.logging_dir" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##应用名（一般情况不用修改）</p>
                  </el-alert>
                  <el-form-item label="server_name" prop="server_name" :rules="[{ required: true, message: '请输入应用名', trigger: 'blur' },]">
                    <el-input v-model="console2Form.server_name" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="error" :closable="false">
                    <p>##jar包名称, paas平台不可用该默认值!!！jar包的名字必须改为：应用标识.jar!!！应用标识可在应用的总览页面看到, eg: console.jar</p>
                  </el-alert>
                  <el-form-item label="jar_file" prop="jar_file" :rules="[{ required: true, message: '请输入jar包名称', trigger: 'blur' },]">
                    <el-input v-model="console2Form.jar_file" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##服务器IP(生产环境不可填127.0.0.1),
                      pass平台这里填"访问端口"页面表格中对应程序运行端口的那条数据的服务名称，eg:
                      console-expose-svc-8081
                    </p>
                  </el-alert>
                  <el-form-item label="app_host" prop="app_host" :rules="[{ required: true, message: '请输入服务器IP', trigger: 'blur' },]">
                    <el-input v-model="console2Form.app_host" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##console启动时间，格式为yyyy-mm-dd
                    </p>
                  </el-alert>
                  <el-form-item label="startRunTime" prop="boot_date" :rules="[{ required: true, message: '请输入服务器IP', trigger: 'blur' },]">
                    <el-input v-model="console2Form.boot_date" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##console的通信端口（这是通信端口并不是程序运行端口，默认为10087一般无需修改，需和engine程序的同名配置保持一致）
                    </p>
                  </el-alert>
                  <el-form-item label="console_comm_port" prop="console_comm_port" :rules="[{ required: true, message: '请输入console的通信端口', trigger: 'blur' },]">
                    <el-input v-model="console2Form.console_comm_port" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##Prometheus地址，eg: http://127.0.0.1:9090</p>
                  </el-alert>
                  <el-form-item label="prometheus_address" prop="prometheus_address" :rules="[{ required: true, message: '请输入Prometheus地址', trigger: 'blur' },]">
                    <el-input v-model="console2Form.prometheus_address" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##elasticsearch地址，eg: 127.0.0.1:9200, 集群环境下有多个地址的情况用逗号隔开</p>
                  </el-alert>
                  <el-form-item label="elasticsearch_host" prop="elasticsearch_host" :rules="[{ required: true, message: '请输入elasticsearch地址', trigger: 'blur' },]">
                    <el-input v-model="console2Form.elasticsearch_host" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##elasticsearch登录账号(如果没设置就可以不填)</p>
                  </el-alert>
                  <el-form-item label="elasticsearch_username">
                    <el-input v-model="console2Form.elasticsearch_username" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##elasticsearch登录密码(如果没设置就可以不填)</p>
                  </el-alert>
                  <el-form-item label="elasticsearch_password">
                    <el-input v-model="console2Form.elasticsearch_password" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##数据库连接URL</p>
                  </el-alert>
                  <el-form-item label="db_url" prop="db_url" :rules="[{ required: true, message: '请输入数据库连接URL', trigger: 'blur' },]">
                    <el-input v-model="console2Form.db_url" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##数据库用户名</p>
                  </el-alert>
                  <el-form-item label="db_username" prop="db_username" :rules="[{ required: true, message: '请输入数据库用户名', trigger: 'blur' },]">
                    <el-input v-model="console2Form.db_username" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##数据库连接密码，如果项目要求密码需要配置密文这里就写密文至于如何加密为密文移步部署文档的第三部分
                    </p>
                  </el-alert>
                  <el-form-item label="db_password" prop="db_password" :rules="[{ required: true, message: '请输入数据库连接密码', trigger: 'blur' },]">
                    <el-input v-model="console2Form.db_password" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##数据库密码加密密码，这里和生成加密密码时的password配置的值需保持一致如果不明白就别修改这里的默认值(如果数据库密码直接配置明文这里就不需要配置)
                    </p>
                  </el-alert>
                  <el-form-item label="encryptor_password">
                    <el-input v-model="console2Form.encryptor_password" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##数据库密码加密算法，这里和生成加密密码时的algorithm配置的值需保持一致如果不明白就别修改这里的默认值(如果数据库密码直接配置明文这里就不需要配置)
                    </p>
                  </el-alert>
                  <el-form-item label="encryptor_algorithm">
                    <el-input v-model="console2Form.encryptor_algorithm" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##前后端跨域配置，不可直接配置成“*”，多个用逗号隔开</p>
                  </el-alert>
                  <el-form-item label="cors_allowed_origins" prop="cors_allowed_origins" :rules="[{ required: true, message: '请输入前后端跨域配置', trigger: 'blur' },]">
                    <el-input v-model="console2Form.cors_allowed_origins" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="info" :closable="false">
                    <p>##生成的启动命令：</p>
                  </el-alert>
                  <el-input
                    v-model="console2Shell"
                    type="textarea"
                    resize="none"
                  />
                  <el-button type="primary" @click="buildConsole2Shell">生成启动命令</el-button>
                </el-space>
              </el-form>
            <!-- </div> -->
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card class="box-card" style=" height: 1856px; overflow-y: auto; position: relative;">
            <!-- <div> -->
              <p class="custom-title">ENGINE启动命令生成器</p>
              <el-form
                label-position="left"
                label-width="160px"
                :model="engine2Form"
                ref="engine2FormRef"
              >
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##启动占用端口</p>
                  </el-alert>
                  <el-form-item
                    label="server_port"
                    prop="server_port"
                    :rules="[{ required: true, message: '请输入启动占用端口', trigger: 'blur' },]"
                  >
                    <el-input v-model="engine2Form.server_port" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##日志目录（一般情况不用修改）</p>
                  </el-alert>
                  <el-form-item 
                    label="logging_dir"
                    prop="logging_dir"
                    :rules="[{ required: true, message: '请输入日志目录', trigger: 'blur' },]"
                  >
                    <el-input v-model="engine2Form.logging_dir" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##应用名（一般情况不用修改）</p>
                  </el-alert>
                  <el-form-item label="server_name" prop="server_name" :rules="[{ required: true, message: '请输入应用名', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.server_name" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="error" :closable="false">
                    <p>##jar包名称, paas平台不可用该默认值!!！jar包的名字必须改为：应用标识.jar!!！应用标识可在应用的总览页面看到, eg: engine.jar</p>
                  </el-alert>
                  <el-form-item label="jar_file" prop="jar_file" :rules="[{ required: true, message: '请输入jar包名称', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.jar_file" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##服务器IP(生产环境不可填127.0.0.1),
                      pass平台这里填"访问端口"页面表格中对应程序运行端口的那条数据的服务名称，eg:
                      engine-expose-svc-8082
                    </p>
                  </el-alert>
                  <el-form-item label="app_host" prop="app_host" :rules="[{ required: true, message: '请输入服务器IP', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.app_host" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##elasticsearch地址，eg: 127.0.0.1:9200, 集群环境下有多个地址的情况用逗号隔开</p>
                  </el-alert>
                  <el-form-item label="elasticsearch_host" prop="elasticsearch_host" :rules="[{ required: true, message: '请输入服务器IP', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.elasticsearch_host" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##elasticsearch登录账号(如果没设置就可以不填)</p>
                  </el-alert>
                  <el-form-item label="elasticsearch_username">
                    <el-input v-model="engine2Form.elasticsearch_username" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##elasticsearch登录密码(如果没设置就可以不填)</p>
                  </el-alert>
                  <el-form-item label="elasticsearch_password">
                    <el-input v-model="engine2Form.elasticsearch_password" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##push_gateway地址，eg: 127.0.0.1:9091</p>
                  </el-alert>
                  <el-form-item label="push_gateway_address" prop="push_gateway_address" :rules="[{ required: true, message: '请输入服务器IP', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.push_gateway_address" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##数据库连接URL</p>
                  </el-alert>
                  <el-form-item label="db_url" prop="db_url" :rules="[{ required: true, message: '请输入数据库连接URL', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.db_url" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##数据库用户名</p>
                  </el-alert>
                  <el-form-item label="db_username" prop="db_username" :rules="[{ required: true, message: '请输入数据库用户名', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.db_username" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##数据库连接密码，如果项目要求密码需要配置密文这里就写密文至于如何加密为密文移步部署文档的第三部分
                    </p>
                  </el-alert>
                  <el-form-item label="db_password" prop="db_password" :rules="[{ required: true, message: '请输入数据库连接密码', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.db_password" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##数据库密码加密密码，这里和生成加密密码时的password配置的值需保持一致如果不明白就别修改这里的默认值(如果数据库密码直接配置明文这里就不需要配置)
                    </p>
                  </el-alert>
                  <el-form-item label="encryptor_password">
                    <el-input v-model="engine2Form.encryptor_password" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##数据库密码加密算法，这里和生成加密密码时的algorithm配置的值需保持一致如果不明白就别修改这里的默认值(如果数据库密码直接配置明文这里就不需要配置)
                    </p>
                  </el-alert>
                  <el-form-item label="encryptor_algorithm">
                    <el-input v-model="engine2Form.encryptor_algorithm" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##engine的通信端口（这是通信端口并不是程序运行端口，默认为10086一般无需修改，需和engine程序的同名配置保持一致）
                    </p>
                  </el-alert>
                  <el-form-item label="engine_comm_port" prop="engine_comm_port" :rules="[{ required: true, message: '请输入engine的通信端口', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.engine_comm_port" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##console的通信端口,需和console程序配置的通信端口保持一致（这是通信端口并不是程序运行端口，默认为10087一般无需修改，需和engine程序的同名配置保持一致）
                    </p>
                  </el-alert>
                  <el-form-item label="console_comm_port" prop="console_comm_port" :rules="[{ required: true, message: '请输入console的通信端口', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.console_comm_port" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##console的通信Ip(eg：192.168.31.31),
                      pass平台这里填console应用的"访问端口"页面表格中对应上一个配置中engine的通信端口的那条数据的服务名称，eg:
                      console-expose-svc-10087
                    </p>
                  </el-alert>
                  <el-form-item label="console_comm_ip" prop="console_comm_ip" :rules="[{ required: true, message: '请输入Console通信IP', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.console_comm_ip" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                      ##配置任务调度服务端地址，这里配置的是console的服务地址例如http://127.0.0.1:8081/console
                      pass平台的ip需填程序运行端口对应的服务名eg: http://console-expose-svc-8081:8081/console
                    </p>
                  </el-alert>
                  <el-form-item label="xxl_job_address" prop="xxl_job_address" :rules="[{ required: true, message: '请输入任务调度服务端地址', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.xxl_job_address" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                        执行器名称与执行器管理配置的appname需一致这里给出一个默认值一般情况下无需修改，具体用法请参照操作手册>>2.4.1.执行器管理
                    </p>
                  </el-alert>
                  <el-form-item label="xxl_job_executor_appname" prop="xxl_job_executor_appname" :rules="[{ required: true, message: '请输入执行器名称', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.xxl_job_executor_appname" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                        客户端执行器端口，配置一个端口用以执行执行定时任务，这里默认配置9998（可根据现场情况修改）
                    </p>
                  </el-alert>
                  <el-form-item label="xxl_job_executor_port" prop="xxl_job_executor_port" :rules="[{ required: true, message: '请输入客户端执行器端口', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.xxl_job_executor_port" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>
                        自动巡检开关(目前的巡检机制会给三方系统造成不必要的报错，建议关闭)---true/false
                    </p>
                  </el-alert>
                  <el-form-item label="enable_inspection" prop="enable_inspection" :rules="[{ required: true, message: '选择', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.enable_inspection" />
                  </el-form-item>
                </el-space>
                <el-space fill style="width: 100%">
                  <el-alert type="success" :closable="false">
                    <p>##巡检周期 单位ms</p>
                  </el-alert>
                  <el-form-item label="inspection_check_interval" prop="inspection_check_interval" :rules="[{ required: true, message: '请输入巡检周期', trigger: 'blur' },]">
                    <el-input v-model="engine2Form.inspection_check_interval" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" />
                  </el-form-item>
                </el-space>
              </el-form>
              <el-space fill style="width: 100%">
                <el-alert type="info" :closable="false">
                  <p>##生成的启动命令：</p>
                </el-alert>
                <el-input
                  v-model="engine2Shell"
                  type="textarea"
                  resize="none"
                />
                <el-button type="primary" @click="buildEngine2Shell"
                  >生成启动命令</el-button
                >
              </el-space>
            <!-- </div> -->
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import { reactive, ref } from "vue";

//hsb1.x console的form表单对象
const console1Form = reactive({});
//hsb1.x engine的form表单对象
const engine1Form = {};
//hsb2.x console的form表单对象
const console2Form = reactive({
  server_port: "8081",
  logging_dir: "logs",
  server_name: "yh-hsb-console",
  jar_file: "yh-hsb-console.jar",
  app_host: "",
  boot_date: "",
  console_comm_port: "10087",
  prometheus_address: "",
  elasticsearch_host: "",
  elasticsearch_username: "",
  elasticsearch_password: "",
  db_url: "",
  db_username: "",
  db_password: "",
  encryptor_password: "yinhai@123",
  encryptor_algorithm: "PBEWithMD5AndDES",
  cors_allowed_origins: "",
});
//hsb2.x engine的form表单对象
const engine2Form = reactive({
  server_port: "8082",
  logging_dir: "logs",
  server_name: "yh-hsb-engine",
  jar_file: "yh-hsb-engine.jar",
  app_host: "",
  app_name: "yh-hsb-engine",
  elasticsearch_host: "",
  elasticsearch_username: "",
  elasticsearch_password: "",
  push_gateway_address: "",
  db_url: "",
  db_username: "",
  db_password: "",
  encryptor_password: "yinhai@123",
  encryptor_algorithm: "PBEWithMD5AndDES",
  engine_comm_port: "10086",
  console_comm_port: "10087",
  console_comm_ip: "",
  xxl_job_address: "",
  xxl_job_executor_appname: "xxl-job-executor-engine",
  xxl_job_executor_port: "9998",
  enable_inspection: "false",
  inspection_check_interval: "600000",
});

const console2Mapping = {
  app_host: " -Dapplication.host=",
  boot_date: " -Dconsole.startRunTime=",
  console_comm_port: " -Dserver.center.register.port=",
  prometheus_address: " -Dprometheus.address=",
  elasticsearch_host: [" -Delasticsearch.rest.hostNames=", " -Dspring.elasticsearch.rest.uris=", " -DlogBack.elasticsearch.rest.hostNames="],
  elasticsearch_username: [" -Delasticsearch.rest.username=", " -DelasticUser=", " -DlogBack.elasticUser="],
  elasticsearch_password: [" -Delasticsearch.rest.password=", " -DelasticPassword=", " -DlogBack.elasticPassword="],
  db_url: " -Dta404.database.mybatis.ta404ds.url=",
  db_username: " -Dta404.database.mybatis.ta404ds.username=",
  db_password: " -Dta404.database.mybatis.ta404ds.password=",
  encryptor_password: " -Djasypt.encryptor.password=",
  encryptor_algorithm: " -Djasypt.encryptor.algorithm=",
  cors_allowed_origins: " -Dta404.resource.cors.allowed-origins=",
}

const engine2Mapping = {
  app_host: " -Dapplication.host=",
  elasticsearch_host: [" -Delasticsearch.rest.hostNames=", " -Dspring.elasticsearch.rest.uris=", " -DlogBack.elasticsearch.rest.hostNames="],
  elasticsearch_username: [" -Delasticsearch.rest.username=", " -DelasticUser="],
  elasticsearch_password: [" -Delasticsearch.rest.password=", " -DelasticPassword="],
  push_gateway_address: " -Dengine.metrics.push-gateway-address=",
  db_url: " -Dta404.database.mybatis.ta404ds.url=",
  db_username: " -Dta404.database.mybatis.ta404ds.username=",
  db_password: " -Dta404.database.mybatis.ta404ds.password=",
  encryptor_password: " -Djasypt.encryptor.password=",
  encryptor_algorithm: " -Djasypt.encryptor.algorithm=",
  engine_comm_port: " -Dserver.center.client.port=",
  console_comm_port: " -Dserver.center.client.registerPort=",
  console_comm_ip: " -Dserver.center.client.registerIp=",
  xxl_job_address: " -Dxxl.job.admin.addresses=",
  xxl_job_executor_appname: " -Dxxl.job.executor.appname=",
  xxl_job_executor_port: " -Dxxl.job.executor.port=",
  enable_inspection: " -Dinspection.enable=",
  inspection_check_interval: " -Dinspection.checkInterval=",
}

export default {
  name: "paasSub",
  data() {
    return {
      console1Form,
      console1Shell: "",
      engine1Form,
      engine1Shell: "",
      console2Form,
      console2Mapping,
      console2Shell: "",
      engine2Form,
      engine2Mapping,
      engine2Shell: "",
    };
  },
  methods: {
    buildConsole2Shell() {
      this.$refs.console2FormRef.validate((valid) => {
        if(valid) {
          this.doBuildConsole2Shell()
        } else {
          this.console2Shell = "请填完所有必填项！！！"
        }
      })
    },
    doBuildConsole2Shell() {
      this.console2Shell = "java"
      for(let key in this.console2Form) {
        const javaAppOptValue = this.console2Form[key].trim()
        if(this.isNullString(javaAppOptValue)) {
          continue;
        }
        let javaAppOpt = this.console2Mapping[key]
        if(javaAppOpt != undefined) {
          if(javaAppOpt instanceof Array) {
            for(let item of javaAppOpt) {
              this.console2Shell += item+javaAppOptValue
              if(key === "elasticsearch_password") {
                this.console2Shell += " -DauthEnabled=true"
                this.console2Shell += " -DlogBack.authEnabled=true"
              }
            }
          } else {
            this.console2Shell += javaAppOpt+javaAppOptValue
          }
        }
      }
      this.console2Shell += " -Dfile.encoding=UTF-8 -jar /opt/" + this.console2Form["jar_file"].trim()
      this.console2Shell += " --server.port=" + this.console2Form["server_port"].trim()
    },
    buildEngine2Shell() {
      this.$refs.engine2FormRef.validate((valid) => {
        if(valid) {
          this.doBuildEngine2Shell()
        } else {
          this.engine2Shell = "请填完所有必填项！！！"
        }
      })
    },
    doBuildEngine2Shell() {
      this.engine2Shell = "java"
      for(let key in this.engine2Form) {
        const javaAppOptValue = this.engine2Form[key].trim()
        if(this.isNullString(javaAppOptValue)) {
          continue;
        }
        let javaAppOpt = this.engine2Mapping[key]
        if(javaAppOpt != undefined) {
          if(javaAppOpt instanceof Array) {
            for(let item of javaAppOpt) {
              this.engine2Shell += item+javaAppOptValue
              if(key === "elasticsearch_password") {
                this.engine2Shell += " -DauthEnabled=true"
              }
            }
          } else {
            this.engine2Shell += javaAppOpt+javaAppOptValue
          }
        }
      }
      this.engine2Shell += " -Dfile.encoding=UTF-8 -jar /opt/" + this.engine2Form["jar_file"].trim()
      this.engine2Shell += " --server.port=" + this.engine2Form["server_port"].trim()
    },
    isNullString(str) {
      return str === undefined || str === null || str === "" || str === "null" || str == "undefined";
    }
  },
};
</script>

<style scoped>
.paas-sub {
  height: 100%;
  padding: 10px;
  box-sizing: border-box;
  overflow-y: auto;
}
.ps {
  margin-bottom: 10px;
}
.custom-title {
  border-bottom: 1px dotted;
  padding-bottom: 5px;
  font-size: 15px;
  font-weight: 600;
  font-family: math;
  margin-bottom: 5px;
}
.el-card__body {
    padding: var(--el-card-padding);
    height: 100%;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
}
</style>