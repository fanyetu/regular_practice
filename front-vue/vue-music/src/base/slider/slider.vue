<!-- create by zhanghaonan 2017/7/25 -->
<template>
  <div class="slider" ref="slider">
    <div class="slider-group" ref="sliderGroup">
      <!--vue的插槽-->
      <slot></slot>
    </div>
    <div class="dots">
      <span class="dot" v-for="item,index in dots" :class="{active : currentIndex===index}"></span>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  import BScroll from 'better-scroll'
  import {addClass} from 'common/js/dom'

  export default {
    data() {
      return {
        dots: [],
        currentIndex: 0
      }
    },
    props: {
      // slider组件的对外属性
      loop: { // 是否循环
        type: Boolean,
        default: true
      },
      autoPlay: { // 是否自动播放
        type: Boolean,
        default: true
      },
      interval: { // 自动播放时间间隔
        type: Number,
        default: 4000
      }
    },
    mounted() {
      // 本来应该是使用vue提供的$nextTick，但是老师推荐使用setTimeout
      // 至于为什么是20毫秒，浏览器刷新是17毫秒一次，20毫秒是一个经验值
      setTimeout(() => {
        this._setSliderWidth()
        this._initSlider()
        this._initDots()

        // 如果是自动播放，那么开始播放
        if (this.autoPlay) {
          this._play()
        }
      }, 20)

      // 监听窗口的resize事件，保证在窗口大小变化的时候slider重新计算宽度
      window.addEventListener('resize', () => {
        if (!this.slider) {
          return
        }

        this._setSliderWidth(true)
        this.slider.refresh()
      })
    },
    // 当keep-alive激活的时候又重新播放
    activated() {
      if (this.autoPlay) {
        this._play()
      }
    },
    // 当keep-alive停用的时候清除timer
    deactivated() {
      clearTimeout(this.timer)
    },
    methods: {
      // 计算slider的宽度
      _setSliderWidth(isResize) {
        this.childs = this.$refs.sliderGroup.children // 获取sliderGroup的子元素们
        // 为了保证获取到的元素个数为正确的，直接使用childs.length时在betterscroll初始化之后会改变
        this.childsLength = this.childs.length

        let width = 0 // sliderGroup的width
        let sliderWidth = this.$refs.slider.clientWidth // slider的width

        for (let i = 0; i < this.childs.length; i++) {
          let child = this.childs[i]
          addClass(child, 'slider-item') // 为所有的slider子元素添加slider-item class

          child.style.width = sliderWidth + 'px' // 设置每个子元素的宽度
          width += sliderWidth
        }

        // 如果是循环模式，那么在slider的左右还要复制一个元素，所以添加两倍sliderWidth
        if (this.loop && !isResize) {
          width += 2 * sliderWidth
        }

        this.$refs.sliderGroup.style.width = width + 'px'
      },
      _initSlider() {

        // 使用better-scroll初始化轮播
        this.slider = new BScroll(this.$refs.slider, {
          scrollX: true,
          scrollY: false,
          momentum: false,
          snap: true,
          snapLoop: this.loop,
          snapThreshold: 0.3,
          snapSpeed: 400
        })

        // 监听scroll的end事件，better scroll滚动一张图片后会派发end事件
        this.slider.on('scrollEnd', () => {
          let pageIndex = this.slider.currentPage.pageX // 获取当前滚动到的页面索引
          if (this.loop) { // 当为loop模式的时候，better-scroll会在前后加一个元素，所以减1
            pageIndex -= 1
          }
          this.currentIndex = pageIndex

          // 如果是自动播放，那么还要清除timer后再播放一次
          if (this.autoPlay) {
            clearTimeout(this.timer)
            this._play()
          }
        })

      },
      _initDots() {
        // 初始化轮播下方的dots，只是一个固定大小的空数组
        this.dots = new Array(this.childsLength)
      },
      _play() {
        // 获取下一页的索引
        let nextIndex = this.currentIndex + 1
        if (this.loop) {
          nextIndex += 1 // 如果是loop模式，前面会加一个元素，所以加1
        }
        this.timer = setTimeout(() => {
          this.slider.goToPage(nextIndex, 0, 400) // 使用better-scroll的goToPage方法
        }, this.interval)
      }
    }
  }
</script>
<style lang="stylus">
  @import "~common/stylus/variable"

  .slider
    min-height 1px
    .slider-group
      position relative
      overflow hidden
      white-space nowrap
      .slider-item
        float left
        box-sizing border-box
        overflow hidden
        text-align center
        a
          display block
          width 100%
          overflow hidden
          text-decoration none
        img
          display block
          width 100%
    .dots
      position absolute
      right 0
      left 0
      bottom 12px
      text-align center
      font-size 0
      .dot
        display inline-block
        margin 0 4px
        width 8px
        height 8px
        border-radius 50%
        background $color-text-l
        &.active
          width 20px
          border-radius 5px
          background $color-text-ll
</style>
