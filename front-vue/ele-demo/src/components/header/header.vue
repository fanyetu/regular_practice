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
          <span class="icon" :class="classMap[seller.supports[0].type]"></span>
          <span class="text">{{seller.supports[0].description}}</span>
        </div>
      </div>
      <div v-if="seller.supports" class="support-count" @click="showDetail">
        <span class="count">{{seller.supports.length}}个</span>
        <i class="icon-keyboard_arrow_right"></i>
      </div>
    </div>
    <div class="bulletin-wrapper" @click="showDetail">
      <span class="bulletin-title"></span><span class="bulletin-text">{{seller.bulletin}}</span>
      <i class="icon-keyboard_arrow_right"></i>
    </div>
    <div class="background">
      <img :src="seller.avatar" width="100%" height="100%">
    </div>
    <transition name="fade">
      <div class="detail" v-show="detailShow">
        <!--css sticker foot布局-->
        <div class="detail-wrapper clear-fix">
          <div class="detail-main">
            <h1 class="name">{{seller.name}}</h1>
            <div class="star-wrapper">
              <star :size="48" :score="seller.score"></star>
            </div>
            <div class="title">
              <div class="line"></div>
              <div class="text">优惠信息</div>
              <div class="line"></div>
            </div>
            <ul v-if="seller.supports" class="supports">
              <li class="supports-item" v-for="(item , index) in seller.supports">
                <span class="icon" :class="classMap[seller.supports[index].type]"></span>
                <span class="text">{{seller.supports[index].description}}</span>
              </li>
            </ul>
            <div class="title">
              <div class="line"></div>
              <div class="text">商家公告</div>
              <div class="line"></div>
            </div>
            <div class="bulletin">
              <p class="content">{{seller.bulletin}}</p>
            </div>
          </div>
        </div>
        <div class="detail-close">
          <i class="icon-close" @click="hideDetail"></i>
        </div>
      </div>
    </transition>
  </div>
</template>
<script type="text/ecmascript-6">
  import star from "components/star/star"

  export default{
    data(){
      return {
        detailShow: false
      }
    },
    props: {
      seller: {
        type: Object
      }
    },
    methods: {
      showDetail: function () {
        this.detailShow = true;
      },
      hideDetail: function () {
        this.detailShow = false;
      }
    },
    created(){
      this.classMap = ["decrease", "discount", "special", "invoice", "guarantee"];
    },
    components: {
      star
    }
  }
