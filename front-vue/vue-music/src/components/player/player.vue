<!-- create by zhanghaonan 2017/8/6 -->
<template>
  <div class="player" v-show="playList.length > 0">
    <transition name="normal" @enter="enter"
                @after-enter="afterEnter" @leave="leave" @after-leave="afterLeave">
      <div class="normal-player" v-show="fullScreen">
        <div class="background">
          <img width="100%" height="100%" :src="currentSong.image"/>
        </div>
        <div class="top">
          <div class="back" @click="back">
            <i class="icon-back"></i>
          </div>
          <h1 class="title" v-html="currentSong.name"></h1>
          <h2 class="subtitle" v-html="currentSong.singer"></h2>
        </div>
        <div class="middle">
          <div class="middle-l">
            <div class="cd-wrapper" ref="cdWrapper">
              <div class="cd" :class="cdClass">
                <img class="image" :src="currentSong.image"/>
              </div>
            </div>
          </div>
        </div>
        <div class="bottom">
          <div class="progress-wrapper">
            <span class="time time-l">{{formatTime(currentTime)}}</span>
            <div class="progress-bar-wrapper">
              <progress-bar :percent="percent" @percentChange="onProgressBarChange"></progress-bar>
            </div>
            <span class="time time-r">{{formatTime(currentSong.duration)}}</span>
          </div>
          <div class="operators">
            <div class="icon i-left">
              <i :class="iconMode" @click="changeMode"></i>
            </div>
            <div class="icon i-left" :class="disableClass">
              <i class="icon-prev" @click="prev"></i>
            </div>
            <div class="icon i-center" :class="disableClass">
              <i @click="togglePlaying" :class="playIcon"></i>
            </div>
            <div class="icon i-right" :class="disableClass">
              <i class="icon-next" @click="next"></i>
            </div>
            <div class="icon i-right">
              <i class="icon icon-not-favorite"></i>
            </div>
          </div>
        </div>
      </div>
    </transition>
    <transition name="mini">
      <div class="mini-player" v-show="!fullScreen" @click="open">
        <div class="icon">
          <img :class="cdClass" width="40" height="40" :src="currentSong.image"/>
        </div>
        <div class="text">
          <h2 class="name" v-html="currentSong.name"></h2>
          <p class="desc" v-html="currentSong.singer"></p>
        </div>
        <div class="control">
          <progress-circle :radius="radius" :percent="percent">
            <i :class="playMiniIcon" @click.stop="togglePlaying" class="icon-mini"></i>
          </progress-circle>
        </div>
        <div class="control">
          <i class="icon-playlist"></i>
        </div>
      </div>
    </transition>
    <!--监听audio的canplay事件和error事件，防止用户快速切换歌曲-->
    <!--监听audio的timeupdate事件，获取audio的currentTime-->
    <audio :src="currentSong.url" ref="audio" @canplay="ready" @error="error"
           @timeupdate="updateTime"></audio>
  </div>
