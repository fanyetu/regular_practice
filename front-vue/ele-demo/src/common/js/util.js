/**
 * Created by zhanghaonan on 2017/6/15.
 */
/*
 解析url参数
 @example ?id=123&a=b
 @return Object {id:123,a:b}
 */
export function urlParse() {
  let url = window.location.search;
  let result = {};
  let regx = /[?&][^?&]+=[^?&]+/g;
  let arr = url.match(regx);
  // ['?id=123','&a=b']
  if (arr) {
    arr.forEach((item) => {
      let tempArr = item.substring(1).split("=");
      let key = decodeURIComponent(tempArr[0]);
      let value = decodeURIComponent(tempArr[1]);
      result[key] = value;
    });
  }

  return result;
}
