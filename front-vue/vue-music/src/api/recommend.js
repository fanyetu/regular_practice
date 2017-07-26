/**
 * Created by zhanghaonan on 2017/7/24.
 */
import jsonp from 'common/js/jsonp'
import {commonParams, options} from './config'
import axios from 'axios'

/**
 * 通过jsonp抓取qq音乐的数据
 */
export function getRecommend() {
  const url = 'https://c.y.qq.com/musichall/fcgi-bin/fcg_yqqhomepagerecommend.fcg'

  const data = Object.assign({}, commonParams, {
    platform: 'h5',
    uin: 0,
    needNewCode: 1
  })

  return jsonp(url, data, options)
}

/**
 * 通过后端代理的方式访问qq的接口，获取歌单数据
 * @returns {Promise.<TResult>}
 */
export function getDiscList() {
  const url = '/api/getDiscList'

  const data = Object.assign({}, commonParams, {
    platform: 'yqq',
    hostUin: 0,
    sin: 0,
    ein: 29,
    sortId: 5,
    needNewCode: 0,
    categoryId: 10000000,
    rnd: Math.random(),
    format: 'json'
  })

  return axios.get(url, {
    params: data
  }).then((resp) => {
    return Promise.resolve(resp.data)
  })
}
