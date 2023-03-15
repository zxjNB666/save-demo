import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import Users from '../components/user/Users.vue'
import Roles from '../components/role/Roles.vue'
import Menus from '../components/menu/Menus.vue'
import Departments from '../components/department/Departments.vue'
import ProductCategorys from '../components/product/ProductCategorys.vue'
import Suppliers from '../components/product/Suppliers.vue'
import Products from '../components/product/Products.vue'
import InStocks from '../components/product/InStocks.vue'
import OutStocks from '../components/product/OutStocks.vue'
import AddStocks from '../components/product/AddStocks.vue'
import GobalMap from '../components/other/map.vue'
import Swagger from '../components/other/Swagger.vue'
import Qqgrup from '../components/other/Qqgrup.vue'
import Druid from '../components/other/Druid.vue'
import Attachments from '../components/other/Attachments.vue'
import LoginLog from '../components/log/LoginLog.vue'
import Notices from '../components/notice/Notices.vue'
import AddNotices from '../components/notice/AddNotices.vue'
import Logs from '../components/log/Logs.vue'
import Consumers from '../components/product/Consumers.vue'
import Stocks from'../components/product/Stocks.vue'
import Icons from '../components/other/Icons.vue'
import Rumors from '../components/other/Rumors.vue'
import RumorDetail from '../components/other/RumorDetail.vue'
import Health from '../components/user/Health.vue'
import PublishStocks from '../components/product/PublishStocks.vue'
import NotPermission from '../components/401.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },

  {
    path: '/home',
    component: Home,
    meta:{title: '首页'},
    redirect: '/welcome',
    children: [{
      path: '/welcome',
      component: Welcome
    }, {
      path: '/users',
      component: Users,
      meta:{title: '用户管理'},
    }
    , {
      path: '/roles',
      component: Roles,
      meta:{title: '角色管理'},
    }
    , {
      path: '/menus',
      component: Menus,
    }
    , {
      path: '/departments',
      component: Departments,
    }

    , {
      path: '/productCategorys',
      component: ProductCategorys,
    }

    , {
      path: '/suppliers',
      component: Suppliers,
    }
    , {
      path: '/products',
      component: Products,
    }
    , {
      path: '/inStocks',
      component: InStocks,
    }
    , {
      path: '/inStocks/addStocks',
      component: AddStocks,
    },
    {
      path: '/map',
      component: GobalMap
    },

    {
      path: '/swagger',
      component: Swagger
    },
    {
      path: '/qqgrup',
      component: Qqgrup
    },

    {
      path: '/druid',
      component: Druid
    },
    {
      path: '/loginLog',
      component: LoginLog
    },

    {
      path: '/notices',
      component: Notices
    },
    {
      path: '/notices/add',
      component: AddNotices
    },
    {
      path: '/logs',
      component: Logs
    },

    {
      path: '/consumers',
      component: Consumers
    },

    {
      path: '/stocks',
      component: Stocks
    },
    {
      path: '/icons',
      component: Icons
    },
    {
      path: '/attachments',
      component: Attachments
    },
    {
      path: '/rumors',
      component: Rumors
    },
    {
      path: '/rumors/detail',
      component: RumorDetail
    },
    {
      path: '/health',
      component: Health
    },
    {
      path: '/outStocks',
      component: OutStocks
    },
    {
      path: '/OutStocks/publishStocks',
      component: PublishStocks
    },
    {
      path: "/401",
      component: NotPermission
    }
    ]
  },
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

import store from '../store'//引入store

//路由导航守卫
router.beforeEach((to, from, next) => {

  const token = window.localStorage.getItem('JWT_TOKEN');
  if (to.path == '/login') {
    if(!token){
      return next();
    }else{
      return next({path: '/home'})
    }
  }

  if(to.path=='/401'){
	  return next();
  }

  if (!token) {
    return next('/login');
  }else {
    //判断是否有访问该路径的权限
    const urls =store.state.userInfo.url;
    //如果是管理员
    if(store.state.userInfo.isAdmin){
       return next();
    }else{
      if(urls.indexOf(to.path) > -1){
        //则包含该元素
        window.sessionStorage.setItem("activePath", to.path);
        return next();
      }else{
        return next("/401");
      }
    }
  }
})

export default router
