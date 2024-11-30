<template>
    <div class="bg-container"/>

    <div :class="{'root-set': !notHead, 'root-set-change': notHead}">

        <div style="width: 100%; height: 100%; display: flex; position: absolute"
        :class="{'zind': notHead, 'zind2': !notHead}">
            <div :class="{'bar-set': !notHead, 'bar-set-change': notHead}">
                <search_title :cond="notHead"/>
            </div>
        </div>

        <div :class="{'layer-set': !notHead, 'layer-set-change': notHead}">

            <div style="margin-top: auto; margin-left: auto; height: 150px;
            display: flex; flex-direction: row; align-items: center;"
            :class="{'msg-set': !notHead, 'msg-set-change': notHead}">

                <fade-box>
                    <home-msg name="学术成果总数" value="197942" photo="book-cover"/>
                </fade-box>

                <fade-box>
                    <home-msg name="门户总数" value="2076748" photo="histogram"/>
                </fade-box>

            </div>

        </div>

    </div>

    <div class="view-set-margin">
        <div class="content-container">

            <div class="content-left">

                <fade-box style="margin-bottom: 25px">
                    <logo/>
                </fade-box>

<!--                <fade-box>-->
                <left-pin/>
<!--                </fade-box>-->

            </div>

            <div class="content-right">
                <fade-box>
                    <article-box v-for="item in articleList" :abstract="item.abstract"
                    :title="item.title" :post-time="item.postTime"
                    :author="item.author"/>
                </fade-box>

                <el-pagination class="pagination-style"
                               v-model:current-page="currentPos"
                               ref="bottomPagination"
                               layout="prev, pager, next"
                               @current-change="pageChange()"
                               :total="10*totalPage"
                               background
                />

            </div>
        </div>

        <home-bottom/>
    </div>



    <el-backtop :right="50" :bottom="50" />

</template>


<script lang="ts">

import Search_title from "@/page/home/component/search_title/index.vue";
import {onMounted, onUnmounted, ref} from "vue";
import {reactive} from "vue";
import articleBox from "@/page/home/component/articleBox/index.vue";
import fadeBox from "@/page/home/component/fadeBox/index.vue";
import logo from "@/page/home/component/logo/index.vue"
import homeBottom from "@/page/home/component/homeBottom/index.vue"
import leftPin from "@/page/home/component/leftPin/index.vue";
import homeMsg from "@/page/home/component/homeMsg/index.vue";
import store from "@/store/index.js";
import {reverseNav, setNav} from "@/nav/set";

