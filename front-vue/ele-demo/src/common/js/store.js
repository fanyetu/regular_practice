/**
 * Created by zhanghaonan on 2017/6/14.
 */
/**
 * 存储到localStorage
 * @param id
 * @param key
 * @param value
 */
export function saveToLocal(id, key, value) {
  let seller = window.localStorage.__seller__;
  if (!seller) {
    seller = {};
    seller[id] = {};
  } else {
    seller = JSON.parse(seller);//从localStorage取出来的是字符串
    if (!seller[id]) {
      seller[id] = {};
    }
  }
  seller[id][key] = value;
  window.localStorage.__seller__ = JSON.stringify(seller);
}

/**
 * 读取localStorage
 * @param id
 * @param key
 * @param def
 */
export function loadFromLocal(id, key, def) {
  let seller = window.localStorage.__seller__;
  if (!seller) {
    return def;
  }
  seller = JSON.parse(seller)[id];
  if (!seller) {
    return def;
  }
  return seller[key] || def;
}
