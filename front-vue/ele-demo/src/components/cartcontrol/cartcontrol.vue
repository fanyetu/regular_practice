<!-- create by zhanghaonan 2017/6/5 -->
<template>
  <div class="cartcontrol">
    <transition name="move">
      <div class="cart-decrease"
           v-show="food.count > 0" @click="decreaseCart($event)">
        <div class="inner icon-remove_circle_outline"></div>
      </div>
    </transition>
    <div class="cart-count" v-show="food.count > 0">{{food.count}}</div>
    <div class="cart-increase icon-add_circle"
         @click="increaseCart($event)"></div>
  </div>
</template>
<script type="text/ecmascript-6">
  import Vue from 'vue';

  export default{
    props: {
      food: {
        type: Object
      }
    },
    methods: {
      decreaseCart(event){
        if (!event._constructed) {
          return;
        }
        if (this.food.count > 0) {
          this.food.count--;
        }
      },
      increaseCart(event){
        if (!event._constructed) {
          return;
        }
        if (!this.food.count) {
//          this.food.count = 1;
          //为vue内的属性新增属性时使用Vue.set方法，我们才可以实现双向数据绑定
          Vue.set(this.food, 'count', 1)
        } else {
          this.food.count++;
        }
        //触发一个自定义事件，向父组件传递当前点击的dom
        this.$emit('cart-add',event.target);
      }
    }
  }
</script>
<style lang="less">

  .cartcontrol {
    font-size: 0;
    .cart-decrease {
      display: inline-block;
      padding: 6px;
      transition: all 0.4s linear;
      &.move-leave,&.move-enter-active {
        opacity: 1;
        transform: translate3d(0, 0, 0);
        .inner{
          transition: all 0.4s linear;
          transform: rotate(0deg);
        }
      }
      &.move-enter,&.move-leave-active {
        opacity: 0;
        transform: translate3d(24px, 0, 0);
        .inner{
          transition: all 0.4s linear;
          transform: rotate(180deg);
        }
      }
      .inner {
        display: inline-block;
        font-size: 24px;
        line-height: 24px;
        color: rgb(0, 160, 220);
      }
    }
    .cart-count {
      display: inline-block;
      vertical-align: top;
      width: 12px;
      padding-top: 6px;
      line-height: 24px;
      text-align: center;
      font-size: 10px;
      color: rgb(147, 153, 159);
    }
    .cart-increase {
      display: inline-block;
      padding: 6px;
      font-size: 24px;
      line-height: 24px;
      color: rgb(0, 160, 220);
    }
  }
</style>
