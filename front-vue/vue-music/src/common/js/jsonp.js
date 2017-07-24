/**
 * Created by zhanghaonan on 2017/7/24.
 */
import originJSONP from 'jsonp'

/**
 * 封装jsonp
 * @param url
 * @param data
 * @param option
 * @returns {Promise}
 */
export default function jsonp(url, data, option) {
  url += (url.indexOf('?') < 0 ? '?' : '&') + params(data) // 拼接url和参数
  // 返回一个promise，成功即调用resolve，失败即调用reject
  return new Promise((resolve, reject) => {
    // 使用jsonp库
    originJSONP(url, option, (err, data) => {
      if (!err) {
        resolve(data)
      } else {
        reject(err)
      }
    })
  })
}

/**
 * 拼接url的参数
 * @param data
 * @returns {string}
 */
function params(data) {
  let url = ''
  for (let k in data) {
    let value = data[k] !== undefined ? data[k] : '';
    url += `&${k}=${encodeURIComponent(value)}` // 对参数进行encoding处理
  }
  return url ? url.substring(1) : ''
}
