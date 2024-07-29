<template>
    <div class="login-wrap">
        <img src="../assets/img/logo-login.png" class="logo-login" alt="Flox" width="108" height="32">
        <form class="login-form">
            <input type="text" placeholder="用户名" v-model.lazy="register_info.userName">
            <input type="password" placeholder="密码" v-model.lazy="register_info.password">
            <input type="password" placeholder="确认密码" v-model.lazy="confirm_password">
            性别
            <select name="gender_selector">
                <option value="M">男</option>
                <option value="F">女</option>
            </select>
            <input type="text" placeholder="年龄" v-model.lazy="register_info.age">
            <input type="button" value="注册" class="regi_btn" @click="register">
        </form>
    </div>
</template>

<script setup>
    import { reactive, ref } from 'vue';
    import config from '@/config'
    import axios from 'axios';
    import router from '..';

    const register_info = reactive({
        userId: 0,
        userName: "",
        password: "",
        gender: "M",
        age: null,
    })

    const confirm_password = ref("")

    async function register() {
        if (register_info.userName == "" || register_info.password == "") {
            alert("用户名和密码不能为空！")
            return;
        }

        if (confirm_password.value != register_info.password) {
            alert("两次输入密码不一致！")
            return;
        }

        else if(register_info.age == null){
            alert("请输入年龄！")
            return;
        }
        register_info.age=parseInt(register_info.age)
        const response = await axios.post(`${config.url}doRegister`, register_info);
        if (response.data.code == 200) {
            alert("注册成功！点击跳转到登录页……")
            router.push("/login");
        } else {
            alert(response.data.msg)
        }
    }
</script>

<style>
    .gender-selector {
        width: 10px;
        height: 10px;
    }

    .login-form {
        color: rgb(201, 200, 200);
    }

    .regi_btn {
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        background: linear-gradient(135deg, rgb(71, 27, 141),#ff69b4);
        color: white;
        cursor: pointer;
    }
</style>