export default {
    name: "home",
    components: {Search_title, articleBox, fadeBox, logo, homeBottom, leftPin, homeMsg},
    setup(){

        const currentPos = ref(1);
        const totalPage = ref(10);
        const pageChange = (): void => {

        }

        const step = ref(true);
        const notHead = ref(false);
        const articleList = ref([
            {
                title: "Cuckoo filter: Practically better than bloom",
                abstract: "In many networking systems, Bloom filters are used for high-speed set membership tests. They permit a small fraction of false positive answers with very good space efficiency. However, they do not permit deletion of items from the set, and previous attempts to extend \"standard\" Bloom filters to support deletion all degrade either space or performance.We propose a new data structure called the cuckoo filter that can replace Bloom filters for approximate set membership tests. Cuckoo filters support adding and removing items dynamically while achieving even higher performance than Bloom filters. For applications that store many items and target moderately low false positive rates, cuckoo filters have lower space overhead than space-optimized Bloom filters. Our experimental results also show that cuckoo filters outperform previous data structures that extend Bloom filters to support deletions substantially in both time and space.",
                postTime: "2014.07.20",
                author: "M Jaderberg, K Simonyan… - Advances in neural …"
            },
            {
                title: "Spatial transformer networks",
                abstract: "In many networking systems, Bloom filters are used for high-speed set membership tests. They permit a small fraction of false positive answers with very good space efficiency. However, they do not permit deletion of items from the set, and previous attempts to extend \"standard\" Bloom filters to support deletion all degrade either space or performance.We propose a new data structure called the cuckoo filter that can replace Bloom filters for approximate set membership tests. Cuckoo filters support adding and removing items dynamically while achieving even higher performance than Bloom filters. For applications that store many items and target moderately low false positive rates, cuckoo filters have lower space overhead than space-optimized Bloom filters. Our experimental results also show that cuckoo filters outperform previous data structures that extend Bloom filters to support deletions substantially in both time and space.",
                postTime: "2014.07.20",
                author: "M Jaderberg, K Simonyan… - Advances in neural …"
            },
            {
                title: "Transformer networks for trajectory forecasting",
                abstract: "In many networking systems, Bloom filters are used for high-speed set membership tests. They permit a small fraction of false positive answers with very good space efficiency. However, they do not permit deletion of items from the set, and previous attempts to extend \"standard\" Bloom filters to support deletion all degrade either space or performance.We propose a new data structure called the cuckoo filter that can replace Bloom filters for approximate set membership tests. Cuckoo filters support adding and removing items dynamically while achieving even higher performance than Bloom filters. For applications that store many items and target moderately low false positive rates, cuckoo filters have lower space overhead than space-optimized Bloom filters. Our experimental results also show that cuckoo filters outperform previous data structures that extend Bloom filters to support deletions substantially in both time and space.",
                postTime: "2014.07.20",
                author: "M Jaderberg, K Simonyan… - Advances in neural …"
            },
            {
                title: "A generalization of transformer networks to graphs",
                abstract: "In many networking systems, Bloom filters are used for high-speed set membership tests. They permit a small fraction of false positive answers with very good space efficiency. However, they do not permit deletion of items from the set, and previous attempts to extend \"standard\" Bloom filters to support deletion all degrade either space or performance.We propose a new data structure called the cuckoo filter that can replace Bloom filters for approximate set membership tests. Cuckoo filters support adding and removing items dynamically while achieving even higher performance than Bloom filters. For applications that store many items and target moderately low false positive rates, cuckoo filters have lower space overhead than space-optimized Bloom filters. Our experimental results also show that cuckoo filters outperform previous data structures that extend Bloom filters to support deletions substantially in both time and space.",
                postTime: "2014.07.20",
                author: "M Jaderberg, K Simonyan… - Advances in neural …"
            },
            {
                title: "Contextual transformer networks for visual recognition",
                abstract: "In many networking systems, Bloom filters are used for high-speed set membership tests. They permit a small fraction of false positive answers with very good space efficiency. However, they do not permit deletion of items from the set, and previous attempts to extend \"standard\" Bloom filters to support deletion all degrade either space or performance.We propose a new data structure called the cuckoo filter that can replace Bloom filters for approximate set membership tests. Cuckoo filters support adding and removing items dynamically while achieving even higher performance than Bloom filters. For applications that store many items and target moderately low false positive rates, cuckoo filters have lower space overhead than space-optimized Bloom filters. Our experimental results also show that cuckoo filters outperform previous data structures that extend Bloom filters to support deletions substantially in both time and space.",
                postTime: "2014.07.20",
                author: "M Jaderberg, K Simonyan… - Advances in neural …"
            },
            {
                title: "Video action transformer network",
                abstract: "In many networking systems, Bloom filters are used for high-speed set membership tests. They permit a small fraction of false positive answers with very good space efficiency. However, they do not permit deletion of items from the set, and previous attempts to extend \"standard\" Bloom filters to support deletion all degrade either space or performance.We propose a new data structure called the cuckoo filter that can replace Bloom filters for approximate set membership tests. Cuckoo filters support adding and removing items dynamically while achieving even higher performance than Bloom filters. For applications that store many items and target moderately low false positive rates, cuckoo filters have lower space overhead than space-optimized Bloom filters. Our experimental results also show that cuckoo filters outperform previous data structures that extend Bloom filters to support deletions substantially in both time and space.",
                postTime: "2014.07.20",
                author: "M Jaderberg, K Simonyan… - Advances in neural …"
            },
            {
                title: "Inverse compositional spatial transformer networks",
                abstract: "In many networking systems, Bloom filters are used for high-speed set membership tests. They permit a small fraction of false positive answers with very good space efficiency. However, they do not permit deletion of items from the set, and previous attempts to extend \"standard\" Bloom filters to support deletion all degrade either space or performance.We propose a new data structure called the cuckoo filter that can replace Bloom filters for approximate set membership tests. Cuckoo filters support adding and removing items dynamically while achieving even higher performance than Bloom filters. For applications that store many items and target moderately low false positive rates, cuckoo filters have lower space overhead than space-optimized Bloom filters. Our experimental results also show that cuckoo filters outperform previous data structures that extend Bloom filters to support deletions substantially in both time and space.",
                postTime: "2014.07.20",
                author: "M Jaderberg, K Simonyan… - Advances in neural …"
            },
            {
                title: "Bloom filter encryption and applications to efficient forward-secret 0-RTT key exchange",
                abstract: "In many networking systems, Bloom filters are used for high-speed set membership tests. They permit a small fraction of false positive answers with very good space efficiency. However, they do not permit deletion of items from the set, and previous attempts to extend \"standard\" Bloom filters to support deletion all degrade either space or performance.We propose a new data structure called the cuckoo filter that can replace Bloom filters for approximate set membership tests. Cuckoo filters support adding and removing items dynamically while achieving even higher performance than Bloom filters. For applications that store many items and target moderately low false positive rates, cuckoo filters have lower space overhead than space-optimized Bloom filters. Our experimental results also show that cuckoo filters outperform previous data structures that extend Bloom filters to support deletions substantially in both time and space.",
                postTime: "2014.07.20",
                author: "M Jaderberg, K Simonyan… - Advances in neural …"
            },
            {
                title: "Privacy-enhanced searches using encrypted bloom filters",
                abstract: "In many networking systems, Bloom filters are used for high-speed set membership tests. They permit a small fraction of false positive answers with very good space efficiency. However, they do not permit deletion of items from the set, and previous attempts to extend \"standard\" Bloom filters to support deletion all degrade either space or performance.We propose a new data structure called the cuckoo filter that can replace Bloom filters for approximate set membership tests. Cuckoo filters support adding and removing items dynamically while achieving even higher performance than Bloom filters. For applications that store many items and target moderately low false positive rates, cuckoo filters have lower space overhead than space-optimized Bloom filters. Our experimental results also show that cuckoo filters outperform previous data structures that extend Bloom filters to support deletions substantially in both time and space.",
                postTime: "2014.07.20",
                author: "M Jaderberg, K Simonyan… - Advances in neural …"
            },
            {
                title: "Efficient search on encrypted data using bloom filter",
                abstract: "In many networking systems, Bloom filters are used for high-speed set membership tests. They permit a small fraction of false positive answers with very good space efficiency. However, they do not permit deletion of items from the set, and previous attempts to extend \"standard\" Bloom filters to support deletion all degrade either space or performance.We propose a new data structure called the cuckoo filter that can replace Bloom filters for approximate set membership tests. Cuckoo filters support adding and removing items dynamically while achieving even higher performance than Bloom filters. For applications that store many items and target moderately low false positive rates, cuckoo filters have lower space overhead than space-optimized Bloom filters. Our experimental results also show that cuckoo filters outperform previous data structures that extend Bloom filters to support deletions substantially in both time and space.",
                postTime: "2014.07.20",
                author: "M Jaderberg, K Simonyan… - Advances in neural …"
            },
        ])

        //滑动响应式组件
        const data = reactive({
            oldScrollTop:0,
        });

        const scrolling = () :void => {
            // 滚动条距文档顶部的距离
            let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
            // 滚动条滚动的距离
            let scrollStep = scrollTop - data.oldScrollTop;
            //console.log("header 滚动距离 ", scrollTop);
            // 更新——滚动前，滚动条距文档顶部的距离
            data.oldScrollTop = scrollTop;
            //变量windowHeight是可视区的高度
            let windowHeight =
                document.documentElement.clientHeight || document.body.clientHeight;
            //变量scrollHeight是滚动条的总高度
            let scrollHeight =
                document.documentElement.scrollHeight || document.body.scrollHeight;
            //滚动条到底部的条件
            if (scrollTop + windowHeight === scrollHeight) {
                //到达底部
            }

            if (scrollStep < 0) {
                //向上滚动
                step.value = true;
                setNav(true);
            } else {
                //向下滚动
                step.value = false;
                if (!notHead.value) {
                    notHead.value = true;
                    setNav(true);
                }else {
                    setNav(false);
                }
            }
            // 判断是否到了最顶部
            if (scrollTop <= 0) {
                notHead.value = false;
                setNav(false);
            }

        }

        onMounted(() => {
            window.addEventListener("scroll", scrolling);
            setNav(false);
        })

        onUnmounted(() => {
            window.removeEventListener("scroll", scrolling);
        })

        return {
            notHead,
            articleList,
            currentPos,
            totalPage,
            pageChange
        };

    }
}
</script>

