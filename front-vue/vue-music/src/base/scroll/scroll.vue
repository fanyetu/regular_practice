<!-- create by zhanghaonan 2017/7/29 -->
<template>
  <div ref="wrapper">
    <!--放一个slot插槽-->
    <slot></slot>
  </div>
</template>
<script type="text/ecmascript-6">
  import BScroll from 'better-scroll'

  /*封装scroll组件*/
  export default {
    props: {
      /*
      1 会截流,只有在滚动结束的时候派发一个 scroll 事件。2在手指 move 的时候也会实时派发 scroll 事件，不会截流。
      3除了手指 move 的时候派发scroll事件，在 swipe（手指迅速滑动一小段距离）的情况下，列表会有一个长距离的滚动动画，
      这个滚动的动画过程中也会实时派发滚动事件
       */
      probeType: {
        type: Number,
        default: 1
      },
      click: {
        type: Boolean,
        default: true
      },
      data: {
        type: Array,
        default: null
      },
      listenScroll: { // 是否监听滚动事件
        type: Boolean,
        default: false
      },
      pullup: { // 是否开启上拉刷新
        type: Boolean,
        default: false
      },
      beforeScroll: { // 是否派发beforeScroll事件
        type: Boolean,
        default: false
      }
    },
    mounted() {
      setTimeout(() => {
        this._initScroll()
      }, 20)
    },
    methods: {
      _initScroll() {
        if (!this.$refs.wrapper) {
          return
        }
        this.scroll = new BScroll(this.$refs.wrapper, {
          probeType: this.probeType,
          click: this.click
        })
        if (this.listenScroll) {
          let me = this
          this.scroll.on('scroll', (pos) => {
            me.$emit('scroll', pos)// 派发scroll事件
          })
        }

        // 如果开启上拉刷新
        if (this.pullup) {
          // 监听bs的scrollEnd事件
          this.scroll.on('scrollEnd', () => {
            // 如果滚动结束时接近底部了（缓冲50px）
            if (this.scroll.y <= (this.scroll.maxScrollY) + 50) {
              this.$emit('scrollToEnd') // 派发scrollToEnd事件
            }
          })
        }

        if (this.beforeScroll) { // 开始滚动时
          this.scroll.on('beforeScrollStart', () => {
            this.$emit('beforeScroll')
          })
        }
      },
      enable() {
        // 代理better-scroll的方法,如果还要代理更多的方法就添加
        this.scroll && this.scroll.enable()
      },
      disable() {
        this.scroll && this.scroll.disable()
      },
      refresh() {
        this.scroll && this.scroll.refresh()
      },
      scrollTo() {
        this.scroll && this.scroll.scrollTo.apply(this.scroll, arguments)
      },
      scrollToElement() {
        this.scroll && this.scroll.scrollToElement.apply(this.scroll, arguments)
      }
    },
    watch: {
      /*监控data属性的变化，如果变化了就刷新scroll*/
      data: function () { // vue官方建议这里不要使用箭头函数，因为箭头函数的作用域不同
        setTimeout(() => {
          this.refresh()
        }, 20)
      }
    }
  }
</script>
<style lang="stylus">
</style>
