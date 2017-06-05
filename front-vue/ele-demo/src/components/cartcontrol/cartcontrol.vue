<!-- create by zhanghaonan 2017/6/5 -->
<template>
  <div class="cartcontrol">
    <div class="cart-decrease" v-show="food.count > 0"></div>
    <div class="cart-count" v-show="food.count > 0">{{food.count}}</div>
    <div class="cart-increase"></div>
  </div>
</template>
<script type="text/ecmascript-6">
  export default{
    props:{
      food:{
        type:Object
      }
    },
    created(){
      console.log(this.food);
    }
  }
</script>
<style lang="less">

  .cartcontrol{
    font-size: 0;
    .cart-decrease{
      display: inline-block;
    }
    .cart-count{
      display: inline-block;
    }
    .cart-increase{
      display: inline-block;
    }
  }
</style>
