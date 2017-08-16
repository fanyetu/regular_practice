/**
 * Created by zhanghaonan on 2017/8/16.
 */
import {commonParams} from './config'
import axios from 'axios'

/**
 * 获取歌词数据的接口，使用后端代理获取qq音乐的数据
 * @param mid
 * @return {Promise.<TResult>}
 */
export function getLyric(mid) {
  const url = '/api/lyric'

  const data = Object.assign({}, commonParams, {
    songmid: mid,
    platform: 'yqq',
    hostUin: 0,
    needNewCode: 0,
    categoryId: 10000000,
    pcachetime: +new Date(),
    format: 'json'
  })

  return axios.get(url, {
    params: data
  }).then((resp) => {
    return Promise.resolve(resp.data)
  })
}
