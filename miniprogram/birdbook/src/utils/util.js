import Config from './config'

/**
 * 封装get请求
 * @param url
 * @returns {Promise<any>}
 */
export function get (url) {
  return new Promise((resolve, reject) => {
    wx.request({
      url: Config.host + url,
      success: function (res) {
        if (res.data.code === 0) {
          resolve(res.data.data)
        } else {
          reject(res.data)
        }
      }
    })
  })
}
