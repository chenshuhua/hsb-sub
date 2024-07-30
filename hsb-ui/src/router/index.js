import { createRouter, createWebHashHistory } from 'vue-router'
import { ElMessageBox } from "element-plus";
import { authCheck } from "../api/log";
import {message} from '@/utils/singletonTip'
import md5 from 'js-md5'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { fa } from 'element-plus/es/locale';


const routes = [
  { path: '/:pathMatch(.*)*', redirect: '/log/404' },
  { path: '', redirect: '/log/milestone' },
  { path: '/', redirect: '/log/milestone' },
  {
    path: "/log/404",
    component: () => import('@/views/404'),
    name: 'not_found',
    meta: {
      index: 'not_found',
      auth: false
    }
  },
  {
    path: "/log/home",
    component: () => import('@/views/home'),
    name: 'home',
    meta: {
      index: 'home',
      auth: false
    },
    children:[
      {
          path: "/log/milestone",
          component: () => import('@/views/milestone'),
          name: 'milestone',
          meta: {
            index: 'milestone',
            auth: false
          }
      },
      {
        path: "/log/opManual",
        component: () => import('@/views/opManual'),
        name: 'opManual',
        meta: {
          index: 'opManual',
          auth: false
        }
      },
      {
        path: "/log/logManage",
        component: () => import('@/views/logManage'),
        name: 'logManage',
        meta: {
          index: 'logManage',
          auth: true,
        }
      },
      {
        path: "/log/paasSub",
        component: () => import('@/views/paasSub'),
        name: 'paasSub',
        meta: {
          index: 'paasSub',
          auth: false,
        }
      },
      {
        path: "/log/debuggingGuide",
        component: () => import('@/views/debuggingGuide'),
        name: 'debuggingGuide',
        meta: {
          index: 'debuggingGuide',
          auth: false,
        }
      }
    ]
  },
]
const router = createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: createWebHashHistory('/hsbupdatelog/'),
    routes,
})


router.beforeEach((to, from, next) => {
  NProgress.start()
  if (to.matched.some(record => record.meta.auth)) {
    ElMessageBox.prompt(
      "此界面为管理界面，请输入授权码",
      "Warning",
      {
        inputType: 'password',
        confirmButtonText: '确认',
        cancelButtonText: '取消',
      }
    ).then(({ value }) => {
      const authCode = md5(value)
      authCheck(authCode).then(res => {
        if(res.data) {
          to.query = {authCode: authCode}
          next(true)
        }else {
          next({path: '/log/milestone'})
          message.error('授权码错误,返回主页')
        }
      }).catch(() => {
        next({path: '/log/milestone'})
        message.error('系统异常,返回主页')
      })
    })
    .catch(() => {
      next({path: '/log/milestone'})
    })
  }else {
    next(true)
  }
})
 
router.afterEach(() => {
  NProgress.done()
})
export default router