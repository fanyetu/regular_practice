<script>
  import qcloud from 'wafer2-client-sdk'
  import Config from './utils/config'

  export default {
    async created () {
      console.log('小程序启动起来了')
      // 设置登录地址
      qcloud.setLoginUrl(Config.loginUrl);

      // 直接这样登录不了了，需要修改为button的方式
      // qcloud.login({
      //   success: function (userInfo) {
      //     console.log('登录成功', userInfo);
      //   },
      //   fail: function (err) {
      //     console.log('登录失败', err);
      //   }
      // });
    }
  }
</script>
<style lang="less">
  @import "./css/variable";

  .btn{
    background: @theme-color;
    color: @front-color;
    margin-left: @margin-normal;
    margin-right: @margin-normal;
    border-radius: ~'5rpx';
    font-size: @font-size-normal;
    &:active{
      background: @theme-color-deep;
    }
  }
</style>
