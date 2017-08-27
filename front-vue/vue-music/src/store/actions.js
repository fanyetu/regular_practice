/**
 * action的使用时机：
 * 1.异步修改state
 * 2.对mutation的封装
 *
 * Created by zhanghaonan on 2017/8/1.
 */
import * as types from './mutation-types'
import {playMode} from "common/js/config"
import {shuffle} from "common/js/util"
import {saveSearch, deleteSearch, clearSearch} from "common/js/cache"

/**
 * 清空搜索结果列表
 * @param commit
 */
export const clearSearchHistory = function ({commit}) {
  commit(types.SET_SEARCH_HISTORY, clearSearch())
}

/**
 * 从搜索结果历史中删除query
 * @param commit
 * @param query
 */
export const deleteSearchHistory = function ({commit}, query) {
  commit(types.SET_SEARCH_HISTORY, deleteSearch(query))
}

/**
 * 将搜索历史存储到本地和state中
 * @param commit
 * @param query
 */
export const saveSearchHistory = function ({commit}, query) {
  commit(types.SET_SEARCH_HISTORY, saveSearch(query))
}

/**
 * 向playlist中插入一条数据
 * @param commit
 * @param state
 * @param song
 */
export const insertSong = function ({commit, state}, song) {
  let playlist = state.playList.slice()
  let sequenceList = state.sequenceList.slice()
  let currentIndex = state.currentIndex
  // 记录当前歌曲
  let currentSong = playlist[currentIndex]
  // 查找当前列表中是否有待插入的歌曲并返回其索引
  let fpIndex = findIndex(playlist, song)
  // 因为是插入歌曲，所以索引+1
  currentIndex++
  // 插入这首歌到当前索引位置
  playlist.splice(currentIndex, 0, song)
  // 如果已经包含了这首歌
  if (fpIndex > -1) {
    // 如果当前插入的序号大于列表中的序号
    if (currentIndex > fpIndex) {
      playlist.splice(fpIndex, 1)
      currentIndex--
    } else {
      playlist.splice(fpIndex + 1, 1)
    }
  }
  let currentSIndex = findIndex(sequenceList, currentSong) + 1
  let fsIndex = findIndex(sequenceList, song)
  sequenceList.splice(currentSIndex, 0, song)
  if (fsIndex > -1) {
    if (currentSIndex > fsIndex) {
      sequenceList.splice(fsIndex, 1)
    } else {
      sequenceList.splice(fsIndex + 1, 1)
    }
  }
  commit(types.SET_PLAY_LIST, playlist)
  commit(types.SET_SEQUENCE_LIST, sequenceList)
  commit(types.SET_CURRENT_INDEX, currentIndex)
  commit(types.SET_FULL_SCREEN, true)
  commit(types.SET_PLAYING, true)
}

/**
 * 查找当前song在list中的index
 * @param list
 * @param song
 */
function findIndex(list, song) {
  return list.findIndex((item) => {
    return item.id === song.id
  })
}

/**
 * 选择一首歌曲的时候要提交的mutations
 * @param commit vuex传入的commit
 * @param list
 * @param index
 */
export const selectPlay = function ({commit, state}, {list, index}) {
  commit(types.SET_SEQUENCE_LIST, list) // 设置顺序播放列表
  // 当当前的播放模式为随机播放的时候，将播放列表设置成随机列表
  if (state.mode === playMode.random) {
    let randomList = shuffle(list)
    commit(types.SET_PLAY_LIST, randomList)
    index = findIndex(randomList, list[index])
  } else {
    commit(types.SET_PLAY_LIST, list) // 设置播放列表
  }
  commit(types.SET_CURRENT_INDEX, index) // 设置当前播放索引
  commit(types.SET_PLAYING, true) // 设置播放状态为true
  commit(types.SET_FULL_SCREEN, true) // 设置播放器全屏
}

/**
 * 点击随机播放按钮的提交的action
 * @param commit
 * @param list
 */
export const randomPlay = function ({commit}, {list}) {
  commit(types.SET_MODE, playMode.random) // 设置播放模式为随机播放
  commit(types.SET_SEQUENCE_LIST, list) // 设置顺序播放列表
  let randomList = shuffle(list) // 获取随机播放列表
  commit(types.SET_PLAY_LIST, randomList) // 设置播放列表
  commit(types.SET_CURRENT_INDEX, 0) // 设置当前index为0
  commit(types.SET_PLAYING, true) // 设置播放状态为true
  commit(types.SET_FULL_SCREEN, true) // 设置播放器全屏
}
