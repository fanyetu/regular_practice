<!-- create by zhanghaonan 2017/8/9 -->
<template>
  <!--progress-bar通用组件-->
  <div class="progress-bar" ref="progressBar"
    @click="progressClick">
    <div class="bar-inner">
      <div class="progress" ref="progress"></div>
      <div class="progress-btn-wrapper" ref="progressBtn"
           @touchstart.pervent="touchStart" @touchmove.pervent="touchMove"
           @touchend.pervent="touchEnd">
        <div class="progress-btn"></div>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  import {prefixStyle} from 'common/js/dom'

  const transform = prefixStyle('transform')
  const progressBtnWidth = 16

  export default {
    props: {
      // 定义播放比例的属性
      percent: {
        type: Number,
        default: 0
      }
    },
    created() {
      this.touch = {}
    },
    methods: {
      // 点击progressBar的时候跳转
      progressClick(e){
        let rect = this.$refs.progressBar.getBoundingClientRect()
        const offsetWidth = e.pageX - rect.left
        this._offset(offsetWidth)
//        this._offset(e.offsetX)
        this._triggerPercent()
      },
      touchStart(e) {
        this.touch.initiated = true
        this.touch.startX = e.touches[0].pageX
        this.touch.left = this.$refs.progress.clientWidth
      },
      touchMove(e) {
        if (!this.touch.initiated) {
          return
        }
        let deltaX = e.touches[0].pageX - this.touch.startX
        let offset = Math.min(this.$refs.progressBar.clientWidth - progressBtnWidth
          , Math.max(deltaX + this.touch.left))
        this._offset(offset)
      },
      touchEnd(e) {
        this.touch.initiated = false
        this._triggerPercent()
      },
      // 向外派发事件
      _triggerPercent() {
        let barWidth = this.$refs.progressBar.clientWidth - progressBtnWidth
        let percent = this.$refs.progress.clientWidth / barWidth
        this.$emit('percentChange', percent)// 派发事件
      },
      _offset(offset) {
        this.$refs.progress.style.width = `${offset}px`
        this.$refs.progressBtn.style[transform] = `translate3d(${offset}px,0,0)`
      }
    },
    watch: {
      // 监听percent的变化
      percent(newPercent) {
        if (newPercent > 0 && !this.touch.initiated) {
          let barWidth = this.$refs.progressBar.clientWidth - progressBtnWidth
          let offsetWidth = barWidth * this.percent
          this._offset(offsetWidth)
        }
      }
    }
  }
</script>
<style lang="stylus">
  @import "~common/stylus/variable"

  .progress-bar
    height 30px
    .bar-inner
      position relative
      height 4px
      top 13px
      background rgba(0, 0, 0, 0.3)
      .progress
        position absolute
        height 100%
        background $color-theme
      .progress-btn-wrapper
        position: absolute
        left: -8px
        top: -13px
        width: 30px
        height: 30px
        .progress-btn
          position relative
          top 7px
          left 7px
          box-sizing border-box
          height 16px
          width 16px
          border 3px solid rgb(255, 255, 255)
          border-radius 50%
          background $color-theme

  /*.test*/

</style>
