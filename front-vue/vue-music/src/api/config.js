/**
 * Created by zhanghaonan on 2017/7/24.
 */
export const commonParams = {
  g_tk: 1928093487,
  inCharset: 'utf-8',
  outCharset: 'utf-8',
  notice: 0,
  format: 'jsonp'
}

export const options = {
  param: 'jsonpCallback',
  prefix: 'jsonpcallbackfunction' // 这里qq音乐有的接口截取了下划线等特殊符号，所以使用全字母的callback
}

// 正确返回码
export const ERR_OK = 0
