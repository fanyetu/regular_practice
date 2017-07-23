import 'babel-polyfill'
import Vue from 'vue'
import App from './App'
import router from './router'
import fastclick from 'fastclick';

import 'common/stylus/index.styl'

// Vue.config.productionTip = false

// 使用fastclick解决移动端点击300毫秒的延迟
fastclick.attach(document.body)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
