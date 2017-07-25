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
