<template>
  <movie-header-backup></movie-header-backup>
  <div class="movie-details">
    <div class="details-container" v-if="movie">
      <div class="poster">
        <img :src="getPosterUrl(movie.posterName)" alt="Movie Poster" />
      </div>
      
      <div class="details">
        <h1 class="movie-title">{{ movie.movieName }}</h1>
        
        <div class="info">
          <div class="rating"><b>评分：</b>{{ movie.rate }}</div>
          <div class="genre"><b>类型：</b>{{ transformGerne(movie.genre) }}</div>
        </div>
        
        <div class="info">
          <div ><b>上映时间：</b>{{ movie.showTime  }}</div>
        </div>

        <div class="info">
          <div><b>演员：</b>{{ movie.actors  }}</div>
        </div>

        <div class="info">
          <div><b>内容梗概：</b></div>
        </div>
        <div class="synopsis">
          <p>{{ movie.summary }}</p>
        </div>
        
        
        <button class="buy-ticket-button" @click="backToContent">返回</button>
      </div>
    </div>
    <div class="details-container">
        <span style="font-size: 20px; color: #ff69b4;">上映院线列表▶️</span><br>
        <div class="grid" style="background: white;">
          <div class="card" style="width: 200px;" v-for="(cinema, index) in cinemas" :key="index">
            <img :src="getPictureUrl(cinema.cinemaId)" alt="Cinema Picture" class="cinema-pic"/>
            <div class="info">
              <h3>{{ cinema.cinemaName }}</h3>
            </div>
            <div class="info"><b>地址</b>{{ cinema.location }}</div>
            <div class="info">价格：{{ cinema.ticketPrice }}￥/张</div>
            <button class="buy-ticket-button" @click = "buyTicket(cinema)">购票</button>
          </div>
        </div>
      </div>
    
    <ticket-modal
      v-model:modelValue="dialogVisible"
      :cinemaId="cinemaId"
      :movieId="movieId"
      :userId="userId"
      :ticketPrice="curCinema.ticketPrice"
      v-if="dialogVisible">
    </ticket-modal>
    <movie-footer></movie-footer>
  </div>
</template>

<script>
import axios from 'axios';
import MovieFooter from './MovieFooter.vue';
import MovieHeaderBackup from './MovieHeaderBackup.vue';
import config from '@/config';
import TicketModal from './TicketModal.vue';

export default {
  components: {
    MovieFooter,
    MovieHeaderBackup,
    TicketModal
  },
  data() {
    return {
      dialogVisible: false,
      movie: null,
      movieId:null,
      userId:null,
      curCinema:null,
      gerneDir: ["动作", "剧情", "喜剧", "恐怖", "科幻", "爱情", "惊悚", "动画",
        "纪录片", "奇幻", "冒险", "悬疑", "犯罪", "音乐", "战争", "传记"
      ]
    };
  },
  created(){
    this.fetchUserId();
    this.fetchMovieDetail();
    this.fetchCinemasByMovie();
  },
  methods:{
    async fetchUserId() {
      try {
        const response = await axios.post(`/user/getUserInfo`);
        if (response.data.code == 200) {
          const data = response.data.data;
          this.userId = data.userId;
        }
      } catch (error) {
        console.log(error);
      }
    },
    async fetchMovieDetail() {
      this.movieId = this.$route.params.movieId;
      try {
        const response = await axios.get(`${config.url}movie/${this.movieId}`);
        if (response.data.code === 200) {
          this.movie = response.data.data;
        } else {
          alert('Failed to fetch movie details');
        }
      } catch (error) {
        console.error('Error fetching movie details:', error);
      }
    },
    async fetchCinemasByMovie(){
      try{
          const response = await axios.post(`${config.url}movie/getCinemasByMovie/${this.movieId}`);
          if (response.data.code === 200) {
            this.cinemas = response.data.data;
            for (const cinema of this.cinemas) {
              cinema.ticketPrice = this.calTicketPrice(cinema.ticketPrices, this.movieId);
            }
          }

        }catch(error){
          console.error('Error fetching movies by cinema:', error);
        }
    },
    getPosterUrl(posterName) {
      return require(`@/assets/img/movie_posts/${posterName}`);
    },
    backToContent(){
      this.$router.push("/");
    },
    getPictureUrl(cinemaId) {
      return require(`@/assets/img/cinema/cinema${cinemaId}.jpg`);      
    },
    async buyTicket(cinema){
      if (!this.userId) {
        this.$router.push("/login");
        return;
      }
      this.dialogVisible = true;
      this.curCinema = cinema;
      this.cinemaId = cinema.cinemaId;
    },
    /**
     * 
     * @param { string } priceStr 
     * @param { number } movieId 
     */
    calTicketPrice(priceStr, movieId) {
      let price = 0;
      let strs = priceStr.split(',');
      for (const s of strs) {
        const movie_price = s.split(':');
        const id = +movie_price[0];
        if (movieId == id) {
          price = +movie_price[1];
          break;
        }
      }
      return price;
    },
    transformGerne(genreNum) {
      return this.gerneDir[genreNum - 1];
    }
  }
};
</script>

<style scoped>
/* 添加一些基本样式以提高视觉效果 */
p {
  text-align: left;
}

.movie-details {
  display: flex;
  flex-direction: column;
  align-items: center;
  background: linear-gradient(135deg, rgb(31, 31, 31), rgb(100, 100, 100));
}

.details-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.poster img {
  width: 300px;
  height: auto;
}

.details {
  margin-top: 20px;
}

.info {
  margin: 10px 0;
}

.synopsis {
  margin-top: 20px;
  max-width: 600px;
}

.cinema-pic {
  height: 143.4px;
  width: fit-content;
}

.buy-ticket-button {
  margin: 20px auto;
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
}

.buy-ticket-button:hover {
  background-color: #0056b3;
}
</style>
