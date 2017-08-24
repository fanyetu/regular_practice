<!-- create by zhanghaonan 2017/8/22 -->
<template>
  <div class="search-box">
    <i class="icon-search"></i>
    <input class="box" :placeholder="placeholder" ref="query"
           v-model="query"/><!--使用v-model实现双向数据绑定-->
    <i class="icon-dismiss" v-show="query" @click="clear"></i>
  </div>
</template>
<script type="text/ecmascript-6">
  import {debounce} from 'common/js/util'

  export default {
    props: {
      placeholder: {
        type: String,
        default: '搜索歌曲、歌手'
      }
    },
    data() {
      return {
        query: ''
      }
    },
    methods: {
      blur(){
        this.$refs.query.blur()
      },
      clear() {
        this.query = ''
      },
      setQuery(query) {
        this.query = query
      }
    },
    created() {
      // 监听query变化，派发query事件
      this.$watch('query', debounce((newQuery) => {
        this.$emit('query', newQuery)
      }, 200)) // 对query事件的派发动作进行节流
    }
  }
</script>
<style lang="stylus">
  @import "~common/stylus/variable"

  .search-box
    display flex
    align-items center
    box-sizing border-box
    width 100%
    padding 0 6px
    height 40px
    background: $color-highlight-background
    border-radius: 6px
    .icon-search
      font-size 24px
      color: $color-background
    .box
      flex: 1
      margin: 0 5px
      line-height: 18px
      background: $color-highlight-background
      color: $color-text
      font-size: $font-size-medium
      &::placeholder
        color: $color-text-d
    .icon-dismiss
      font-size: 16px
      color: $color-background

  /*.test*/
</style>
