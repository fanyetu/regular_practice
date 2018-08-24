<script>
  import qcloud from 'wafer2-client-sdk'
  import Config from './utils/config'
  import {get} from './utils/util'

  export default {
    async created () {
      // 设置登录地址
      console.log(Config.loginUrl)
      // 直接这样登录不了了，需要修改为button的方式
      qcloud.setLoginUrl(Config.loginUrl);
      qcloud.login({
        success: function (userInfo) {
          console.log('登录成功', userInfo);
        },
        fail: function (err) {
          console.log('登录失败', err);
        }
      });

      // let data = await get('/demo')
      // console.log(data)
    }
  }
</script>
<style>
</style>
