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

        {
            path:'/administrator',
            name:'administrator',
            component: defineAsyncComponent(() => import(`../page/administrator/index.vue`)),
            meta:{
                title: '管理员界面',
            }
        },
        //学者搜索页面路由
        {
            path: '/scholarSearch/:input/:select',
            name: 'scholarSearch',
            component: defineAsyncComponent(() => import(`../page/scholarSearch/index.vue`)),
            meta: {
                title: '学者搜索',
            },
        },
        //学者搜索引导页
        {
            path: '/scholarAccess',
            name: 'scholarAccess',
            component: defineAsyncComponent(() => import(`../page/scholarAccess/index.vue`)),
            meta: {
                title: '学者搜索引导',
            },
        },

        {
            path:'/newLogin',
            name:'newLogin',
            component: defineAsyncComponent(() => import(`../page/newLogin/index.vue`)),
            meta: {
                title: '登录'
            }
        },
        {
            path: '/search',
            name: 'search',
            component: defineAsyncComponent(() => import(`../page/search/index.vue`)),
        },
        //学术平台首页
        {
            path: '/home',
            name: 'home',
            component: defineAsyncComponent(() => import(`../page/home/index.vue`)),

            meta: {
                title: 'Welcome to SSSR',
            },
        },

        //  注册信息填写路由
        {
            path: '/register-info',
            name: 'register-info',
            component: defineAsyncComponent(() => import(`../page/register-info/index.vue`)),
            meta: {
                title: '注册信息填写',
            },
        },

        //  个人门户页面路由
        {
            path: '/profile',
            name: 'profile',
            component: defineAsyncComponent(() => import(`../page/profile/index.vue`)),
            meta: {
                title: '个人门户',
            },
        },

        //  个人门户-发表文献页面路由
        {
            path: '/profile_achieve',
            name: 'profile_achieve',
            component: defineAsyncComponent(() => import(`../page/profile/tabs/achieve.vue`)),
            meta: {
                title: '个人-成果',
            },
        },

        //  个人门户-数据分析页面路由
        {
            path: '/profile_analysis',
            name: 'profile_analysis',
            component: defineAsyncComponent(() => import(`../page/profile/tabs/analysis.vue`)),
            meta: {
                title: '个人-分析',
            },
        },
        {
            path: '/:catchAll(.*)',
            redirect: '/scholarSearch',
        },
        {
            path: '/',
            redirect: '/newLogin',
        }

    ]
})

router.beforeEach((to, from, next)=>{

    // const hasToken = store.getters.getToken;
    //
    // if (hasToken != null){
    //     //console.log('现在存在token：'+hasToken);
    //     if (to.path === '/login') {
    //         //已经登录了，去博客页面
    //         next({ path: '/blog' })
    //     }else{
    //         if (to.meta.title) {
    //             document.title = `${to.meta.title}`;
    //         }
    //         //直接放行
    //         next();
    //     }
    //
    // }else{
    //     //没有登录
    //     if (to.path === '/login'){
    //         //要登录，放行
    //         next();
    //     }else{
    //         window.alert('未检测到token，请重新登录');
    //         //没有登录，去登录页面
    //         next('/login');
    //         next();
    //     }
    // }

    // }else{
    //     //没有登录
    //     if (to.path === '/login'){
    //         //要登录，放行
    //         next();
    //     }else{
    //         window.alert('未检测到token，请重新登录');
    //         //没有登录，去登录页面
    //         next('/login');
    //     }
    // }
    setNav(false); // 切换时先隐藏导航条
    next()
})

router.afterEach((to, from)=>{
    //切换路由成功
    //console.log('change page succeed')
})

export default router