</template>
<script type="text/ecmascript-6">
  import {mapGetters, mapMutations} from 'vuex'
  import animations from 'create-keyframe-animation' // 使用这个包实现js的css3动画
  import {prefixStyle} from 'common/js/dom'
  import ProgressBar from 'base/progress-bar/progress-bar'
  import ProgressCircle from 'base/progress-circle/progress-circle'
  import {playMode} from 'common/js/config'

  const transform = prefixStyle('transform')

  export default {
    data() {
      return {
        songReady: false,
        currentTime: 0,
        radius: 32
      }
    },
    components: {
      ProgressBar,
      ProgressCircle
    },
    computed: {
      iconMode() {
        return this.mode === playMode.sequence ? 'icon-sequence' : this.mode === playMode.loop ?
          'icon-loop' : 'icon-random'
      },
      cdClass() {
        return this.playing ? 'play' : 'play pause'
      },
      playIcon() {
        return this.playing ? 'icon-pause' : 'icon-play'
      },
      playMiniIcon() {
        return this.playing ? 'icon-pause-mini' : 'icon-play-mini'
      },
      disableClass() {
        return this.songReady ? '' : 'disable'
      },
      percent() {
        return this.currentTime / this.currentSong.duration
      },
      ...mapGetters([
        'fullScreen',
        'playList',
        'currentSong',
        'playing',
        'currentIndex',
        'mode'
      ])
    },
    methods: {
      changeMode() {
        let mode = (this.mode + 1) % 3
        this.setMode(mode)
      },
      // 监听progressbar的percentchange事件
      onProgressBarChange(percent) {
        this.$refs.audio.currentTime = this.currentSong.duration * percent
      },
      updateTime(e) {
        this.currentTime = e.target.currentTime // audio标签有一个currentTime属性，是可读写的，表示当前播放时间
      },
      ready() {
        this.songReady = true
      },
      error() {
        this.songReady = true
      },
      prev() {
        if (!this.songReady) {
          return
        }
        let index = this.currentIndex - 1
        if (index === -1) {
          index = this.playList.length - 1
        }
        this.setCurrentIndex(index)
        if (!this.playing) {
          this.togglePlaying()
        }
        this.songReady = false
      },
      next() {
        if (!this.songReady) { // 如果当前歌曲还没有准备完成就直接return
          return
        }
        let index = this.currentIndex + 1
        if (index === this.playList.length) {
          index = 0
        }
        this.setCurrentIndex(index)
        if (!this.playing) {
          this.togglePlaying()
        }
        this.songReady = false
      },
      togglePlaying() {
        if (!this.songReady) {
          return
        }
        this.setPlaying(!this.playing) // 修改当前的playing状态
      },
      back() {
        this.setFullScreen(false) // 设置为不是全屏
      },
      open() {
        this.setFullScreen(true) // 设置全屏
      },
      // vue transition提供的enter事件
      enter(el, done) {
        const {x, y, scale} = this._getPosAndScale()

        // 创建动画
        let animation = {
          0: {
            transform: `translate3d(${x}px,${y}px,0) scale(${scale})`
          },
          60: {
            transform: `translate3d(0,0,0) scale(1.1)`
          },
          100: {
            transform: `translate3d(0,0,0) scale(1)`
          }
        }

        // 注册动画
        animations.registerAnimation({
          name: 'move',
          animation,
          presets: {
            duration: 400,
            easing: 'linear'
          }
        })

        // 执行动画，第一个参数是需要执行动画的dom，第二个参数是动画的名称，第三个参数的回调
        animations.runAnimation(this.$refs.cdWrapper, 'move', done) // 调用done完成transition的过程
      },
      afterEnter(el) {
        // 完成enter之后，清除动画
        animations.unregisterAnimation('move')
        this.$refs.cdWrapper.style.animation = ''
      },
      leave(el, done) {
        const {x, y, scale} = this._getPosAndScale()

        // 直接使用transition实现
        this.$refs.cdWrapper.style.transition = 'all 0.4s'
        this.$refs.cdWrapper.style[transform] = `translate3d(${x}px,${y}px,0) scale(${scale})`

        // 添加transitionend的监听事件，完成时调用done
        this.$refs.cdWrapper.addEventListener('transitionend', done)
      },
      afterLeave(el) {
        this.$refs.cdWrapper.style.transition = ''
        this.$refs.cdWrapper.style[transform] = ''
      },
      // 格式化currentTime
      formatTime(interval) {
        interval = interval | 0 // 向下取整
        let m = interval / 60 | 0
        let s = this._pad(interval % 60)
        return `${m}:${s}`
      },
      // 对数字进行补0操作
      _pad(num, n = 2) {
        let len = num.toString().length
        while (len < n) {
          num = '0' + num
          len++
        }
        return num
      },
      // 计算唱片动画的位置
      _getPosAndScale() {
        const targetWidth = 40 // 底部小唱片的宽度
        const paddingLeft = 20 + targetWidth / 2 // 底部小唱片的中心距left
        const paddingBottom = 10 + targetWidth / 2 // 底部小唱片的中心距bottom
        const sourceWidth = window.innerWidth * 0.8 // 中部大唱片的宽度
        const paddingTop = 80 + sourceWidth / 2 // 中部大唱片的中心距top
        const scale = targetWidth / sourceWidth // 底部小唱片的缩放比例
        const x = -(window.innerWidth / 2 - paddingLeft) // 中部移动到底部的x差值
        const y = window.innerHeight - paddingTop - paddingBottom // 中部移动到底部的y差值
        return {
          x, y, scale
        }
      },
      // 使用mutations修改fullScreen属性
      ...mapMutations({
        setFullScreen: 'SET_FULL_SCREEN',
        setPlaying: 'SET_PLAYING',
        setCurrentIndex: 'SET_CURRENT_INDEX',
        setMode: 'SET_MODE'
      })
    },
    watch: {
      currentSong: function () {
        this.$nextTick(() => {
          this.$refs.audio.play()
        })
      },
      playing: function (newPlaying) { // 监听vuex的playing状态
        const audio = this.$refs.audio
        this.$nextTick(() => {
          newPlaying ? audio.play() : audio.pause()
        })
      }
    }
  }
