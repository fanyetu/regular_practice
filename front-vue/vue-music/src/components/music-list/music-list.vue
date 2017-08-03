<!-- create by zhanghaonan 2017/8/3 -->
<template>
  <div class="music-list">
    <div class="back">
      <i class="icon-back"></i>
    </div>
    <h1 class="title" v-html="title"></h1>
    <div class="bg-image" :style="bgStyle" ref="bgImage">
      <div class="filter"></div>
    </div>
    <div class="bg-layer" ref="layer"></div><!--跟随scroll移动的层-->
    <scroll :data="songs" class="list" ref="list">
      <div class="song-list-wrapper"><!--控制内部样式-->
        <song-list :songs="songs"></song-list>
      </div>
    </scroll>
  </div>
</template>
<script type="text/ecmascript-6">
  import Scroll from 'base/scroll/scroll'
  import SongList from 'base/song-list/song-list'

  export default {
    props: {
      title: { // 顶部title
        type: String,
        default: ''
      },
      songs: { // 歌曲列表
        type: Array,
        default: []
      },
      bgImage: { // 背景图片
        type: String,
        default: ''
      }
    },
    mounted() {
      this.$refs.list.$el.style.top = `${this.$refs.bgImage.clientHeight}px`
    },
    computed: {
      bgStyle() {
        return `background-image:url(${this.bgImage})`
      }
    },
    components: {
      Scroll,
      SongList
    }
  }
</script>
<style lang="stylus">
  @import "~common/stylus/variable"
  @import "~common/stylus/mixin"

  .music-list
    position fixed
    z-index 100
    top 0
    left 0
    right 0
    bottom 0
    background $color-background
    .back
      position absolute
      top 0
      left 6px
      z-index 50
      .icon-back
        display block
        padding 10px
        font-size $font-size-large-x
        color $color-theme
    .title
      position absolute
      top 0
      left 10%
      z-index 40
      width 80%
      no-wrap()
      text-align center
      line-height 40px
      font-size $font-size-large
      color $color-text
    .bg-image
      position relative
      width 100%
      height 0
      padding-top 70% // 设置width为100%，height为0，这时候设置padding-top，高度就是width的70%，宽高10：7
      transform-origin top // 设置元素的旋转基点
      background-size cover
    .list
      position fixed
      top 0
      bottom 0
      width 100%
      /*overflow hidden*/
      background $color-background
      .song-list-wrapper
        padding 20px 30px
  /*.test*/
</style>
