import { createApp } from 'vue';
import App from './App.vue';
import axios from 'axios';
import config from './config'; // 确保 config 导入正确
import router from './index'; // 确保路由导入正确
import '@/assets/sass/app.scss';

axios.defaults.baseURL = config.url;

axios.interceptors.request.use(
    (config) => {
      const token = localStorage.getItem('jwt');
      if (token) {
        config.headers.token = `${token}`;//注意看后端是不是只认token
      }
      return config;
    },
  );

// Create the Vue app instance
const app = createApp(App);

// Use the router
app.use(router);

// Mount the app
app.mount('#app');
