/**
 * 封装get请求
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
export function get (url, data) {
  return request(url, 'GET', data)
}

/**
 * post请求
 * @param url
 * @param data
 */
export function post (url, data) {
  return requrest(url, 'POST', data)
}

/**
 * 通用请求
 * @param url
 * @param method
 * @param data
 * @returns {Promise<any>}
 */
export function requrest (url, method, data) {
  showLoading()
  return new Promise((resolve, reject)=>{
    wx.request({
      url,
      method,
      data,
      success: function (res) {
        hideLoading()
        if (res.data.code === 0) {
          resolve(res.data.data)
        } else {
          showModal('错误', res.data.data.msg || '请求失败')
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
