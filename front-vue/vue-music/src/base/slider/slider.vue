<!-- create by zhanghaonan 2017/7/25 -->
<template>
  <div class="slider" ref="slider">
    <div class="slider-group" ref="sliderGroup">
      <!--vue的插槽-->
      <slot></slot>
    </div>
    <div class="dots"></div>
  </div>
</template>
<script type="text/ecmascript-6">
  import BScroll from 'better-scroll'
  import {addClass} from 'common/js/dom'

  export default {
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
      }, 20)
    },
    methods: {
      // 计算slider的宽度
      _setSliderWidth() {
        let childs = this.$refs.sliderGroup.children // 获取sliderGroup的子元素们

        let width = 0 // sliderGroup的width
        let sliderWidth = this.$refs.slider.clientWidth // slider的width

        for (let i = 0; i < childs.length; i++) {
          let child = childs[i]
          addClass(child, 'slider-item') // 为所有的slider子元素添加slider-item class

          child.style.width = sliderWidth + 'px' // 设置每个子元素的宽度
          width += sliderWidth
        }

        // 如果是循环模式，那么在slider的左右还要复制一个元素，所以添加两倍sliderWidth
        if (this.loop) {
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
          snapSpeed: 400,
          click: true
        })
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
</style>
