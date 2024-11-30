<template>

    <div class="top-bar">

        <div style="height: 100%; display: flex; flex-direction: row;
         align-items: center; justify-content: left">
            <div class="left-topbar">
                <img src="@/asset/home/logo-black.png" alt="Logo" class="logo" />

                <div class="nav-title">
                    Significant Shared Science Research
                </div>

            </div>

            <div class="search-container">

                <el-input v-model="searchInput" style="width: 300px"
                          :suffix-icon="Search" placeholder="Search by SSSR!"
                @keydown.enter="callSearchPage"/>

            </div>

        </div>

        <div style="height: 100%; display: flex; flex-direction: row;
         align-items: center; justify-content:right; margin-right: 100px">
            <div style="border-radius: 27px;
            width: 37px;
            height: 37px;
            background-color: rgba(142,142,142,0.15)">
                <img
                    src="@/asset/home/user.png"
                    alt="Button Image"
                    class="button-image"
                />
            </div>
        </div>

    </div>

</template>

<script setup>
import { useStore } from "vuex";
import { register, resetPassword, sendEmail } from "@/api/example";
import {computed, provide, ref} from "vue";
import { callSuccess, callError, callInfo, callWarning } from "@/call";
import { Search } from '@element-plus/icons-vue'
import store from "@/store";
import router from "@/router"

const searchInput = ref('');

const callSearchPage = () => {
    if (searchInput.value.length > 100){
        callInfo('搜索内容过长');
        return;
    }
    if (searchInput.value.length === 0){
        callInfo('搜索内容不得为空');
        return;
    }
    // router.push(`/search/${searchInput.value}`);
    router.push(`/newLogin/`);
    setTimeout(()=>{
        router.push(`/search/${searchInput.value}`);
    }, 100);
}

</script>

<style scoped>


.left-topbar {
    display: flex;
    align-items: center;
    flex-direction: row;
}

.logo {
    height: 30px;
}

.search-container {
    display: flex;
    align-items: center;
    margin-left: 15px;
}

.button-image {
    width: 36px; /* Adjust the size as needed */
    height: 36px;
}

.title b,
.authors b,
.submitted b {
    font-weight: bold;
}

.submitted b{
    font-weight: bold;
    text-align: left;
}

.open .abstract-content {
    text-overflow: initial;
    white-space: normal;
    overflow: visible;
}

.top-bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1% 2% 1% 2%;
    background-color: rgba(255, 255, 255, 0.8);
    height: 40px;
    width: 100%;
}

.nav-title{
    font-family: 'Meiryo', sans-serif;
    font-size: 18px;
    margin-left: 5px;
    font-weight: bold;
}

</style>
