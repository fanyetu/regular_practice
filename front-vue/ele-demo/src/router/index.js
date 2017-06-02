import Vue from 'vue'
import Router from 'vue-router'
//通过配置webpack.base.config.js中components别名实现的
import goods from 'components/goods/goods'
import ratings from 'components/ratings/ratings'
import seller from 'components/seller/seller'

// 使用router插件
Vue.use(Router)

var router = new Router({
  routes: [{
    path:'/goods',
    component:goods
  },{
    path:'/ratings',
    component:ratings
  },{
    path:'/seller',
    component:seller
  }],
  linkActiveClass:'active'
})

//默认跳转到goods页面
router.push('/goods')

export default router