</script>
<style lang="less">
  @import url("../../common/css/mixin");

  @path: "../../components/header/"; //解决less引用文件相对路径问题,stylus没有这个问题

  // 定义蒙版切换样式
  .fade-enter-active, .fade-leave-active {
    transition: opacity 0.5s;
  }

  .fade-enter, .fade-leave-active {
    opacity: 0;
  }

  .header {
    position: relative;
    overflow: hidden;
    color: #fff;
    background: rgba(7, 17, 27, 0.5);
    .content-wrapper {
      position: relative;
      padding: 24px 12px 18px 24px;
      font-size: 0; //将avatar和content中间的间隙取消，同时content的要设置font-size
      .avatar {
        display: inline-block;
        vertical-align: top;
        img {
          border-radius: 2px;
        }
      }
      .content {
        display: inline-block;
        margin-left: 16px;
        /*font-size: 14px;*/
        .title {
          margin: 2px 0 8px 0;
          .brand {
            display: inline-block;
            vertical-align: top;
            width: 30px;
            height: 18px;
            display: inline-block;
            .bg-img("@{path}brand");
            background-size: 30px 18px;
            background-repeat: no-repeat;
          }
          .name {
            margin-left: 6px;
            font-size: 16px;
            line-height: 18px;
            font-weight: bold;
          }
        }
        .description {
          margin-bottom: 10px;
          line-height: 12px;
          font-size: 12px;
        }
        .support {
          .icon {
            display: inline-block;
            vertical-align: top;
            height: 12px;
            width: 12px;
            margin-right: 4px;
            background-size: 12px 12px;
            background-repeat: no-repeat;
            &.decrease {
              .bg-img("@{path}decrease_1");
            }
            &.discount {
              .bg-img("@{path}discount_1");
            }
            &.guarantee {
              .bg-img("@{path}guarantee_1");
            }
            &.invoice {
              .bg-img("@{path}invoice_1");
            }
            &.special {
              .bg-img("@{path}special_1");
            }
          }
          .text {
            line-height: 14px;
            font-size: 10px;
          }
        }
      }
      .support-count {
        position: absolute;
        right: 12px;
        bottom: 14px;
        padding: 0 8px;
        height: 24px;
        border-radius: 14px;
        background: rgba(0, 0, 0, 0.2);
        text-align: center;
        .count {
          vertical-align: top;
          font-size: 10px;
          line-height: 26px;
        }
        .icon-keyboard_arrow_right {
          margin-left: 2px;
          line-height: 24px;
          font-size: 10px;
        }
      }
    }
    .bulletin-wrapper {
      position: relative;
      height: 28px;
      line-height: 28px;
      padding: 0 22px 0 12px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      background: rgba(7, 17, 27, 0.2);
      .bulletin-title {
        display: inline-block;
        vertical-align: top;
        margin-top: 8px;
        width: 22px;
        height: 12px;
        .bg-img("@{path}bulletin");
        background-size: 22px 12px;
        background-repeat: no-repeat;
      }
      .bulletin-text {
        vertical-align: top;
        font-size: 10px;
        margin: 0 4px 0 4px;
      }
      .icon-keyboard_arrow_right {
        position: absolute;
        font-size: 10px;
        right: 12px;
        top: 8px;
      }
    }
    .background {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      background-size: contain;
      z-index: -1;
      filter: blur(10px);
    }
    .detail {
      position: fixed;
      top: 0;
      left: 0;
      z-index: 100;
      width: 100%;
      height: 100%;
      overflow: auto;
      background: rgba(7, 17, 27, 0.8);
      // css sticker foot布局
      .detail-wrapper {
        min-height: 100%;
        width: 100%;
        .detail-main {
          margin-top: 64px;
          padding-bottom: 64px;
          .name {
            line-height: 16px;
            font-size: 16px;
            text-align: center;
            font-weight: 700;
          }
          .star-wrapper {
            margin-top: 18px;
            padding: 2px 0;
            text-align: center;
          }
          .title {
            display: flex;
            width: 80%;
            margin: 28px auto 24px auto;
            .line {
              flex: 1;
              position: relative;
              top: -6px;
              border-bottom: 1px solid rgba(255, 255, 255, 0.2);
            }
            .text {
              padding: 0 12px;
              font-size: 14px;
              font-weight: 700;
            }
          }
          .supports {
            width: 80%;
            margin: 0 auto;
            .supports-item {
              padding: 0 12px;
              font-size: 0;
              margin-bottom: 12px;
              &:last-child {
                margin-bottom: 0;
              }
              .icon {
                display: inline-block;
                vertical-align: top;
                width: 16px;
                height: 16px;
                margin-right: 6px;
                background-size: 16px 16px;
                background-repeat: no-repeat;
                &.decrease {
                  .bg-img("@{path}decrease_2");
                }
                &.discount {
                  .bg-img("@{path}discount_2");
                }
                &.guarantee {
                  .bg-img("@{path}guarantee_2");
                }
                &.invoice {
                  .bg-img("@{path}invoice_2");
                }
                &.special {
                  .bg-img("@{path}special_2");
                }
              }
              .text {
                line-height: 16px;
                font-size: 12px;
              }
            }
          }
          .bulletin {
            width: 80%;
            margin: 0 auto;
            .content {
              padding: 0 12px;
              line-height: 24px;
              font-size: 12px;
            }
          }
        }
      }
      // css sticker foot布局
      .detail-close {
        position: relative;
        width: 32px;
        height: 32px;
        margin: -64px auto 0 auto;
        clear: both;
        font-size: 32px;
      }
    }
  }
</style>
