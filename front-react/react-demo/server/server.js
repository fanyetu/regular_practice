const express = require('express')
const mongoose = require('mongoose')

// 连接mongo 并且使用react这个集合
const DB_URL = 'mongodb://127.0.0.1:27017/react'
mongoose.connect(DB_URL)
mongoose.connection.on('connected', function () {
    console.log('mongodb connect success')
})

// 创建mongodb里面的文档，类似于mysql里面的表
const User = mongoose.model('user', new mongoose.Schema({
    name:{type: String, require: true},
    age:{type: Number, require: true}
}))

// 创建新的app
var app = express()

app.get('/', function (req, resp) {
    resp.send('<h1>hello world</h1>')
})

app.get('/data', function (req, resp) {
    resp.json({
        name:'tom jack',
        age: 18
    })
})

app.get('/create', function (req, resp) {
    User.create({
        name:'小明',
        age: 12
    }, function (err, doc) {
        console.log(doc)
    })
    resp.send('success')
})

app.get('/update', function (req, resp) {
    User.update({
        age: 12
    }, {
        '$set':{age: 25}
    },function (err, doc) {
        console.log(doc)
    })
    resp.send('success')
})

app.get('/find', function (req, resp) {
    User.find({name:'小明'}, function (err, doc) {
        resp.json(doc)
    })
})

app.get('/findOne', function (req, resp) {
    User.findOne({name:'小明'}, function (err, doc) {
        if (!err) {
            resp.json(doc)
        }
    })
})

app.get('/remove', function (req, resp) {
    User.remove({name:'小明'}, function (err, doc) {
        if (!err) {
            console.log(doc)
        }
        resp.send('success')
    })
})

app.listen(9093,function () {
    console.log('node app start at port 9093')
})