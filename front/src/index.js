//This coding style fits Vue 3 practice well.
import { createRouter, createWebHistory } from 'vue-router';
import config from './config';
import MovieContent from './components/MovieContent.vue';
import MovieDetail from './components/MovieDetail.vue';
import CinemaContent from './components/CinemaContent.vue';
import UserLogin from './components/UserLogin.vue';
import CinemaDetail from './components/CinemaDetail.vue';
import UserRegister from './components/UserRegister.vue';
import UserCenter from './components/UserCenter.vue';
import OrderContent from './components/OrderContent.vue';
import MovieSearchPage from './components/MovieSearchPage.vue';

const routes = [
  {  
    path: '/', 
    component: MovieContent, 
    name: 'home' ,
    meta: { title: '首页' }
  },
  {
    path:'/cinema', 
    component:CinemaContent, 
    name:'cinema',
    meta: { title: '影院列表' }
  },
  { 
    path:'/login',  
    component:UserLogin, 
    name:'login' ,
    meta: { title: '登录' }
  },
  {
    path: '/movie/:movieId',
    name: 'MovieDetail',
    component: MovieDetail,
    meta: { title: '电影详情' }
  },
  {
    path: '/cinema/:cinemaId',
    component: CinemaDetail,
    name:"CinemaDetail",
    meta: { title: '影院详情' }
  },
  {
    path: '/userRegister',
    component: UserRegister,
    name: 'register',
    meta: { title: '注册' }
  },
  {
    path: '/user',
    component: UserCenter,
    name: 'UserCenter',
    meta: { title: '用户中心' }
  },
  {
    path: '/order',
    component: OrderContent,
    name: 'OrderContent',
    meta: { title: '订单列表' }
  },
  {
    path: '/searchMovies/:partText',
    component: MovieSearchPage,
    name: 'MovieSearch',
    meta: { title: '搜索结果' }
  }
];

// Create a router instance
const router = createRouter({
  history: createWebHistory(config.uri),
  routes,
});

/**
 * 访问每个路由前的拦截操作
 */
// router.beforeEach((to, from, next) => {
//   const token = localStorage.getItem('jwt');

//   if (!token) {
//       // 如果没有token，跳转到登录页
//       if (to.path !== '/login') {
//           next('/login');
//       } else {
//           next();
//       }
//   } 
  // else {
  //     // 如果有token，检查是否过期
  //     try {
  //       const decodedToken = jwtDecode(token);
  //       const currentTime = Date.now() / 1000;
  //       if (decodedToken.exp < currentTime) {
  //           // token已过期，跳转到登录页
  //           localStorage.removeItem('jwt');
  //           next('/doLogin');
  //       } else {
  //           next();
  //       }
  //     } catch (error) {
  //       console.error('Token decoding error:', error);
  //       localStorage.removeItem('jwt');
  //       next('/doLogin');
  //     }
  //   }
// })

export default router