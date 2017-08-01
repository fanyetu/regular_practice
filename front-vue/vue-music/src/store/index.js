/**
 * Created by zhanghaonan on 2017/8/1.
 */
import Vue from 'vue'
import Vuex from 'vuex'
import * as actions from './actions'
import * as getters from './getters'
import state from './state'
import mutations from './mutations'
import createLogger from 'vuex/dist/logger'

Vue.use(Vuex)

// 如果不是生产环境则开启debug
const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  actions,
  getters,
  state,
  mutations,
  strict: debug, // 开启vuex严格模式，只能通过mutations的方式修改state
  plugins: debug ? [createLogger()] : [] // 在debug模式下启用logger插件，打印vuex的状态信息
})
