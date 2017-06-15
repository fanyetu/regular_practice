import Vue from 'vue'
import Router from 'vue-router'
//通过配置webpack.base.config.js中components别名实现的
import goods from 'components/goods/goods'
import ratings from 'components/ratings/ratings'
import seller from 'components/seller/seller'

// 使用router插件
Vue.use(Router);

const routes = [{
  path: '/',
  redirect: '/goods'//使用redirect保证进入就跳转到goods组件
}, {
  path: '/goods',
  component: goods
}, {
  path: '/ratings',
  component: ratings
}, {
  path: '/seller',
  component: seller
}];

const router = new Router({
  linkActiveClass: 'active',
  routes
});

export default router
