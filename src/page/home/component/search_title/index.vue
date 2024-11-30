<template>
    <div style="display: flex; flex-direction: column;">

        <vuetyped :class="{'title-font': !cond, 'change-font': cond}"
                  :strings="title_str" :type-speed="20" :show-cursor="false">
            <div class="typing"/>
        </vuetyped>

        <search_bar :cond="cond" style="margin-left: auto; margin-right: auto; margin-top: 25px;"/>

    </div>
</template>

<script lang="ts">

import Search_bar from "@/page/home/component/search_bar/index.vue";
import {ref} from "vue";

export default {
    name: "search_title",
    components: {Search_bar},
    props: {
        'cond' : Boolean
    },
    setup(){
        const title_str = ref(['Significant Shared Science Research']);
        return {
            title_str
        };
    }
}

</script>

<style scoped>
.title-font {
    font-family: 'Meiryo', sans-serif;
    font-weight: bold;
    font-size: 32px;
    transition: font-size 0.6s;
    color: rgba(255, 255, 255, 0.9);
    margin: auto;
}

.change-font {
    font-family: 'Meiryo', sans-serif;
    font-weight: bold;
    font-size: 16px;
    transition: font-size 0.6s;
    color: rgba(255, 255, 255, 0.9);
    margin: auto;
}

</style>
