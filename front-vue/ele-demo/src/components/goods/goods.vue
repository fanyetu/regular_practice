<!-- create by zhanghaonan 2017/6/1 -->
<template>
  <div class="goods">
    <div class="menu-wrapper">
      <ul>
        <li v-for="item in goods" class="menu-item">
          <span class="text">
            <span v-show="item.type>0" class="icon" :class="classMap[item.type]"></span>
            {{item.name}}
          </span>
        </li>
      </ul>
    </div>
    <div class="foods-wrapper"></div>
  </div>
</template>
<script type="text/ecmascript-6">
  const ERR_OK = 0;

  export default{
    props: {
      seller: {
        type: Object
      }
    },
    data(){
      return {
        goods: {}
      }
    },
    created(){
      //获取商品列表
      this.$http.get('/api/goods').then((resp) => {
        resp = resp.body;//通过body属性取到返回的内容
        if (resp.errno === ERR_OK) {
          this.goods = resp.data;
        }
      });

      this.classMap = ["decrease", "discount", "special", "invoice", "guarantee"];
    }
  }
</script>
<style lang="less">
  @import "../../common/css/mixin";

  @path: '../../components/goods/';

  .goods {
    position: absolute;
    top: 174px;
    bottom: 46px;
    width: 100%;
    display: flex;
    overflow: hidden;
    .menu-wrapper {
      flex: 0 0 80px;
      width: 80px;
      background-color: #f3f5f7;
      .menu-item {
        display: table;
        height: 54px;
        width: 56px;
        line-height: 14px;
        padding: 0 12px;
        .icon {
          display: inline-block;
          vertical-align: top;
          height: 12px;
          width: 12px;
          margin-right: 2px;
          background-size: 12px 12px;
          background-repeat: no-repeat;
          &.decrease {
            .bg-img("@{path}decrease_3");
          }
          &.discount {
            .bg-img("@{path}discount_3");
          }
          &.guarantee {
            .bg-img("@{path}guarantee_3");
          }
          &.invoice {
            .bg-img("@{path}invoice_3");
          }
          &.special {
            .bg-img("@{path}special_3");
          }
        }
        .text {
          display: table-cell;
          width: 56px;
          vertical-align: middle;
          font-size: 12px;
          .bottom-border-1px(rgba(7,17,27,0.1))
        }
      }
    }
    .foods-wrapper {
      flex: 1;
    }
  }
</style>
