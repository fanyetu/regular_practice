import axios from 'axios'
import {Toast} from 'antd-mobile'

// 配置axios
axios.interceptors.request.use((config)=>{
    Toast.loading('加载中...', 0)
    return config
})

axios.interceptors.response.use((config)=>{
    setTimeout(()=>{
        Toast.hide()
    }, 2000)
    return config
})