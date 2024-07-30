import axios from 'axios'
import {ElMessageBox } from 'element-plus'
import { getToken, removeToken } from '@/utils/auth'
import router from '../router'
import {message} from '@/utils/singletonTip'
import { ElLoading } from 'element-plus'


// const BASE_API_URL = "/api/";
const BASE_API_URL = "http://172.20.21.108:10087/hsbSub/";
// const HeaderName = "A-TOKEN";

var elLoadingObject= null
// 还未得到服务器响应的请求个数，用于控制页面加载遮罩显示状态
var fetchCount= 0

//创建axios实例
const service = axios.create({
  baseURL: BASE_API_URL
})
//默认10s超时
service.defaults.timeout=10000

//request拦截
service.interceptors.request.use(config => {
  fetchCount += 1
  if(fetchCount > 0 && elLoadingObject == null) {
    elLoadingObject = ElLoading.service({
      lock: true,
      text: 'Loading',
      background: 'rgba(0, 0, 0, 0.7)',
    })
  }
  if(config.headers['Content-Type'] === undefined) {
    config.headers['Content-Type'] = "application/json"
  }
  let token = getToken();
  // if (token != null && token != "null" && token != undefined) {
  //   config.headers[HeaderName] = token;
  // }
  return config;
}, error => {
  // console.error("request error", error);
  Promise.reject(error);
})

// respone拦截器
service.interceptors.response.use(
  response => {
    /**
    * ret为非200是抛错
    */
    const res = response.data;
    fetchCount = fetchCount > 0 ? --fetchCount:0
    if(fetchCount==0 && elLoadingObject != null) {
      elLoadingObject.close()
      elLoadingObject = null
    }
    if (res.ret !== 200) {
      // 402:Token 过期了;
      if (res.ret === 402) {
        ElMessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/fedLogOut').then(() => {
            router.push({name: 's-login'}).then(() => {
              location.reload()// 为了重新实例化vue-router对象 避免bug
            })
          })
        });
        removeToken();
      }
      //未授权
      else if (res.ret === 401) {
        // this.$store.push({path: '/manage/home'});
        // Message({
        //   message: res.data,
        //   type: 'warning'
        // })
        message.warning(res.data);
      } else if (res.ret === 500) {
        message.error(res.data);
      } else {
        message.warning(res.data);
      }
      return Promise.reject('error');
    } else {
      return response.data
    }
  },
  error => {
    //如果不是手动取消请求就给出错误提示
    if(!axios.isCancel(error)) {
      message.error("后台连接出错请联系管理员");
    }
    return Promise.reject(error);
  }
)

export default service