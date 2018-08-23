<script>
  import {get} from './utils/util'

  export default {
    async created () {
      let data = await get('/demo')
      console.log(data)
    }
  }
</script>
<style>
</style>
