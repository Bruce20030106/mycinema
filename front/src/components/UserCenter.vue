<template>
    <movie-header-backup></movie-header-backup>
    <div id="background">
        <div id="intro-area">
            <div id="intro-left">
                <div id="avatar">{{ user.first_letter }}</div>
                <div @click="logout()" id="logout-btn">登出</div>
            </div>
            <div id="intro-right">
                <div class="intro-block">
                    <span class="intro-label">用户名</span>
                    <span>{{ user.name }}</span>
                </div>
                <div class="intro-block">
                    <span class="intro-label">ID号</span>
                    <span>{{ user.id }}</span>
                </div>
                <div class="intro-block">
                    <span class="intro-label">性别</span>
                    <span>{{ user.gender }}</span>
                </div>
                <div class="intro-block">
                    <span class="intro-label">年龄</span>
                    <span>{{ user.age }}</span>
                </div>
            </div>
        </div>
        <div id="order-area">
            <div id="order-title-area">
                <span id="order-title">订单</span>
                <span id="more-order-btn" @click="toOrderPage()">查看更多订单</span>
            </div>
            <div v-for="order in orders.arr">
                <order-card v-bind="order"></order-card>
            </div>
        </div>  
    </div>

</template>

<script setup>
    import MovieHeaderBackup from './MovieHeaderBackup.vue';
    import OrderCard from './OrderCard.vue';
    import { reactive } from 'vue';
    import axios from 'axios';
    import router from '..';

    const user = reactive({
        id: 0,
        name: '',
        gender: 'M',
        age: 0,
        first_letter: ''
    });

    const orders = reactive({
        arr: []
    });

    async function fetchUser() {
        try {
            const response = await axios.post(`/user/getUserInfo`);
            if (response.data.code == 200) {
                const data = response.data.data;
                user.id = data.userId;
                user.name = data.userName;
                user.age = data.age;
                if (data.gender == 'M') {
                    user.gender = '男';
                } else {
                    user.gender = '女';
                }
                user.first_letter = user.name[0].toUpperCase();
                fetchOrder();
            } else {
                // 判定为没有登录，转到登录界面
                router.push("/login");
            }
        } catch (error) {
            console.log(error);
        }
    }

    async function fetchOrder() {
        try {
            const response = await axios.get(`/orders/${user.id}`);
            if (response.data.code == 200) {
                const allOrders = response.data.data;
                if (allOrders.length >= 1) {
                    orders.arr.push(allOrders[0]);
                }
                if (allOrders.length >= 2) {
                    orders.arr.push(allOrders[1]);
                }
            }
        } catch(error) {
            console.log(error);
        }
    }

    function logout() {
        const token = localStorage.getItem("jwt");
        try {
            // 登出
            axios.post("/doLogout", token);
            localStorage.setItem("jwt", "");
            // 跳到主页
            router.push("/")
        } catch (error) {
            console.log(error);
        }
    }

    function toOrderPage() {
        router.push("/order");
    }

    fetchUser();
</script>

<style scoped>
    #background {
        width: 100%;
        height: 100%;
        background: linear-gradient(135deg, rgb(31, 31, 31), rgb(100, 100, 100));
        position: fixed;
    }

    #intro-area {
        display: flex;
        justify-content: center;
        background: white;
        width: 70%;
        margin: 40px auto;
        padding-top: 20px;
        padding-bottom: 20px;
        border-radius: 20px;
        box-shadow: 0px 3px 5px 1px rgb(55, 55, 55);;
    }

    #intro-left {
        margin-right: 70px;
        padding-top: 20px;
    }

    #intro-right {
        border-left: 2px darkgray solid;
        padding-left: 70px;
        padding-top: 20px;
    }

    #avatar {
        width: 80px;
        height: 80px;
        font-size: 4em;
        background: lightpink;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 40px;
        font-weight: bold;
    }

    .intro-block {
        width: 100%;
        text-align: left;
        margin-bottom: 20px;
        font-size: 1.1em;
    }

    .intro-label {
        display: inline-block;
        width: 90px;
        font-weight: bolder;
    }

    #logout-btn {
        width: fit-content;
        margin-top: 20px;
        margin-left: auto;
        margin-right: auto;
    }

    #logout-btn:hover {
        cursor: pointer;
    }

    #order-area {
        width: 70%;
        margin: 0 auto;
    }

    #order-title-area {
        height: 60px;
        color: white;
    }

    #order-title {
        font-size: 1.5em;
        float: left;
    }

    #more-order-btn {
        float: right;
    }

    #more-order-btn:hover {
        cursor: pointer;
    }

    .card-container {
        margin-bottom: 30px;
    }
</style>