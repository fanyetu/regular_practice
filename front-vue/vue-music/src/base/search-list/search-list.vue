<!-- create by zhanghaonan 2017/8/26 -->
<template>
  <div class="search-list" v-show="searches.length">
    <ul>
      <li class="search-item" v-for="item in searches"
          @click="selectItem(item)">
        <span class="text">{{item}}</span>
        <span class="icon" @click.stop="deleteOne(item)"><!--stop阻止事件冒泡-->
          <i class="icon-delete"></i>
        </span>
      </li>
    </ul>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    props: {
      searches: {
        type: Array,
        default: []
      }
    },
    methods: {
      selectItem(item) {
        this.$emit('select', item)
      },
      deleteOne(item) {
        this.$emit('delete', item)
      }
    }
  }
</script>
<style lang="stylus">
  @import "~common/stylus/variable"

  .search-list
    .search-item
      display flex
      align-items center
      height 40px
      .text
        flex 1
        color $color-text-l
      .icon
        extend-click()
        .icon-delete
          font-size $font-size-small
          color $color-text-d

  /*.test*/
</style>
