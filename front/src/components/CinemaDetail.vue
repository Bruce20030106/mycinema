<template>
  <movie-header-backup></movie-header-backup>
  <div class="movie-details" >
    <div class="details-container" v-if="cinema">
      <div class="poster">
        <img :src="getPictureUrl(cinema.cinemaId)" alt="Movie Poster" />
      </div>
      
      <div class="details">
        <h1 class="movie-title">{{ cinema.cinemaName }}</h1>
        
        <div class="info" >
          <div style="font-size: 20px;"><br><b>地理位置：</b>{{ cinema.location }}</div>
          
        </div>
      </div>
    </div>
    <div class="details-container">
        <span style="font-size: 20px; color: #ff69b4;">近期热映▶️</span><br>
        <div class="grid" style="background: white;">
          <div class="card" style="width: 200px;" v-for="(movie, index) in movies" :key="index" @click="goToMovieDetail(movie.movieId)">
            <img :src="getPosterUrl(movie.posterName)" alt="Movie Poster" class="movie-poster"/>
            <div class="info">
              <h3>{{ movie.movieName }}</h3>
            </div>
            <div class="showtime-info">
              <span><b>上映时间：</b>{{ movie. showTime }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  <div>

  </div>
  <movie-footer></movie-footer>
</template>

<script>
import axios from 'axios'
import MovieFooter from './MovieFooter.vue'
import MovieHeaderBackup from './MovieHeaderBackup.vue'
import config from '@/config'

export default {
    components: {
        MovieHeaderBackup,
        MovieFooter
    },
    data(){
        return {
            cinema:null,
            movies:[],
            cinemaName:null,
            cinemaId:null
        };
    },
    created(){
      this.fetchCinema();
      this.fetchMoviesByCinema();
    },
    methods:{
      async fetchCinema() {
        this.cinemaId = this.$route.params.cinemaId;
        try {
          const response = await axios.post(`${config.url}getAllCinemas`);
          console.log(response.data.data);
          if (response.data.code === 200) {
            this.cinema = response.data.data.find(cinema => cinema.cinemaId.toString() === this.cinemaId);
            //注意这里的类型转换
            console.log(this.cinema)
            if (!this.cinema) {
              console.warn(`No cinema found with id: ${this.cinemaId}`);
            }
          } else {
            alert('Failed to fetch cinemas');
          }
        } catch (error) {
          console.error('Error fetching cinemas:', error);
        }
      },
      async fetchMoviesByCinema(){
        try{
          const response = await axios.post(`${config.url}cinema/getMoviesByCinema/${this.cinemaId}`);
          if (response.data.code === 200) {
            this.movies = response.data.data;
          }

        }catch(error){
          console.error('Error fetching movies by cinema:', error);
        }
      },
      getPictureUrl(cinemaId) {
        return require(`@/assets/img/cinema/cinema${cinemaId}.jpg`);
      },
      getPosterUrl(posterName) {
        return require(`@/assets/img/movie_posts/${posterName}`);
      },
      goToMovieDetail(movieId) {
        this.$router.push(`/movie/${movieId}`);
      }
    }
}
</script>

<style scoped>
  .movie-details {
    background: linear-gradient(135deg, rgb(31, 31, 31), rgb(100, 100, 100));
  }

  .movie-poster {
    width: 200px;
    height: 295.55px;
  }

  .info {
    height: 95px;
  }

  .showtime-info {
    margin-bottom: 20px;
  }
</style>