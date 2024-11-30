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
                    <img src="../assets/comment.png" @click="dialogVisible = true" class="image" alt="Icon"/>
                    <el-dialog
                        v-model="dialogVisible"
                        title="回复评论"
                        width="500"
                        :before-close="handleClose"
                    >
                        <textarea v-model="userInput" placeholder="请输入信息" class="custom-textarea"></textarea>
                        <template #footer>
                            <div class="dialog-footer">
                                <el-button @click="dialogVisible = false">Cancel</el-button>
                                <el-button type="primary" @click="commitComment">
                                    Confirm
                                </el-button>
                            </div>
                        </template>
                    </el-dialog>
                    <img v-if="!comments[index].liked" src="../assets/un-liked.png" @click="likeComment(index)" class="image" alt="Icon"/>
                    <img v-if="comments[index].liked" src="../assets/liked.png" @click="likeComment(index)" class="image" alt="Icon"/>
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
import {ElMessageBox} from "element-plus";
import {ref} from "vue";

export default {
    name: "comments",
    setup() {
        const dialogVisible = ref(false)
        let userInput=ref("");
        const handleClose = (done: () => void) => {
            ElMessageBox.confirm('确定放弃评论?')
                .then(() => {
                    done()
                })
                .catch(() => {

                })
        }
        function likeComment(index) {
            console.log(`点在评论 ${index}`);
            comments.value[index].liked^=1;
            comments.value[index].likes+=comments.value[index].liked?1:-1;
        }
        function commitComment() {
            dialogVisible.value = false;
            let newComment={
                user: '新用户',
                likes: 0,
                replies: 0,
                time: new Date().toLocaleTimeString(),
                content: userInput.value,
                liked: 0,
            }
            comments.value.push(newComment);
            console.log("发布评论");
        }
        let comments = ref([
            {
                user: '用户1',
                likes: 5,
                replies: 2,
                time: '2024-11-01 12:00',
                content: '这是第一条评论的内容。',
                liked: 0,
            },
            {
                user: '用户2',
                likes: 3,
                replies: 1,
                time: '2024-11-01 14:00',
                content: '这是第二条评论的内容。',
                liked: 0,
            }
        ]);
        return {
            comments,
            userInput,
            likeComment,
            dialogVisible,
            handleClose,
            commitComment,
        };
    }
}
</script>

<style scoped>
.comment-container {
    width: 100%;
    /*max-width: 800px;*/
    margin: 10px;
}

.comment {
    border: 1px solid #ccc;
    margin-bottom: 10px;
    width: 90%;
    padding: 10px;
    border-radius: 5px;
}

.comment-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

.comment-info {
    display: flex;
    gap: 10px;
}

.comment-actions {
    display: flex;
    gap: 10px;
}

.comment-body p {
    margin: 0;
}

.image {
    width: 32px;
    height: 32px;
    cursor: pointer;
    transition: transform 0.3s ease;
    margin: 5px;
}

.image:hover {
    transform: scale(1.2);
}

.small-image {
    width: 20px;
    height: 20px;
    cursor: pointer;
    transition: transform 0.3s ease;
    margin: 5px;
}

.small-image:hover {
    transform: scale(1.2);
}

.custom-textarea {
    width: 95%;
    height: 150px;
    padding: 10px;
    font-size: 16px;
    line-height: 1.5;
    border: 1px solid #ccc;
    border-radius: 5px;
    resize: vertical;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    overflow-y: auto;
}
.user {
    font-weight: bold;
}

.likes {
    font-weight: bold;
}

.replies {
    font-weight: bold;
}

.time {
    color: #6c757d;
}

.pagination-container {
    display: flex;
    justify-content: center;
    margin-top: 20px; /* 可选：添加一些顶部间距 */
}

</style>
