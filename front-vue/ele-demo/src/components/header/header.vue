<!-- create by zhanghaonan 2017/5/31 -->
<template>
  <div class="header">
    <div class="content-wrapper">
      <div class="avatar">
        <img :src="seller.avatar" width="64" height="64"/>
      </div>
      <div class="content">
        <div class="title">
          <span class="brand"></span>
          <span class="name">{{seller.name}}</span>
        </div>
        <div class="description">
          {{seller.description}}/{{seller.deliveryTime}}分钟送达
        </div>
        <div class="support" v-if="seller.supports">
          <span class=""></span>
          <span class="text">{{seller.supports[0].description}}</span>
        </div>
      </div>
    </div>
    <div class="bulletin-wrapper">

    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  export default{
    props: {
      seller: {
        type: Object
      }
    }
  }
</script>
<style lang="less">
  @import url("../../common/css/mixin");
  @path:"../../components/header/";//解决less引用文件相对路径问题,stylus没有这个问题

  .header{
    color: #fff;
    background-color: #000;
    .content-wrapper{
      padding: 24px 12px 18px 24px;
      font-size: 0;//将avatar和content中间的间隙取消，同时content的要设置font-size
      .avatar{
        display: inline-block;
        vertical-align: top;
        img{
          border-radius: 2px;
        }
      }
      .content{
        display: inline-block;
        margin-left: 16px;
        /*font-size: 14px;*/
        .title{
          margin: 2px 0 8px 0;
          .brand{
            display: inline-block;
            vertical-align: top;
            width: 30px;
            height: 18px;
            display: inline-block;
            .bg-img("@{path}brand");
            background-size: 30px 18px;
            background-repeat: no-repeat;
          }
          .name{
            margin-left: 6px;
            font-size: 16px;
            line-height: 18px;
            font-weight: bold;
          }
        }
        .description{
          margin-bottom: 10px;
          line-height: 12px;
          font-size: 12px;
        }
      }
    }
  }
</style>
