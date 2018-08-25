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

/**
 * 展示loading
 * @param title
 */
export function showLoading (title='请稍等') {
  wx.showLoading({
    title
  })
}

/**
 * 关闭loading
 */
export function hideLoading () {
  wx.hideLoading()
}

/**
 * 展示成功提示
 * @param text
 */
export function showSuccess (text) {
  wx.showToast({
    title: text,
    icon: 'success'
  })
}

/**
 * 展示提示狂
 * @param title
 * @param message
 */
export function showModal (title, message) {
  wx.showModal({
    title: title,
    content: message,
    showCancel: false
  })
}
