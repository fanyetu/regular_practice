import {getLyric} from "api/song"
import {ERR_OK} from "api/config"
import {Base64} from 'js-base64'

/**
 * 歌曲类
 *
 * Created by zhanghaonan on 2017/8/2.
 */
export default class Song {

  constructor({id, mid, singer, name, album, duration, image, url}) {
    this.id = id
    this.mid = mid
    this.singer = singer
    this.name = name
    this.album = album
    this.duration = duration
    this.image = image
    this.url = url
  }

  /**
   * 获取歌词
   * @return {*}
   */
  getLyric() {
    if (this.lyric) {
      return Promise.resolve(this.lyric)
    }

    return new Promise((resovle, reject) => {
      getLyric(this.mid).then((resp) => {
        if (resp.retcode === ERR_OK) {
          this.lyric = Base64.decode(resp.lyric)
          resovle(this.lyric)
        } else {
          reject('no lyric')
        }
      })
    })

  }

}

/**
 * 创建Song对象的工厂方法
 * @param musicData
 * @returns {Song}
 */
export function createSong(musicData) {
  return new Song({
    id: musicData.songid,
    mid: musicData.songmid,
    singer: filterSinger(musicData.singer),
    name: musicData.songname,
    album: musicData.albumname,
    duration: musicData.interval,
    image: `https://y.gtimg.cn/music/photo_new/T002R300x300M000${musicData.albummid}.jpg?max_age=2592000`,
    url: `http://ws.stream.qqmusic.qq.com/${musicData.songid}.m4a?fromtag=46`
  })
}

/**
 * 处理singer的name数据，拼接成一个/分割的字符串
 * @param singer
 * @returns {*}
 */
function filterSinger(singer) {
  if (!singer) {
    return ''
  }
  let ret = []
  singer.forEach((s) => {
    ret.push(s.name)
  })
  return ret.join('/')
}