</script>
<style lang="stylus">
  @import "~common/stylus/variable"

  .player
    .normal-player
      position fixed
      top 0
      bottom 0
      left 0
      right 0
      z-index 150
      background $color-background
      .background
        position absolute
        left 0
        top 0
        width 100%
        height 100%
        z-index -1
        opacity 0.6
        filter blur(20px)
      .top
        position relative
        margin-bottom 25px
        .back
          position absolute
          top 0
          left 6px
          z-index 50
          .icon-back
            display block
            padding 9px
            font-size $font-size-large-x
            color $color-theme
            transform rotate(-90deg)
        .title
          width 70%
          margin 0 auto
          line-height 40px
          text-align center
          no-wrap()
          font-size $font-size-large
          color $color-text
        .subtitle
          line-height 20px
          text-align center
          font-size $font-size-medium
          color $color-text
      .middle
        position fixed
        width 100%
        top 80px
        bottom 170px
        white-space nowrap
        font-size 0
        .middle-l
          display inline-block
          vertical-align top
          position relative
          width 100%
          height 0
          padding-top 80%
          .cd-wrapper
            position absolute
            left 10%
            top 0
            width 80%
            height 100%
            .cd
              width 100%
              height 100%
              box-sizing border-box
              border 10px solid rgba(255, 255, 255, 0.1)
              border-radius 50%
              &.play
                animation rotate 20s linear infinite
              &.pause
                animation-play-state paused
              .image
                position absolute
                left 0
                top 0
                width 100%
                height 100%
                border-radius 50%

      .bottom
        position absolute
        bottom 50px
        width 100%
        .progress-wrapper
          display flex
          width 80%
          margin 0 auto
          align-items center
          padding 10px 0
          .time
            width 30px
            flex 0 0 30px
            color $color-text
            font-size $font-size-small
            line-height 30px
            &.time-l
              text-align left
            &.time-r
              text-align right
          .progress-bar-wrapper
            flex 1
        .operators
          display flex
          align-items center
          .icon
            flex 1
            color $color-theme
            &.disable
              color: $color-theme-d
            i
              font-size 30px
          .i-left
            text-align right
          .i-center
            padding 0 20px
            text-align center
          .i-right
            text-align left
          .icon-favorite
            color $color-sub-theme
      &.normal-enter-active, &.normal-leave-active
        transition all 0.4s
        .top, .bottom
          transition all 0.4s cubic-bezier(0.86, 0.18, 0.82, 1.32)
      &.normal-enter, &.normal-leave-to
        opacity 0
        .top
          transform translate3d(0, -100px, 0)
        .bottom
          transform translate3d(0, 100px, 0)

    .mini-player
      display flex
      align-items center
      position fixed
      left 0
      bottom 0
      z-index 180
      width 100%
      height 60px
      background $color-highlight-background
      .icon
        flex 0 0 40px
        width 40px
        padding 0 10px 0 20px
        img
          border-radius 50%
          &.play
            animation rotate 10s linear infinite
          &.pause
            animation-play-state paused
      .text
        display flex
        flex 1
        flex-direction column
        justify-content center
        line-height 20px
        overflow hidden
        .name
          margin-bottom 2px
          no-wrap()
          font-size $font-size-medium
          color $color-text
        .desc
          no-wrap()
          font-size $font-size-small
          color $color-text-d
      .control
        flex 0 0 30px
        width 30px
        padding 0 10px
        .icon-play-mini, .icon-pause-mini, .icon-playlist
          font-size 30px
          color $color-theme-d
        .icon-mini
          position absolute
          font-size 32px
          top 0
          left 0

      &.mini-enter-active, &.mini-leave-active
        transition all 0.4s
      &.mini-enter, &.mini-leave-to
        opacity 0

  @keyframes rotate
    0%
      transform rotate(0)
    100%
      transform rotate(360deg)

  /*.test*/
</style>
