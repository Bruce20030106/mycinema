<template>
    <movie-header-backup></movie-header-backup>
    <div class="search-bar">
        <input type="text" placeholder="Search" @keydown="searchButtonDown" v-model="search"/>
    </div>
    <div>
        <div class="grid">
            <div class="card" v-for="movie in movies.arr" @click="goToMovieDetail(movie.movieId)">
                <img :src="getPosterUrl(movie.posterName)" alt="Movie Poster" class="movie-poster"/>
                <div class="info"><h3>{{ movie.movieName }}</h3></div>
                <div class="info">
                <span><b>评分：</b>{{ movie.rate }}</span>
                <span><b>领域：</b>{{ movie.genre }}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import MovieHeaderBackup from './MovieHeaderBackup.vue';
    import { reactive, ref } from 'vue';
    import router from '..';
    
    const movies = reactive({
        arr: []
    });

    const search = ref('');

    async function fetchMovies(partText) {
        try {
            const response = await axios.get(`/searchMovies/${partText}`);
            if (response.data.code == 200) {
                movies.arr = response.data.data;
            }
        } catch (error) {
            console.log(error);
        }
    }

    function goToMovieDetail(movieId) {
        router.push(`/movie/${movieId}`)
    }

    function getPosterUrl(posterName) {
        return require(`@/assets/img/movie_posts/${posterName}`);
    }

    function searchButtonDown(event) {
        if (event.code == "Enter") {
            // 如果没有输入，就退回主页
            if (search.value.length == 0) {
                router.push("/");
            } else {
                fetchMovies(search.value)
            }
        }
    }

    fetchMovies(router.currentRoute.value.params.partText);
</script>

<style scoped>
    .movie-poster {
        height: 444.44px;
    }
</style>