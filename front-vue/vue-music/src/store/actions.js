/**
 * action的使用时机：
 * 1.异步修改state
 * 2.对mutation的封装
 *
 * Created by zhanghaonan on 2017/8/1.
 */
import * as types from './mutation-types'

/**
 * 选择一首歌曲的时候要提交的mutations
 * @param commit vuex传入的commit
 * @param state vuex传入的state
 * @param list
 * @param index
 */
export const selectPlay = function ({commit, state}, {list, index}) {
  commit(types.SET_SEQUENCE_LIST, list) // 设置顺序播放列表
  commit(types.SET_PLAY_LIST, list) // 设置播放列表
  commit(types.SET_CURRENT_INDEX, index) // 设置当前播放索引
  commit(types.SET_PLAYING, true) // 设置播放状态为true
  commit(types.SET_FULL_SCREEN, true) // 设置播放器全屏
}
