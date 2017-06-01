import Vue from 'vue'
import Router from 'vue-router'
import goods from 'components/goods/goods'

// 使用router插件
Vue.use(Router)

export default new Router({
  routes: [{
    path:'/goods',
    component:goods
  }]
})
