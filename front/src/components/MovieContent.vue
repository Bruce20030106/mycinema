<template>
  <movie-header-backup></movie-header-backup>
  <div class="search-bar">
    <input type="text" placeholder="Search" @keydown="searchButtonDown" v-model="search"/>
  </div>
  <div>
    <div class="grid">
      <div class="card" v-for="(movie, index) in movies" :key="index" @click="goToMovieDetail(movie.movieId)">
        <img :src="getPosterUrl(movie.posterName)" alt="Movie Poster" class="movie-poster"/>
        <div class="info"><h3>{{ movie.movieName }}</h3></div>
        <div class="info">
          <span><b>评分：</b>{{ movie.rate }}</span>
          <span><b>类型：</b>{{ transformGerne(movie.genre) }}</span>
        </div>
      </div>
    </div>
    <div class="pagination">
      <button @click="prevPage" :disabled="currentPage === 1" class="page-btn">上一页</button>
      <span> {{ currentPage }} / {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages" class="page-btn">下一页</button>
    </div>
  </div>
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
      movies: [],
      currentPage: 1,
      totalPages: 5,
      search: '',
      gerneDir: ["动作", "剧情", "喜剧", "恐怖", "科幻", "爱情", "惊悚", "动画",
        "纪录片", "奇幻", "冒险", "悬疑", "犯罪", "音乐", "战争", "传记"
      ]
    };
  },
  created() {
    this.fetchMovies();
  },
  methods: {
    async fetchMovies() {
      try {
        const response = await axios.get(`${config.url}movies/${this.currentPage}`);
        if (response.data.code === 200) {
          this.movies = response.data.data.list;
          this.totalPages = response.data.data.pages;
        } else {
          alert('Failed to fetch movies');
        }
      } catch (error) {
        console.error('Error fetching movies:', error);
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
        this.fetchMovies();
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
        this.fetchMovies();
      }
    },
    goToMovieDetail(movieId) {
      this.$router.push(`/movie/${movieId}`);
    },
    getPosterUrl(posterName) {
      return require(`@/assets/img/movie_posts/${posterName}`);
    },
    searchButtonDown(event) {
      if (event.code == "Enter") {
        // 如果没有输入，就退回主页
        if (this.search.length == 0) {
          this.$router.push("/");
        } else {
          this.$router.push(`/searchMovies/${this.search}`);
        }
      }
    },
    transformGerne(genreNum) {
      return this.gerneDir[genreNum - 1];
    }
  }
};
</script>

<style lang="scss" scoped>
.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px 0;
}

.grid {
  background: linear-gradient(135deg, rgb(31, 31, 31), rgb(100, 100, 100));
}

.movie-poster {
  height: 444.44px;
}

.page-btn {
  background-color: white;
  border: 1px gray solid;
  margin: 0 10px;
  padding: 8px 12px;
  border-radius: 8px;
}

.page-btn:hover {
  cursor: pointer;
  border: 1px solid rgb(206, 29, 88);
  color: rgb(206, 29, 88);
}
</style>
