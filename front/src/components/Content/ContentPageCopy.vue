<template>
    <main>
      <div class="content-submenu" v-if="!loading && items.length">
        <div class="sort-wrap no-select">
          <div class="sort-direction" @click="setUserSortDirection()">
            <i v-if="userSortDirection == 'asc'">&#8593;</i>
            <i v-if="userSortDirection == 'desc'">&#8595;</i>
          </div>
          <div class="filter-wrap">
            <span class="current-filter" @click="toggleShowFilters()">{{ lang(userFilter) }} <span class="arrow-down"></span></span>
            <ul class="all-filters" :class="{active: showFilters}">
              <li v-for="filter in filteredFilters" :key="filter" @click="setUserFilter(filter)">{{ lang(filter) }}</li>
            </ul>
          </div>
        </div>
      </div>
  
      <div class="wrap-content" v-if="!loading">
        <movie-item :item="item" v-for="(item, index) in items"
              :key="index"
              :genre="displayGenre"
              :date="displayDate"
              :ratings="displayRatings"
        ></movie-item>
  
        <span class="nothing-found" v-if="!items.length">{{ lang('nothing found') }}</span>
  
        <div class="load-more-wrap">
          <span class="load-more" v-if="!clickedMoreLoading && paginator" @click="loadMore()">{{ lang('load more') }}</span>
          <span class="loader" v-if="clickedMoreLoading"><i></i></span>
        </div>
      </div>
       <span>你好</span>
  
      <span class="loader fullsize-loader" v-if="loading"><i></i></span>
    </main>
  </template>
  
  <script>
  // import MovieItem from './MovieItem.vue';
  import { mapActions, mapState, mapMutations } from 'vuex';
  import MiscHelper from '../../helpers/misc';
  import http from 'axios';
  import config from '../../config';
  
  export default {
    name: 'ContentPage', 
    mixins: [MiscHelper],
  
    created() {
      // this.fetchData();
      // this.fetchSettings();
      console.log("All is created");
    },
    
    data() {
      return {
        displayGenre: null,
        displayDate: null,
        displayRatings: null,
      }
    },
  
    computed: {
      ...mapState({
        // filters: state => state.filters,
        // showFilters: state => state.showFilters,
        // loading: state => state.loading,
        // items: state => state.items,
        // userFilter: state => state.userFilter,
        // userSortDirection: state => state.userSortDirection,
        // clickedMoreLoading: state => state.clickedMoreLoading,
        // paginator: state => state.paginator
      }),
      filteredFilters() {
        return this.filters.filter(filter => filter !== this.userFilter);
      }
    },
  
    methods: {
      ...mapActions([ 'loadItems', 'loadMoreItems', 'setSearchTitle', 'setPageTitle' ]),
      ...mapMutations([ 'SET_USER_FILTER', 'SET_SHOW_FILTERS', 'SET_USER_SORT_DIRECTION' ]),
      
      /**
       * 获取路由信息
       */
      fetchData() {
        let name = this.$route.name;
        if(name){
          console.log("Current route name:", name);
          this.setTitle(name);
          this.loadItems({name});
          this.setSearchTitle('');
        }else{
          console.log("Route name is undefined");
        }
      },
      /**
       * 设置当前页面标题
       * @param {*} name 
       */
      setTitle(name) {
        switch(name) {
          case 'home':
            return this.setPageTitle();
          case 'tv':
          case 'movie':
          case 'watchlist':
            return this.setPageTitle(this.lang(name));
        }
      },
  
      fetchSettings() {
        // console.log("f3");
        http(`${config.api}/settings`).then(value => {
          const data = value.data;
  
          this.displayGenre = data.genre;
          this.displayDate = data.date;
          this.displayRatings = data.ratings;
        });
      },
  
      loadMore() {
        this.loadMoreItems(this.paginator);
      },
  
      toggleShowFilters() {
        this.SET_SHOW_FILTERS(!this.showFilters);
      },
  
      setUserFilter(filter) {
        this.SET_SHOW_FILTERS(false);
  
        localStorage.setItem('filter', filter);
        this.SET_USER_FILTER(filter);
        this.fetchData();
      },
  
      setUserSortDirection() {
        let newSort = this.userSortDirection === 'asc' ? 'desc' : 'asc';
  
        localStorage.setItem('sort-direction', newSort);
        this.SET_USER_SORT_DIRECTION(newSort);
        this.fetchData();
      }
    },
  
    components: {
        // MovieItem
    },
  
    watch: {
      $route() {
        this.fetchData();
      }
    }
  }
  </script>
  