<style scoped>

.content-container{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 1200px;
    height: auto;
}

.bg-container {
    background: url('@/asset/home/bg.png');
    /*opacity: 0.8;*/
    background-size: cover;
    position: fixed;
    height: 100vh;
    width: 100vw;
    z-index: -3;
    top: 0;
    left: 0;
}

.root-set{
    height: 100vh;
    width: 100%;
    /*display: flex;*/
    transition: height 0.8s;
    position: relative;
}

.root-set-change{
    height: 0;
    width: 100%;
    /*display: flex;*/
    transition: height 0.8s;
}

.msg-set{
    transition: opacity 0.3s;
}

.msg-set-change{
    opacity: 0;
    transition: opacity 0.3s;
    z-index: -2;
}

.bar-set{
    margin: auto;
    /*width: 100%;*/
    transition: opacity 0.6s;
}

.bar-set-change{
    margin: auto;
    opacity: 0;
    transition: opacity 0.6s;
    z-index: -2;
}

.content-right{
    width: 70%;
    display: flex;
    flex-direction: column;
    gap: 20px;
}
.content-left{
    width: 28%;
}

.view-set-margin{
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 150px;
}
.pagination-style{
    justify-content: center;
}

.layer-set{
    z-index: 1;
    width: 100%;
    height: 100%;
    position: absolute;
    display: flex
}

.layer-set-change{
    z-index: -2;
    width: 100%;
    height: 100%;
    position: absolute;
    display: flex
}

.zind{
    z-index: -2;
}

.zind2{
    z-index: 2;
}

</style>
