<template>
  <div
    id="app"
    class="centerAll">
    <Home />
  </div>
</template>

<script>
  import Home from './views/Home.vue'
  export default {
    name: 'Home2',
    components: {Home},
    data: () => ({
    }),
  }
</script>

<style>
.centerAll {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
