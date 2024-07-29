<template>
    <div>
      <span class="top-bar"></span>
      <div class="login-wrap">
        <img src="../assets/img/logo-login.png" class="logo-login" alt="Flox" width="108" height="32">
  
        <form class="login-form" @submit.prevent="login">
          <input type="text" placeholder="Username" v-model="username" autofocus>
          <input type="password" placeholder="Password" v-model="password">
          <a class="register-btn" href="" @click="toRegister">没有账号？点击注册</a>
          <input type="submit" class="regi_btn" :class="errorShake ? 'shake-horizontal shake-constant' : ''" value="Login">
        </form>
      </div>
    </div>
  </template>
  
  <script>
import axios from 'axios';
import config from '@/config';
  export default {
    data() {
      return {
        username: '',
        password: '',
        error: false,
        errorShake: false,
        errorMessage: ''
      }
    },
  
    mounted() {
      document.body.classList.add('dark');
    },

    methods: {
      async login() {
        try {
          const response = await axios.post(`${config.url}doLogin`, {
              userName: this.username,//注意这里的变量名要和后端保持一致，name的n也要大写
              password: this.password
          });
          console.log('Response:', response); // 打印原始响应，便于调试

          if (response.data.code === 200) {
              const jwtToken = response.data.data; // 确保正确访问 token
              localStorage.setItem('jwt', jwtToken);  // 保存 JWT 到 localStorage
              this.$router.push('/');  // 重定向到主页
          } else {
              this.error = true;
              this.errorMessage = response.data?.message || '登录失败';
              alert(response.data.msg)
          }
        } catch (error) {
          console.error('Error:', error); // 打印错误，便于调试
          this.error = true;
          this.errorMessage = error.response?.data?.message || '登录时发生错误';
          this.errorShake = true;
          setTimeout(() => {
              this.errorShake = false;
          }, 500);
        }
      },
      toRegister() {
        this.$router.push('/userRegister')
      }
    }
  }
  </script>
  
<style scoped>
  .register-btn {
    color: #f1329b;
  }
</style>
  