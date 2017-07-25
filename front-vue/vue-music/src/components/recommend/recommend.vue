<!-- create by zhanghaonan 2017/7/23 -->
<template>
  <div class="recommend">
    <div class="recommend-content">
      <!--使用v-if，当recommends不为空的时候才渲染slider-->
      <div v-if="recommends.length" class="slider-wrapper">
        <slider>
          <div v-for="item in recommends">
            <a :href="item.linkUrl">
              <img :src="item.picUrl"/>
            </a>
          </div>
        </slider>
      </div>
      <div class="recommend-list">
        <h1 class="list-title">热门歌单推荐</h1>
        <ul></ul>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  import {getRecommend} from 'api/recommend'
  import {ERR_OK} from 'api/config'
  import Slider from 'base/slider/slider'

  export default {
    created() {
      this._getRecommend()
    },
    data() {
      return {
        recommends: []
      }
    },
    components: {
      Slider
    },
    methods: {
      _getRecommend() {
        // 通过封装的jsonp抓取qq音乐的数据
        getRecommend().then((resp) => {
          if (resp.code === ERR_OK) {
            this.recommends = resp.data.slider
          }
        })
      }
    }
  }
</script>
<style lang="stylus">
  @import "~common/stylus/variable"

  .recommend
    position fixed
    width 100%
    top 88px
    bottom 0
    .recommend-content
      height 100%
      overflow hidden
      .slider-wrapper
        position relative
        width 100%
        overflow hidden
      .recommend-list
        .list-title
          height 65px
          line-height 65px
          text-align center
          font-size $font-size-medium
          color $color-theme
</style>
