<template>
  <div id="app">
    <vheader></vheader>
    <div class="tab">
      <div class="tab-item">
        <router-link to="/goods">商品</router-link>
      </div>
      <div class="tab-item">
        <router-link to="/ratings">评论</router-link>
      </div>
      <div class="tab-item">
        <router-link to="/seller">商家</router-link>
      </div>
    </div>
    <router-view></router-view>
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

<style lang="less" rel="stylesheet/less">
  /**
  使用flex布局(盒子模型)
   */
  .tab {
    //postcss插件自动添加兼容性写法
    display: flex;
    width: 100%;
    height: 40px;
    line-height: 40px;
    .tab-item {
      flex: 1;
      text-align: center;
    }
  }
</style>
