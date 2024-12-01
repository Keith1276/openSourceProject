import store from "@/store"
import * as Icons from '@element-plus/icons-vue'
import axios from "axios"
import vClickOutside from "click-outside-vue3"
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createApp } from 'vue'
import vuetyped from 'vue3typed'
import App from './App.vue'
import router from './router'

axios.defaults.baseURL = 'https://api.github.com' //axios请求默认URL
// 这里的token应该要改一下，改成github令牌
axios.interceptors.request.use(config => {
    config.headers['token'] = store.getters.getToken; //有效
    config.headers.Authorization = `Bearer ${store.getters.getToken}`; //无效
    //console.log('请求头携带token：'+`${store.getters.getToken}`);
    return config;
}, error => {
    return Promise.reject(error);
}); //请求头token设置

const app = createApp(App);
// app.use(App);
app.use(ElementPlus);
app.use(router)
app.use(store)
app.use(vClickOutside)
app.use(vuetyped)
app.config.globalProperties.$http = axios;
Object.keys(Icons).forEach(key => {
    app.component(key, Icons[key])
})

app.mount('#app');
