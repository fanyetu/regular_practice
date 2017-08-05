/**
 * Created by zhanghaonan on 2017/7/25.
 */

/**
 * 为dom添加一个className
 * @param el dom对象
 * @param className 新添加的className
 */
export function addClass(el, className) {
  if (hasClass(el, className)) {
    return
  }
  let newClass = el.className.split(' ')
  newClass.push(className)
  el.className = newClass.join(' ')
}

/**
 * 检测dom对象是否包含className
 * @param el dom对象
 * @param className 要检测的className
 * @returns {boolean}
 */
export function hasClass(el, className) {
  let reg = new RegExp('(^|\\s)' + className + '(\\s|$)')
  return reg.test(el.className)
}

/**
 * 从dom获取值，或为dom设置值
 * @param el
 * @param name
 * @param val 如果val为空则为get，如果val不为空则为set
 * @returns {*}
 */
export function getData(el, name, val) {
  const prefix = 'data-'
  name = prefix + name
  if (val) {
    return el.setAttribute(name, val)
  } else {
    return el.getAttribute(name)
  }
}

// 用于检测当前浏览器支持的前缀
let elementStype = document.createElement('div').style

let vendor = (() => {
  let transformNames = {
    webkit: 'webkitTransform',
    Moz: 'MozTransform',
    O: 'OTransform',
    ms: 'msTransform',
    standard: 'transform'
  }

  for (let key in transformNames) {
    if (elementStype[transformNames[key]] !== undefined) {
      return key
    }
  }

  return false
})()

/**
 * 为样式名加上浏览器前缀
 * @param style
 * @returns {*}
 */
export function prefixStyle(style) {
  if (vendor === false) {
    return false
  }

  if (vendor === 'standard') {
    return style
  }

  return vendor + style.charAt(0).toUpperCase() + style.substr(1)
}
