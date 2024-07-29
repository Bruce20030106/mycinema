<template>
    <div class="card-container">
        <div class="poster-area">
            <img :src="moviePoster" alt="Movie Poster">
        </div>
        <div class="info-area">
            <span>订单号：{{ props.orderId }}</span>
            <span>电影名：{{ props.movieName }}</span>
            <span>电影院：{{ props.cinemaName }}</span>
            <span>影厅号：{{ props.hallNumber }}</span>
            <span>座位号：{{ props.seatNumber }}</span>
            <span>订单数量：{{ props.ticketCount }}</span>
            <span>价格：{{ props.orderPrice }}</span>
        </div>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import config from '@/config';
    import { ref } from 'vue';

    const props = defineProps({
        orderId: Number,
        movieId: Number,
        movieName: String,
        cinemaName: String,
        hallNumber: String,
        seatNumber: String,
        ticketCount: Number,
        orderPrice: Number,
        showTime: String,
        orderTime: String,
        ticketPrice: Number
    })

    let moviePoster = ref("");

    async function fetchMoviePoster() {
        try {
            const response = await axios.get(`${config.url}movie/${props.movieId}`);
            if (response.data.code == 200) {
                const movie = response.data.data;
                moviePoster.value = movie.posterName;
                moviePoster.value = require(`@/assets/img/movie_posts/${movie.posterName}`);
            }
        } catch (error) {
            console.log(error);
        }
    }

    fetchMoviePoster();
</script>

<style scoped>
    .card-container {
        border-radius: 10px;
        box-shadow: 0px 3px 5px 1px rgb(55, 55, 55);
        overflow: hidden;
        padding-top: 5px;
        padding-bottom: 5px;
        display: flex;
        align-items: center;
        background: white;
    }

    .card-container span {
        margin-left: 15px;
        margin-right: 15px;
    }

    .card-container img {
        width: 80px;
        height: fit-content;
    }

    .poster-area {
        width: 100px;
        float: left
    }

    .info-area {
        width: calc(100% - 130px);
        float: right;
    }
</style>