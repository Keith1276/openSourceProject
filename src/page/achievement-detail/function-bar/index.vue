<template>
    <div class="image-container">
        <img src="../assets/comment.png" @click="handleComment" class="image" alt="Icon"/>
        <img v-if="!isStar" src="../assets/star.png" @click="handleStar" class="image" alt="Icon"/>
        <img v-if="isStar" src="../assets/star-fill.png" @click="handleStar" class="image" alt="Icon"/>
        <img src="../assets/share.png" @click="handleShare" class="image" alt="Icon"/>
        <img src="../assets/quote.png" @click="handleQuote" class="image" alt="Icon"/>
    </div>
</template>

<script lang="ts">
import {ref} from "vue";

export default {
    name: "function-bar",
    methods: {
    },
    setup() {
        const isStar = ref(0);
        function handleStar() {
            isStar.value ^= 1;
            console.log('收藏/取消收藏');
        }
        function handleComment() {
            console.log('评论');
        }
        function handleShare() {
            console.log('评论');
        }
        function handleQuote() {
            console.log('引用');
        }
        return {
            isStar,
            handleStar,
            handleComment,
            handleShare,
            handleQuote
        };
    }
}
</script>

<style scoped>
@import "style.css";
</style>
