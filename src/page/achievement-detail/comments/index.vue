<template>
    <div class="comment-container">
        <div v-for="(comment, index) in comments" :key="index" class="comment">
            <div class="comment-header">
                <div class="comment-info">
                    <img src="../assets/avatar.png" class="small-image" alt="Icon"/>
                    <span class="user">{{ comment.user }}</span>
                    <span class="likes">{{ comment.likes }} 赞</span>
                    <span class="replies">{{ comment.replies }} 回复</span>
                    <span class="time">{{ comment.time }}</span>
                </div>
                <div class="comment-actions">
                    <img src="../assets/comment.png" @click="replyToComment(index)" class="image" alt="Icon"/>
                    <img src="../assets/like.png" @click="likeComment(index)" class="image" alt="Icon"/>
                </div>
            </div>
            <div class="comment-body">
                <p>{{ comment.content }}</p>
            </div>
        </div>
        <div class="pagination-container">
            <el-pagination layout="prev, pager, next" :total="1000" />
        </div>
    </div>
</template>

<script lang="ts">
export default {
    name: "comments",
    setup() {
        function likeComment(index) {
            console.log(`点在评论 ${index}`);
        }
        function replyToComment(index) {
            console.log(`回复评论 ${index}`);
        }
        let comments = [
            {
                user: '用户1',
                likes: 5,
                replies: 2,
                time: '2024-11-01 12:00',
                content: '这是第一条评论的内容。'
            },
            {
                user: '用户2',
                likes: 3,
                replies: 1,
                time: '2024-11-01 14:00',
                content: '这是第二条评论的内容。'
            }
        ];
        return {
            comments,
            likeComment,
            replyToComment,
        };
    }
}
</script>

<style scoped>
@import "style.css";
</style>
