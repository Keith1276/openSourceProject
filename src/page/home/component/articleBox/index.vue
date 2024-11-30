<template>
    <div class="box-container" @mouseenter="hover=true" @mouseleave="hover=false">

        <div class="msg">
            <div :class="{ titleFont: true }">
                {{title.length > 60 ? title.slice(0, 60) + '...' : title  }}
                <div v-for="item in tagList" class="tagSet">
                    {{item}}
                </div>
            </div>
            <div :class="{ abstractFont: true }" >{{abstract.slice(0, 512)+'...'}}</div>
            <div class="otherMsg">
                <div :class="{ otherMsgFont: true }">{{"author: "+author}}</div>
                <div :class="{ otherMsgFont: true }">{{" | time: "+postTime}}</div>
            </div>
        </div>

    </div>

</template>

<script lang="ts">

import {ref} from "vue";
import { useStore } from "vuex";
import {callInfo} from "@/call";

export default {
    name: "articleBox",
    props:['title', 'abstract', 'author', 'postTime', 'id', 'tags'],
    setup(){
        const tagList = ref(["IEEE"])
        const hover = ref(false)

        return {
            tagList,
            hover
        }

    }
}
</script>

<style scoped>
.box-container{
    height: 130px;
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
    cursor: pointer;
    transition: background-color 0.5s;
    background-color: rgba(255, 255, 255, 0.92);
    border-top: rgba(142, 142, 142, 0.4) 1px solid;
    border-bottom: rgba(142, 142, 142, 0.4) 1px solid;
}

.box-container:hover{
    height: 130px;
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
    cursor: pointer;
    background-color: rgba(255, 255, 255, 0.6);
    transition: background-color 0.5s;
}

.msg{
    display: flex;
    flex-direction: column;
    justify-content: left;
    width: 97%;
    height: 96%;
}
.otherMsg{
    display: flex;
    flex-direction: row;
    justify-content: left;
    margin-top: auto;
    align-items: center;
}
.titleFont {
    font-family: 'Meiryo', sans-serif;
    font-weight: bold;
    font-size: 22px;
    color: #00405f;
    margin-left: 5px;
    text-align: left;
    display: flex;
    flex-direction: row;
    align-items: center;
}
.abstractFont {
    font-family: 'Meiryo', sans-serif;
    font-size: 12px;
    margin-left: 7px;
    color: #646464;
    text-align: left;
}
.otherMsgFont {
    font-family: 'Meiryo', sans-serif;
    font-size: 14px;
    margin-left: 5px;
    color: #8e8e8e;
    text-align: left;
}
.setHr{
    border-top-color: rgba(44, 44, 44, 0.1);
}

.tagSet{
    padding-left: 5px;
    padding-right: 5px;
    margin-left: 3px;
    height: 60%;
    min-width: 0;
    display: flex;
    flex-direction: row;
    background-color: #def2ff;
    border-radius: 6px;
    justify-content: center;
    align-items: center;
    cursor: default;
    font-size: 10px;
}

.otherMsgFontDelete {
    font-family: '微软雅黑', 'Microsoft YaHei', sans-serif;
    font-size: 10px;
    font-weight: bold;
    color: #ff8e8e;
    margin-left: 10px;
    text-align: left;
}
</style>
