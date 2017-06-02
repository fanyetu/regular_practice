import Vue from 'vue'
import App from './App'
import router from './router'
import vueResource from 'vue-resource'

//在js中引入css，全局使用
import 'common/css/index.less'

Vue.config.productionTip = false

//加载vueResource插件
Vue.use(vueResource)

/* eslint-disable no-new */
new Vue({
  el: '#box-app',
  router,
  render: h => h(App)
})

