<template>
    <div class="image-container">
        <img src="../assets/comment.png" @click="handleComment" class="image" alt="Icon"/>
        <img v-if="!isStar" src="../assets/star.png" @click="starArticle" class="image" alt="Icon"/>
        <img v-if="isStar" src="../assets/star-fill.png" @click="unStarArticle" class="image" alt="Icon"/>
        <img src="../assets/share.png" @click="shareVisible = true" class="image" alt="Icon"/>
        <img src="../assets/quote.png" @click="quoteVisible = true" class="image" alt="Icon"/>
        <el-dialog
            v-model="quoteVisible"
            title="引用格式"
            width="500"
        >
            <template #footer>
                <div class="dialog-footer">
                    <div v-for="(quote, index) in quotes" :key="index">
                        <div class="info">
                            <span class="info-title">{{ quote.style }}</span>
                            <span class="quote-text">{{ quote.text }}</span>
                            <el-button @click="copyQuoteToClipboard(index)" style="align-items: center">复制到剪切板</el-button>
                        </div>
                    </div>
                </div>
            </template>
        </el-dialog>
        <el-dialog
            v-model="shareVisible"
            title="分享给他人"
            width="500"
        >
            <template #footer>
                <div class="dialog-footer">
                    <div class="info">
                        <span class="info-title">我在SSSR看到了很棒的学术论文《{{ title }}》，你也来看看吧</span>
                        <el-button @click="copyShareToClipboard" style="align-items: center">复制到剪切板</el-button>
                    </div>
                </div>
            </template>
        </el-dialog>
        <el-dialog
            v-model="starVisible"
            title="添加至收藏夹"
            width="500"
        >
            <template #footer>
                <div class="dialog-footer">
                    <div class="info">
                        <span class="info-title">选择添加至收藏夹</span>
                        <el-select
                            v-model="chooseId"
                            clearable
                            placeholder="Select"
                            style="width: 240px"
                        >
                            <el-option
                                v-for="item in categories"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id"
                            />
                        </el-select>
                        <el-button @click="handleStar" style="align-items: center">确定收藏</el-button>
                    </div>
                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script lang="ts">
import {ref} from "vue";
import {callError, callSuccess} from "@/call";

export default {
    name: "function-bar",
    props :{
      title: '默认题目'
    },
    methods: {
    },
    setup() {
        const chooseId = ref('')
        let quotes = ref([
            {
                id: 0,
                style: "GB/T 7714-2015",
                text: "[1]周文,许凌云.论新质生产力：内涵特征与重要着力点[J].改革,2023,(10):1-13.\n",
            },
            {
                id: 1,
                style: "MIT",
                text: "引用方式2",
            }
        ]);
        let categories = ref([
            {
                id: 0,
                name: "Deep Learning"
            },
            {
                id: 1,
                name: "Machine Learning",
            }
        ])
        const quoteVisible = ref(false)
        const shareVisible = ref(false);
        let starVisible = ref(false);
        let isStar = ref(0);
        function starArticle() {
            starVisible.value = true;
            console.log('收藏/取消收藏');
        }
        function unStarArticle() {
            isStar.value ^= 1;
            callSuccess("取消收藏成功");
        }
        function handleStar() {
            callSuccess("收藏成功");
            isStar.value ^= 1;
        }
        function handleComment() {
            console.log('评论');
        }
        async function copyQuoteToClipboard(index) {
            try {
                const textToCopy = quotes.value[index].text;
                await navigator.clipboard.writeText(textToCopy);
                callSuccess("复制成功");
            } catch (error) {
                callError("复制失败");
            }
        }
        async function copyShareToClipboard() {
            try {
                const textToCopy = "我在SSSR看到了很棒的学术论文《" + `$this.title` + "》，你也来看看吧";
                await navigator.clipboard.writeText(textToCopy);
                callSuccess("复制成功");
            } catch (error) {
                callError("复制失败");
            }
        }
        return {
            isStar,
            starArticle,
            unStarArticle,
            handleComment,
            quoteVisible,
            quotes,
            copyQuoteToClipboard,
            shareVisible,
            copyShareToClipboard,
            starVisible,
            chooseId,
            categories,
            handleStar,
        };
    }
}
</script>

<style scoped>
.image-container {
    display: inline-block;
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

.info {
    padding: 15px;
    display: flex;
    flex-direction: column;
    gap: 10px;
    max-width: 400px;
}

.quote-text {
    font-size: 14px;
    text-align: left;
}

.info-title {
    font-size: 16px;
    font-weight: bold;
    text-align: left;
}


</style>
