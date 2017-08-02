<!-- create by zhanghaonan 2017/8/1 -->
<template>
  <transition name="slide">
    <div class="singer-detail">
    </div>
  </transition>
</template>
<script type="text/ecmascript-6">
  import {mapGetters} from 'vuex'
  import {getSingerDetail} from 'api/singer'
  import {ERR_OK} from 'api/config'

  export default {
    data() {
      return {
        songs: []
      }
    },
    computed: {
      ...mapGetters([
        'singer' // 将vuex中的getter映射到计算属性中
      ])
    },
    created() {
      this._getDetail()
      console.log(this.singer)
    },
    methods: {
      _getDetail() {
        if (!this.singer.id) { // 如果从vuex中没有获取到singer，那么就直接回退到singer
          this.$router.push('/singer')
        }
        getSingerDetail(this.singer.id).then((resp) => {
          if (resp.code === ERR_OK) {
            console.log(resp.data.list)
          }
        })
      },
      _normalizeSongs(list) {

      }
    }
  }
</script>
<style lang="stylus">
  @import "~common/stylus/variable"

  .singer-detail
    position fixed
    z-index 100
    top 0
    right 0
    bottom 0
    left 0
    background $color-background

  .slide-enter-active, .slide-leave-active
    transition all 0.3s

  .slide-enter, .slide-leave-to
    transform translate3d(100%, 0, 0)

  /*.test*/
</style>
