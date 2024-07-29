<template>
  <movie-header-backup></movie-header-backup>
  <div>
    <div class="grid">
      <div class="card" v-for="(cinema, index) in cinemas" :key="index">
        <img :src="getPictureUrl(cinema.cinemaId)" alt="Cinema Picture" class="cinema-pic"/>
        <!-- 因为数据库里面没有做电影名的返回值，所以使用cinemaId代替 -->
        <div class="info">
          <h3>{{ cinema.cinemaName }}</h3>
        </div>
        <div class="info"><b>地址：</b>{{ cinema.location }}</div>
        <button class="buy-ticket-button" @click = "goToCinemaDetail(cinema.cinemaId)">详情</button>
      </div>
    </div>

  </div>
  <movie-footer></movie-footer>
</template>

<script>
import axios from 'axios';
import MovieFooter from './MovieFooter.vue';
import MovieHeaderBackup from './MovieHeaderBackup.vue';
import config from '@/config';

export default {
  components: {
    MovieFooter,
    MovieHeaderBackup
  },
  data() {
    return {
      cinemas: []
    };
  },
  created() {
    this.fetchCinemas();
  },
  methods: {
    async fetchCinemas() {
      try {
        const response = await axios.post(`${config.url}getAllCinemas`);
        
        if (response.data.code === 200) {
          this.cinemas = response.data.data;
        } else {
          alert('Failed to fetch cinemas');
        }
      } catch (error) {
        console.error('Error fetching cinemas:', error);
      }
    },
    getPictureUrl(cinemaId) {
      return require(`@/assets/img/cinema/cinema${cinemaId}.jpg`);
    },
    goToCinemaDetail(cinemaId) {
      this.$router.push( `/cinema/${cinemaId}`);
    }
  }
};
</script>

<style lang="scss" scoped>
.pagination {
  display: flex;
  justify-content: center;
  margin: 20px 0;
}

.pagination button {
  margin: 0 10px;
  padding: 10px 20px;
}

.cinema-pic {
  width: 300px;
  height: 200px;
}

.buy-ticket-button {
  margin-bottom: 20px;
}

.grid {
  background: linear-gradient(135deg, rgb(31, 31, 31), rgb(100, 100, 100));
}
</style>
