<template>
  <div id="app">
    <vheader :seller="seller"></vheader>
    <div class="tab border-1px">
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
    <keep-alive>
      <router-view :seller="seller"></router-view>
    </keep-alive>
  </div>
</template>

<script>
  import vheader from 'components/header/header.vue'
  import {urlParse} from 'common/js/util';

  const ERR_OK = 0;

  export default {
    name: 'app',
    data(){
      return {
        seller: {
          id: (() => {
            let queryParam = urlParse();
            return queryParam.id;
          })()
        }
      }
    },
    created(){
      this.$http.get('/api/seller?id='+this.seller.id).then((resp) => {
        resp = resp.body;//通过body属性取到返回的内容
        if (resp.errno === ERR_OK) {
//          this.seller = resp.data
          this.seller = Object.assign({},this.seller,resp.data);
        }
      })
    },
    components: {
      vheader
    }
  }
</script>

<style lang="less" rel="stylesheet/less">

  @import "common/css/mixin"; //引入mixin样式

  /**
  使用flex布局(盒子模型)
   */
  .tab {
    //postcss插件自动添加兼容性写法
    display: flex;
    width: 100%;
    height: 40px;
    line-height: 40px;
    .bottom-border-1px(rgba(7, 17, 27, 0.1));
    .top-border-1px(rgba(7, 17, 27, 0.1));

    .tab-item {
      flex: 1;
      text-align: center;

      & > a {
        display: block;
        font-size: 14px;
        color: rgb(77, 85, 93);
        &.active {
          color: rgb(240, 20, 20);
        }
      }
    }
  }
</style>
