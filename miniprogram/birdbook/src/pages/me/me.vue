<template>
  <div class="container">
    <div class="top">
      <img class="avatar" :src="avatarUrl"/>
      <span class="nickname" v-text="nickname"></span>
    </div>
    <div class="middle">
      <button class="btn" v-if="!userinfo" open-type="getUserInfo" lang="zh_CN" @click="doLogin">登录</button>
      <button class="btn" v-if="userinfo" @click="scanCode">添加图书</button>
    </div>
  </div>
</template>
<script>
  import qcloud from 'wafer2-client-sdk'
  import Config from '@/utils/config'
  import { post, showSuccess, showModal, showLoading, hideLoading } from '@/utils/util'

  const USER_INFO_KEY = 'userinfo'

  export default {
    data () {
      return {
        userinfo: null
      }
    },
    computed: {
      avatarUrl () {
        return this.userinfo ? this.userinfo.avatarUrl : '/static/imgs/unlogin.png'
      },
      nickname () {
        return this.userinfo ? this.userinfo.nickName : '请登录'
      }
    },
    created () {
      this.userinfo = wx.getStorageSync(USER_INFO_KEY)
      if (this.userinfo) {
        this.avatarUrl = this.userinfo.avatarUrl
        this.nickname = this.userinfo.nickName
      }
    },
    methods: {
      addBook (isbn) {
        post(Config.addBook, {
          isbn,
          openId: this.userinfo.openId
        }).then((res)=>{
          showSuccess('添加图书成功')
        })
      },
      scanCode () {
        wx.scanCode({
          success: (res) => {
            if (res.result) {
              this.addBook(res.result)
            } else {
              showModal('错误', '没有识别到图书')
            }
          }
        })
      },
      doLogin () {
        const session = qcloud.Session.get()
        showLoading()

        if (session) {
          // 第二次登录
          // 或者本地已经有登录态
          // 可使用本函数更新登录态
          qcloud.loginWithCode(this.loginHandler())
        } else {
          // 首次登录
          qcloud.login(this.loginHandler())
        }
      },
      loginHandler () {
        return {
          success: res => {
            hideLoading()
            this.saveUserInfo(res)
            showSuccess('登录成功')
          },
          fail: err => {
            hideLoading()
            showModal('登录错误', err.message)
          }
        }
      },
      saveUserInfo (res) {
        this.userinfo = res
        wx.setStorageSync(USER_INFO_KEY, res)
      }
    }
  }
</script>
<style scoped lang="less">
  @import "../../css/variable";

  .container {
    .top {
      margin-top: @margin-normal;
      display: flex;
      align-items: center;
      flex-direction: column;
      .avatar {
        height: ~'150rpx';
        width: ~'150rpx';
        border-radius: 50%;
      }
      .nickname {
        margin-top: ~'10rpx';
      }
    }
    .middle {
      margin-top: @margin-normal;
    }
  }
</style>
