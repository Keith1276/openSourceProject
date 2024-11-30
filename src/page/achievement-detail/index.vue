<template>
    <div class="bg-container"/>
    <div class="bg-strong-container"/>
    <el-container class="el-main" style="display: flex">
        <el-row>
            <el-col :span="18">
                <div class="main-container">
                    <div class="header-container">
                        <div class="header-title">{{title}}</div>
                        <div class="info-container">
                            <div class="detail-info">
                                <span class="info-label">作者：</span>
                                <span v-for="(author, index) in authors" :key="index"
                                      class="author-link"
                                      @click="goToLink(index)">{{ author }}{{ index < authors.length - 1 ? ', ' : '' }}</span>
                            </div>
                            <div class="detail-info">
                                <span class="info-label">发表时间：</span>
                                <span>{{date}}</span>
                            </div>
                            <div class="detail-info">
                                <span class="info-label">关键词：</span>
                                <span v-for="(author, index) in keywords" :key="index">{{ author }}{{ index < keywords.length - 1 ? ', ' : '' }}</span>
                            </div>
                            <div class="detail-info">
                                <span class="info-label">机构：</span>
                                <span>{{institution}}</span>
                            </div>
                            <div class="detail-info">
                                <span class="info-label">来源：</span>
                                <span>{{source}}</span>
                            </div>
                        </div>
                        <function-bar/>
                    </div>
                    <div class="down-container">
                        <div class="abstract-title">摘要</div>
                        <div class="abstract-content">
                            {{abstract}}
                        </div>
                    </div>
                    <div class="down-container">
                        <el-tabs
                            v-model="activeName"
                            type="card"
                            class="demo-tabs"
                            @tab-click="handleClick" style="">
                            <el-tab-pane label="参考文献" name="first">
                                <references/>
                            </el-tab-pane>
                            <el-tab-pane label="引证文献" name="second">
                                <citing/>
                            </el-tab-pane>
                            <el-tab-pane label="评论" name="third">
                                <comments/>
                            </el-tab-pane>
                        </el-tabs>
                    </div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="side-container">
                    <side-component/>
                </div>
            </el-col>

        </el-row>
    </el-container>
</template>

<script lang="ts">
import SideComponent from "@/page/achievement-detail/side-component/index.vue";
import {ref} from "vue";
import FunctionBar from "@/page/achievement-detail/function-bar/index.vue";
import References from "@/page/achievement-detail/references/index.vue";
import Citing from "@/page/achievement-detail/citing/index.vue";
import Comments from "@/page/achievement-detail/comments/index.vue";

export default {
    name: "achievement-detail",
    components: {Comments, Citing, References, FunctionBar, SideComponent},
    setup() {
        const title = "How to write front-end";
        const authors = ["作者1", "作者2"];
        const links = ["https://www.baidu.com", "https://codeforces.com"];
        const keywords = ["copy", "paste"];
        const abstract = "You want to build a computer to achieve a specific functionality: Given an integer x, determine whether x lies within the interval [LR]. To accomplish this, you designed a directed acyclic graph (DAG) with edge weights of 0 and 1, which contains a starting node with an indegree of 0 and an ending node with an outdegree of 0. By starting from the starting node and following a path to the ending node, the sequence of the traversed edge weights forms a binary representation of an integer within the range [LR] without leading zeros. Every integer within the range [LR] must correspond to exactly one unique path in this graph. In this way, you can determine whether an integer lies within the range [LR] by checking if its binary representation can be constructed by traversing this DAG.";
        const date = "2024-10-31 20:00";
        const institution = "Bei Hang Da Xue";
        const source = "Bei Hang Xue Bao";
        const activeName = ref('first')
        function goToLink(index) {
            window.open(links[index]);
        }
        function handleClick() {
            console.log("clicked");
        }
        return {
            authors,
            keywords,
            title,
            abstract,
            date,
            institution,
            source,
            activeName,
            goToLink,
            handleClick
        };
    }
}
</script>

<style scoped>
@import "style.css";
</style>
