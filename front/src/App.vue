<template>
  <router-view></router-view>
</template>

<script>



export default {
  name: 'App',
  components: {

  },
  watch: {
    $route(to, from) {
      document.title = "FLOX - " + to.meta.title || '默认标题';
    }
  },
  mounted() {
    document.title = "FLOX - " + this.$route.meta.title || '默认标题';
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
</style>
