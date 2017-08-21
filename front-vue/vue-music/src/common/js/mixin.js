import {mapGetters} from 'vuex'

/**
 * vue提供的mixin特性，有点类似与aop，一处编写，添加到多个组件
 *
 * Created by zhanghaonan on 2017/8/17.
 */
export const playListMixin = {
  computed: {
    ...mapGetters([
      'playList'
    ])
  },
  mounted() {
    this.handlePlayList(this.playList)
  },
  activated() {
    this.handlePlayList(this.playList)
  },
  watch: {
    playList(newVal) {
      this.handlePlayList(newVal)
    }
  },
  methods: {
    // 组件自己去实现
    handlePlayList() {
      throw new Error('component must implement handlePlayList method')
    }
  }
}
