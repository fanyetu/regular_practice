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
