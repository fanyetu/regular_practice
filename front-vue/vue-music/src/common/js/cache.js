/**
 * 操作localStorage相关的逻辑
 *
 * Created by zhanghaonan on 2017/8/26.
 */

import storage from 'good-storage'

const SEARCH_KEY = '__search__'
const SEARCH_MAX_LENGTH = 15 // 最大存储空间

const PLAY_KEY = '__play__'
const PLAY_MAX_LENGTH = 200

const FAVORITE_KEY = '__favorite__'
const FAVORITE_MAX_LENGTH = 200

/**
 * 加载收藏歌曲
 * @return {*}
 */
export function loadFavorite() {
  return storage.get(FAVORITE_KEY, [])
}

/**
 * 删除收藏歌曲
 * @param song
 * @return {*}
 */
export function deleteFavorite(song) {
  let songs = storage.get(FAVORITE_KEY)
  deleteFromArray(songs, (item) => {
    return song.id === item.id
  })

  storage.set(FAVORITE_KEY, songs)
  return songs
}

/**
 * 保存收藏歌曲
 * @param song
 * @return {*}
 */
export function saveFavorite(song) {
  let songs = storage.get(FAVORITE_KEY, [])
  insertArray(songs, song, (item) => {
    return song.id === item.id
  }, FAVORITE_MAX_LENGTH)

  storage.set(FAVORITE_KEY, songs)

  return songs
}

/**
 * 读取本地歌曲列表
 * @return {*}
 */
export function loadPlay() {
  return storage.get(PLAY_KEY, [])
}

/**
 * 存储歌曲到本地
 * @param song
 * @return {*}
 */
export function savePlay(song) {
  let songs = storage.get(PLAY_KEY, [])

  insertArray(songs, song, (item) => {
    return item.id === song.id
  }, PLAY_MAX_LENGTH)

  storage.set(PLAY_KEY, songs)
  return songs
}

/**
 * 情况本地搜索缓存
 */
export function clearSearch() {
  storage.remove(SEARCH_KEY)
  return []
}

/**
 * 从本地缓存中删除query
 * @param query
 * @return {*}
 */
export function deleteSearch(query) {
  let searches = storage.get(SEARCH_KEY)
  deleteFromArray(searches, (item) => {
    return item === query
  })

  storage.set(SEARCH_KEY, searches)
  return searches
}

/**
 * 从数组中删除
 * @param arr
 * @param compare
 */
function deleteFromArray(arr, compare) {
  const index = arr.findIndex(compare)
  if (index > -1) {
    arr.splice(index, 1)
  }
}

/**
 * 加载搜索历史
 * @return {*}
 */
export function loadSearch() {
  return storage.get(SEARCH_KEY, [])
}

/**
 * 存储搜索历史
 * @param query
 */
export function saveSearch(query) {
  let searches = storage.get(SEARCH_KEY, [])

  insertArray(searches, query, (item) => {
    return item === query
  }, SEARCH_MAX_LENGTH)

  storage.set(SEARCH_KEY, searches)
  return searches
}

/**
 * 在数组头部插入元素
 * @param arr
 * @param val
 * @param compare
 * @param maxLen
 */
function insertArray(arr, val, compare, maxLen) {
  const index = arr.findIndex(compare)
  if (index === 0) {
    return
  }
  if (index > 0) {
    arr.splice(index, 1)
  }
  arr.unshift(val)
  if (maxLen && arr.length > maxLen) {
    arr.pop()
  }
}
