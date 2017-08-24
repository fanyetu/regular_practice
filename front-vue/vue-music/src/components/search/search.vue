<!-- create by zhanghaonan 2017/7/23 -->
<template>
  <div class="search">
    <div class="search-box-wrapper">
      <search-box ref="searchBox" @query="onQueryChange"></search-box>
    </div>
    <div class="shortcut-wrapper" v-show="!query">
      <div class="shortcut">
        <div class="hot-key">
          <h1 class="title">热门搜索</h1>
          <ul>
            <li v-for="item in hotKey" class="item" @click="addQuery(item.k)">
              <span>{{item.k}}</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="search-result" v-show="query">
      <suggest :query="query" @listScroll="blurInput"></suggest>
    </div>
    <router-view></router-view>
  </div>
</template>
<script type="text/ecmascript-6">
  import SearchBox from 'base/search-box/search-box'
  import {getHotKey} from 'api/search'
  import {ERR_OK} from 'api/config'
  import Suggest from 'components/suggest/suggest'

  export default {
    created() {
      this._getHotKey()
    },
    data() {
      return {
        hotKey: [],
        query: ''
      }
    },
    methods: {
      // 将input失去焦点
      blurInput(){
        this.$refs.searchBox.blur()
      },
      onQueryChange(query) {
        this.query = query
      },
      addQuery(query) {
        this.$refs.searchBox.setQuery(query)
      },
      _getHotKey() {
        getHotKey().then((res) => {
          if (res.code === ERR_OK) {
            this.hotKey = res.data.hotkey.slice(0, 10) // 截取array
          }
        })
      }
    },
    components: {
      SearchBox,
      Suggest
    }
  }
</script>
<style lang="stylus">
  @import "~common/stylus/variable"

  .search
    .search-box-wrapper
      margin: 20px
    .shortcut-wrapper
      position fixed
      top 178px
      bottom 0
      width 100%
      .shortcut
        height 100%
        overflow hidden
        .hot-key
          margin 0 20px 20px 20px
          .title
            margin-bottom 20px
            font-size $font-size-medium
            color $color-text-l
          .item
            display inline-block
            padding 5px 10px
            margin 0 20px 10px 0
            border-radius 6px
            background: $color-highlight-background
            font-size: $font-size-medium
            color: $color-text-d
    .search-result
      position: fixed
      width: 100%
      top: 178px
      bottom: 0
  /*.test*/
</style>
