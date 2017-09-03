import 'babel-polyfill'
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import fastclick from 'fastclick';
import VueLazyLoad from 'vue-lazyload'

import 'common/stylus/index.styl'

/* eslint-disable no-unused-vars */
import vConsole from 'vconsole' // 引入vConsole移动端console.log输出工具，不需要配置，引入即可使用

console.log("test")

// Vue.config.productionTip = false

// 使用fastclick解决移动端点击300毫秒的延迟
fastclick.attach(document.body)

Vue.use(VueLazyLoad, {
  loading: require('common/image/default.png')
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store, // 注册vuex
  render: h => h(App)
})
