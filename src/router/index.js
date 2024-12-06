import { createRouter,createWebHashHistory, createWebHistory } from "vue-router";
import {computed, defineAsyncComponent} from 'vue'
import store from "@/store";
import {callSuccess, callError, callInfo, callWarning} from "@/call";
import {setNav} from "@/nav/set";
//路由设置

const router = createRouter({
    //history: createWebHashHistory(process.env.BASE_URL),  // hash 模式
    history: createWebHistory(),  // history 模式
    routes: [
        //学者搜索页面路由
        {
            path:'/Board',
            name:'Board',
            component: defineAsyncComponent(() => import(`../page/Board/index.vue`)),
            meta: {
                title: '主页',
            }
        },

        {
            path: '/scholarSearch',
            name: 'scholarSearch',
            component: defineAsyncComponent(() => import(`../page/scholarSearch/index.vue`)),
            meta: {
                title: '开源开发者搜索',
            },
        },
        {
            path: '/personal',
            name: 'personal',
            component: defineAsyncComponent(() => import(`../page/personal/index.vue`)),
            meta: {
                title: '开发者详情',
            },
        },
        {
            path: '/search',
            name: 'search',
            component: defineAsyncComponent(() => import(`../page/search/index.vue`)),
        },
        {
            path: '/:catchAll(.*)',
            redirect: '/scholarSearch',
        },
        {
            path: '/',
            redirect: '/Board',
        }

    ]
})

router.beforeEach((to, from, next)=>{
    setNav(false); // 切换时先隐藏导航条
    next()
})

router.afterEach((to, from)=>{
    //切换路由成功
    //console.log('change page succeed')
})

export default router
