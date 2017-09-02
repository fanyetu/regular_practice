import {mapGetters, mapMutations, mapActions} from 'vuex'
import {playMode} from "common/js/config"
import {shuffle} from 'common/js/util'

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

/**
 * 播放器相关mixin
 * @type {{computed: {iconMode: (function())}, methods: {changeMode: (function()), _resetCurrentIndex: (function(*))}}}
 */
export const playerMixin = {
  computed: {
    iconMode() {
      return this.mode === playMode.sequence ? 'icon-sequence' : this.mode === playMode.loop ?
        'icon-loop' : 'icon-random'
    },
    ...mapGetters([
      'sequenceList',
      'playList',
      'currentSong',
      'mode',
      'favoriteList'
    ])
  },
  methods: {
    // 收藏相关mixin
    getFavoriteIcon(song) {
      if (this.isFavorite(song)) {
        return 'icon-favorite'
      }
      return 'icon-not-favorite'
    },
    toggleFavorite(song) {
      if (this.isFavorite(song)) {
        this.deleteFavoriteList(song)
      } else {
        this.saveFavoriteList(song)
      }
    },
    isFavorite(song) {
      const index = this.favoriteList.findIndex((item) => {
        return item.id === song.id
      })
      return index > -1
    },
    // 切换播放模式
    changeMode() {
      let mode = (this.mode + 1) % 3
      this.setMode(mode)

      let list = null
      if (this.mode === playMode.random) {
        list = shuffle(this.sequenceList)
      } else {
        list = this.sequenceList
      }

      this._resetCurrentIndex(list)
      this.setPlayList(list)
    },
    // 当修改playList的时候，修改当前的currentIndex，达到不跳转歌曲的目的
    _resetCurrentIndex(list) {
      // findIndex是es6的函数
      let index = list.findIndex((item) => {
        return item.id === this.currentSong.id
      })

      this.setCurrentIndex(index)
    },
    ...mapMutations({
      setPlaying: 'SET_PLAYING',
      setCurrentIndex: 'SET_CURRENT_INDEX',
      setMode: 'SET_MODE',
      setPlayList: 'SET_PLAY_LIST'
    }),
    ...mapActions([
      'saveFavoriteList',
      'deleteFavoriteList'
    ])
  }
}

/**
 * 搜索相关mixin
 * @type {{computed: {}, data: (function()), methods: {saveSearch: (function()), blurInput: (function()), onQueryChange: (function(*)), addQuery: (function(*=))}}}
 */
export const searchMixin = {
  computed: {
    ...mapGetters([
      'searchHistory'
    ])
  },
  data() {
    return {
      query: '',
      refreshDelay: 100 // 解决transition-group的100毫秒动画之后scroll刷新不及时的问题
    }
  },
  methods: {
    saveSearch() {
      // 保存搜索结果
      this.saveSearchHistory(this.query)
    },
    // 将input失去焦点
    blurInput() {
      this.$refs.searchBox.blur()
    },
    onQueryChange(query) {
      this.query = query
    },
    addQuery(query) {
      this.$refs.searchBox.setQuery(query)
    },
    ...mapActions([
      'saveSearchHistory',
      'deleteSearchHistory'
    ])
  }
}
