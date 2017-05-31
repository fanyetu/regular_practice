<template>
  <div id="app">
    <vheader></vheader>
    <div class="tab">
      i am tab
    </div>
    <div class="content">
      i am content
    </div>
  </div>
</template>

<script>
  import vheader from './components/header/header.vue'

  export default {
    name: 'app',
    components: {
      vheader
    }
  }
</script>

<style>
</style>
