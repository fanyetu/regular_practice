/**
 * Created by zhanghaonan on 2017/8/1.
 */
import {playMode} from "common/js/config"

const state = {
  singer: {},
  playing: false, // 当前播放器的状态
  fullScreen: false, // 播放器是否全屏
  playList: [], // 歌曲播放列表
  sequenceList: [], // 歌曲顺序列表
  mode: playMode.sequence, // 播放模式
  currentIndex: -1, // 当前歌曲索引
  disc: {}, // 歌单对象
  topList: {} // 排行榜歌单对象
}

export default state
