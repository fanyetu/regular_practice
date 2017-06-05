<!-- create by zhanghaonan 2017/6/1 -->
<template>
  <div class="goods">
    <div class="menu-wrapper" ref="menuWrapper">
      <ul>
        <li v-for="(item,index) in goods" class="menu-item"
            :class="{'current':currentIndex === index}"
            @click="selectMenu(index,$event)">
          <span class="text">
            <span v-show="item.type>0" class="icon" :class="classMap[item.type]"></span>
            {{item.name}}
          </span>
        </li>
      </ul>
    </div>
    <div class="foods-wrapper" ref="foodsWrapper">
      <ul>
        <li v-for="item in goods" class="food-list food-list-hook">
          <h1 class="title">{{item.name}}</h1>
          <ul>
            <li v-for="food in item.foods" class="food-item">
              <div class="icon">
                <img width="57" :src="food.icon"/>
              </div>
              <div class="content">
                <h2 class="name">{{food.name}}</h2>
                <p class="desc">{{food.description}}</p>
                <div class="extra">
                  <span class="count">月售{{food.sellCount}}份</span><span>好评率{{food.rating}}%</span>
                </div>
                <div class="price">
                  <span class="now">￥{{food.price}}</span><span v-show="food.oldPrice"
                                                                class="old">￥{{food.oldPrice}}</span>
                </div>
              </div>
              <div class="cartcontrol-wrapper">
                <cartcontrol :food="food"></cartcontrol>
              </div>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <shopcart :delivery-price="seller.deliveryPrice"
      :min-price="seller.minPrice"></shopcart>
  </div>
</template>
<script type="text/ecmascript-6">
  import BScroll from 'better-scroll';
  import shopcart from 'components/shopcart/shopcart';
  import cartcontrol from 'components/cartcontrol/cartcontrol';

  const ERR_OK = 0;

  export default{
    components:{
      shopcart,
      cartcontrol
    },
    props: {
      seller: {
        type: Object
      }
    },
    data(){
      return {
        goods: [],
        //每个区间的高度
        listHeights: [],
        scrollY: 0
      }
    },
    created(){
      //获取商品列表
      this.$http.get('/api/goods').then((resp) => {
        resp = resp.body;//通过body属性取到返回的内容
        if (resp.errno === ERR_OK) {
          this.goods = resp.data;
          //在下次 DOM 更新循环结束之后执行延迟回调。在修改数据之后立即使用这个方法，获取更新后的 DOM。
          this.$nextTick(() => {
            this._initScroll();
            this._calculateHeight();
          });
        }
      });

      this.classMap = ["decrease", "discount", "special", "invoice", "guarantee"];
    },
    computed: {
      currentIndex(){
        for (let i = 0; i < this.listHeights.length; i++) {
          let height1 = this.listHeights[i];
          let height2 = this.listHeights[i + 1];
          if (!height2 || (this.scrollY >= height1 && this.scrollY < height2)) {
            return i;
          }
        }
        return 0;
      }
    },
    methods: {
      selectMenu(index, event) {
        //vue的v-bind中传入$event，其中better-scroll的event中_constructed为true，而浏览器原生事件没有
        //_constructed
        if (!event._constructed) {
          return;
        }
        let foodList = this.$refs.foodsWrapper.getElementsByClassName("food-list-hook");
        let el = foodList[index];
        this.foodsScroll.scrollToElement(el, 300);
      },
      //初始化scroll
      _initScroll() {
        //通过vue ref属性获取到dom
        this.menuScroll = new BScroll(this.$refs.menuWrapper, {
          click: true
        });
        this.foodsScroll = new BScroll(this.$refs.foodsWrapper, {
          probeType: 3//让bscroll实时返回滚动的位置
        });
        this.foodsScroll.on("scroll", (pos) => {
          this.scrollY = Math.abs(Math.round(pos.y));
        })
      },
      _calculateHeight(){
        //获取所有的li
        let foodList = this.$refs.foodsWrapper.getElementsByClassName("food-list-hook");
        let height = 0;
        this.listHeights.push(height);
        for (let i = 0; i < foodList.length; i++) {
          let item = foodList[i];
          height += item.clientHeight;
          this.listHeights.push(height);
        }
      }
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
        &.current {
          position: relative;
          z-index: 10;
          margin-top: -1px;
          background: #fff;
          font-weight: 700;
          .text {
            .bottom-border-none();
          }
        }
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
          .bottom-border-1px(rgba(7, 17, 27, 0.1))
        }
      }
    }
    .foods-wrapper {
      flex: 1;
      .title {
        padding-left: 14px;
        height: 26px;
        line-height: 26px;
        border-left: 2px solid #d9dde1;
        font-size: 12px;
        color: rgb(147, 153, 159);
        background-color: #f3f5f7;
      }
      .food-item {
        display: flex;
        margin: 18px;
        padding-bottom: 18px;
        .bottom-border-1px(rgba(7, 17, 27, 0.1));
        &:last-child {
          margin-bottom: 0;
          .bottom-border-none();
        }
        .icon {
          flex: 0 0 57px;
          width: 57px;
          margin-right: 10px;
        }
        .content {
          flex: 1;
          .name {
            margin: 2px 0 8px 0;
            height: 14px;
            line-height: 14px;
            font-size: 14px;
            color: rgb(7, 17, 27);
          }
          .desc, .extra {
            line-height: 10px;
            font-size: 10px;
            color: rgb(147, 153, 159);
          }
          .desc {
            margin-bottom: 8px;
            line-height: 12px;
          }
          .extra {
            .count {
              margin-right: 12px;
            }
          }
          .price {
            font-weight: 700;
            line-height: 24px;
            .now {
              margin-right: 18px;
              font-size: 14px;
              color: rgb(240, 20, 20);
            }
            .old {
              text-decoration: line-through;
              font-size: 10px;
              color: rgb(147, 153, 159);
            }
          }
        }
      }
    }
  }
</style>
