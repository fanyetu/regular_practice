<!-- create by zhanghaonan 2017/8/3 -->
<template>
  <div class="music-list">
    <div class="back" @click="back">
      <i class="icon-back"></i>
    </div>
    <h1 class="title" v-html="title"></h1>
    <div class="bg-image" :style="bgStyle" ref="bgImage">
      <div class="play-wrapper">
        <div class="play" v-show="songs.length > 0" ref="playButton"
             @click="random">
          <i class="icon-play"></i>
          <span class="text">随机播放全部</span>
        </div>
      </div>
      <div class="filter" ref="filter"></div><!--为图片加上模糊的层-->
    </div>
    <div class="bg-layer" ref="layer"></div><!--跟随scroll移动的层-->
    <!--监听scroll组件的scroll事件-->
    <scroll :data="songs" class="list" ref="list" @scroll="scroll"
            :probe-type="probeType" :listen-scroll="listenScroll">
      <div class="song-list-wrapper"><!--控制内部样式-->
        <!--监听song-list组件的select事件-->
        <song-list :songs="songs" @select="selectItem"></song-list>
      </div>
      <div class="loading-container" v-show="!songs.length">
        <loading></loading>
      </div>
    </scroll>
  </div>
</template>
<script type="text/ecmascript-6">
  import Scroll from 'base/scroll/scroll'
  import SongList from 'base/song-list/song-list'
  import {prefixStyle} from 'common/js/dom'
  import Loading from 'base/loading/loading'
  import {mapActions} from 'vuex'

  const RESERVED_HEIGHT = 40
  const transform = prefixStyle('transform')
  const backdrop = prefixStyle('backdrop-filter')

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
    data() {
      return {
        scrollY: 0
      }
    },
    created() {
      this.probeType = 3
      this.listenScroll = true
    },
    mounted() {
      this.imageHeight = this.$refs.bgImage.clientHeight
      this.minTranslateY = -this.imageHeight + RESERVED_HEIGHT
      this.$refs.list.$el.style.top = `${this.imageHeight}px`
    },
    computed: {
      bgStyle() {
        return `background-image:url(${this.bgImage})`
      }
    },
    methods: {
      ...mapActions([
        'selectPlay',
        'randomPlay'
      ]),
      random() {
        this.randomPlay({list: this.songs})
      },
      scroll(pos) {
        this.scrollY = pos.y
      },
      back() {
        this.$router.back()
      },
      selectItem(item, index) {
        this.selectPlay({ // 调用vuex的action
          list: this.songs,
          index: index
        })
      }
    },
    watch: {
      // 观察scrollY的变化
      scrollY: function (newY) {
        let translateY = Math.max(this.minTranslateY, newY)
        let zIndex = 0
        let scale = 1
        let blur = 0 // 图片模糊

        // 动态改变layer层的transform属性
        this.$refs.layer.style[transform] = `translate3d(0,${translateY}px,0)`

        const persent = Math.abs(newY / this.imageHeight)
        if (newY > 0) { // 如果是向下滚动
          scale = 1 + persent
          zIndex = 10
        } else {
          blur = Math.min(20 * persent, 20)
        }
        // 添加图片的模糊，只有苹果手机才有效果
        this.$refs.filter.style[backdrop] = `blur(${blur}px)`

        if (newY < this.minTranslateY) {
          zIndex = 10
          this.$refs.bgImage.style['paddingTop'] = 0
          this.$refs.bgImage.style['height'] = `${RESERVED_HEIGHT}px`
          this.$refs.playButton.style.display = 'none'
        } else {
          this.$refs.bgImage.style['paddingTop'] = '70%'
          this.$refs.bgImage.style['height'] = 0
          this.$refs.playButton.style.display = ''
        }
        this.$refs.bgImage.style['zIndex'] = zIndex
        this.$refs.bgImage.style[transform] = `scale(${scale})`
      }
    },
    components: {
      Scroll,
      SongList,
      Loading
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
    .bg-layer
      position relative
      height 100%
      background $color-background
    .bg-image
      position relative
      width 100%
      height 0
      padding-top 70% // 设置width为100%，height为0，这时候设置padding-top，高度就是width的70%，宽高10：7
      transform-origin top // 设置元素的旋转基点
      background-size cover
      .filter
        position absolute
        top 0
        left 0
        height 100%
        width 100%
        background rgba(7, 17, 27, 0.4)
      .play-wrapper
        position absolute
        bottom 20px
        z-index 50
        width 100%
        .play
          box-sizing border-box
          width 135px
          padding 7px 0
          margin 0 auto
          text-align center
          border 1px solid $color-theme
          color $color-theme
          border-radius 100px
          .icon-play
            display inline-block
            vertical-align middle
            margin-right 6px
            font-size $font-size-medium-x
          .text
            display inline-block
            vertical-align middle
            font-size $font-size-small
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
