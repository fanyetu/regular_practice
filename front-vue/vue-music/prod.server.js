/**
 * Created by zhanghaonan on 2017/9/3.
 */
var express = require('express')
var config = require('./config/index')
var axios = require('axios')

var app = express()

// 通过代理访问qq的接口，前端的ajax无法修改header的host和referer，在服务端修改
var apiRoutes = express.Router()

apiRoutes.get('/getDiscList', function (req, resp) {
  var url = 'https://c.y.qq.com/splcloud/fcgi-bin/fcg_get_diss_by_tag.fcg'

  axios.get(url, {
    headers: { // 伪装成qq自己的客户端
      referer: 'https://c.y.qq.com/',
      host: 'c.y.qq.com'
    },
    params: req.query
  }).then((response) => {
    resp.json(response.data)
  }).catch((e) => {
    console.log(e)
  })
})

// 获取歌词信息
apiRoutes.get('/lyric', function (req, res) {
  var url = 'https://c.y.qq.com/lyric/fcgi-bin/fcg_query_lyric_new.fcg'

  axios.get(url, {
    headers: {
      referer: 'https://c.y.qq.com/',
      host: 'c.y.qq.com'
    },
    params: req.query
  }).then((response) => {
    var ret = response.data
    if (typeof ret === 'string') {
      var reg = /^\w+\(({[^()]+})\)$/
      var matches = ret.match(reg)
      if (matches) {
        ret = JSON.parse(matches[1])
      }
    }
    res.json(ret)
  }).catch((e) => {
    console.log(e)
  })
})

app.use('/api', apiRoutes)

app.use(express.static('./dist'))

var port = process.env.PORT || config.build.port

module.exports = app.listen(port, function (err) {
  if (err){
    console.log(err)
    return
  }
  console.log('> Listening at http://localhost:' + port + '\n')
})

// 使用node prod.server.js启动这个server
