import Vue from 'vue'
import Router from 'vue-router'

import rank from 'components/rank/rank'
import recommend from 'components/recommend/recommend'
import search from 'components/search/search'
import singer from 'components/singer/singer'
import singerDetail from 'components/singer-detail/singer-detail'

// 在vue中注册router
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/recommend'
    },
    {
      path: '/rank',
      component: rank
    },
    {
      path: '/recommend',
      component: recommend
    },
    {
      path: '/search',
      component: search
    },
    {
      path: '/singer',
      component: singer,
      // 为singer下面配置子路由
      children: [
        {
          path: ':id', // :id代表动态的参数
          component: singerDetail
        }
      ]
    }
  ]
})
