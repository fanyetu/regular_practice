/**
 * 添加图书controller
 */

const https = require('https')
const { mysql } = require('../qcloud')

// 新增图书
// 1. 获取豆瓣信息
// https://developers.douban.com/wiki/?title=book_v2#get_isbn_book
// https://api.douban.com/v2/book/isbn/9787536692930
// 2. 入库
module.exports = async (ctx) => {
  const { isbn, openId } = ctx.request.body
  console.log(isbn, openId)
  if (isbn && openId) {
    // 查询数据库中是否已经有了这本书
    const findRes = await mysql('books').select().where('isbn', isbn)
    if (findRes.length) {
      ctx.state = {
        code: -1,
        data: {
          msg: '图书已存在'
        }
      }
      return
    }

    // 调用豆瓣的接口查询图书信息
    let url = 'https://api.douban.com/v2/book/isbn/' + isbn
    const bookInfo = await getJSON(url)
    console.log(bookInfo)
    // 组装数据
    const rate = bookInfo.rating.average
    const { title, image, alt, publisher, summary, price } = bookInfo
    const tags = bookInfo.tags.map(v => {
      return `${v.title} ${v.count}`
    }).join(',')
    const author = bookInfo.author.join(',')

    try {
      // 将书籍信息存入数据库
      await mysql('books').insert({
        isbn, openId, rate, title, image, alt, publisher, summary, price, tags, author
      })
      ctx.state.data = {
        title,
        msg: 'success'
      }
    } catch (e) {
      console.error(e)
      ctx.state = {
        code: -1,
        data: {
          msg: '新增书籍失败: ' + e.sqlMessage
        }
      }
    }

  }
}

/**
 * get请求获取json
 * @param url
 * @returns {Promise<any>}
 */
function getJSON (url) {
  return new Promise((resolve, reject) => {
    https.get(url, res => {
      let urlData = ''
      res.on('data', data => {
        urlData += data
      })

      res.on('end', data => {
        const bookInfo = JSON.parse(urlData)
        if (bookInfo.title) {
          resolve(bookInfo)
        }
        reject(bookInfo)
      })
    })
  })
}
