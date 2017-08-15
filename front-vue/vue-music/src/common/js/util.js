/**
 * Created by zhanghaonan on 2017/8/13.
 */

/**
 * 洗牌函数（将list打乱）
 * @param arr
 * @return
 */
export function shuffle(arr) {
  let _arr = arr.slice()
  for (let i = 0; i < _arr.length; i++) {
    let j = getRandomInt(0, i);
    let t = _arr[i]
    _arr[i] = _arr[j]
    _arr[j] = t
  }
  return _arr
}

/**
 * 返回min和max之间的随机数，包括max
 * @param min
 * @param max
 * @return
 */
function getRandomInt(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min)
}
