<template>
    <movie-header-backup></movie-header-backup>
    <div id="background">
        <h2 id="title">我的订单</h2>
        <div v-for="order in orders.arr" id="order-area">
            <order-card v-bind="order"></order-card>
        </div>
    </div>
</template>

<script setup>
    import MovieHeaderBackup from './MovieHeaderBackup.vue';
    import OrderCard from './OrderCard.vue';
    import { reactive, ref } from 'vue';
    import axios from 'axios';
    import router from '..';

    const orders = reactive({
        arr: []
    });

    const userId = ref('0')

    async function getUserId() {
        try {
            const response = await axios.post("/user/getUserInfo");
            if (response.data.code == 200) {
                const data = response.data.data;
                userId.value = data.userId;
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
            const response = await axios.get(`/orders/${userId.value}`);
            if (response.data.code == 200) {
                orders.arr = response.data.data;
            }
        } catch(error) {
            console.log(error);
        }
    }

    getUserId();
</script>

<style scoped>
    #background {
        width: 100%;
        height: 100%;
        background: linear-gradient(135deg, rgb(31, 31, 31), rgb(100, 100, 100));
        position: fixed;
    }

    #title {
        margin-top: 40px;
        margin-bottom: 30px;
        color: aliceblue;
    }

    #order-area {
        width: 70%;
        margin-left: auto;
        margin-right: auto;
    }

    .card-container {
        margin-bottom: 30px;
    }
</style>