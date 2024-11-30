<template>
    <div class="stats">
        <div class="stat">
            <div>阅读量</div>
            <div :style="{ color: getColor(views) }">{{ formatNumber(views) }}</div>
        </div>
        <div class="stat">
            <div>点赞数</div>
            <div :style="{ color: getColor(likes) }">{{ formatNumber(likes) }}</div>
        </div>
        <div class="stat">
            <div>评论数</div>
            <div :style="{ color: getColor(comments) }">{{ formatNumber(comments) }}</div>
        </div>
        <div class="stat">
            <div>收藏数</div>
            <div :style="{ color: getColor(favorites) }">{{ formatNumber(favorites) }}</div>
        </div>
    </div>
    <hr>
    <div class="field-list">
        <p class="small-title">领域</p>
        <div v-for="field in fields" :key="field.name" class="field" @click="goToUrl(field.url)">
            - {{ field.name }}
        </div>
    </div>
    <hr>
    <div class="field-list">
        <p class="small-title">相关文章</p>
        <div v-for="field in fields" :key="field.name" class="field" @click="goToUrl(field.url)">
            - {{ field.name }}
        </div>
    </div>

</template>

<script lang="ts">
export default {
    name: "side-component",
    methods: {
        getColor(value) {
            if (value < 10) {
                return '#a3bbdb';
            }
            else if (value < 100) {
                return '#4994c4';
            }
            else if (value < 1000) {
                return '#2e59a7';
            }
            else if(value < 10000) {
                return '#003d74';
            }
            else {
                return '#2e59a7';
            }
        },
        formatNumber(value) {
            if (value >= 1000000) {
                return (value / 1000000).toFixed(1) + ' M';
            } else if (value >= 1000) {
                return (value / 1000).toFixed(1) + ' K';
            } else {
                return value.toString();
            }
        },
        goToUrl(url) {
            window.location.href = url;
        }
    },
    setup() {
        const views = 99, likes = 824, comments = 4353, favorites = 1e9+7;
        const fields = [
            { name: "技术", url: "https://www.baidu.com" },
            { name: "科学", url: "https://www.baidu.com" },
            { name: "健康", url: "https://www.baidu.com" },
            { name: "教育", url: "https://www.baidu.com" }
        ];
        const articles = [
            { name: "技术", url: "https://www.baidu.com" },
            { name: "科学", url: "https://www.baidu.com" },
            { name: "健康", url: "https://www.baidu.com" },
            { name: "教育", url: "https://www.baidu.com" }
        ];
        return {
            views,
            likes,
            comments,
            favorites,
            fields,
            articles
        };
    }
}
</script>

<style scoped>
.stats {
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 100px;
    padding: 10px;
    box-sizing: border-box;
    margin-top: 10px;
    align-items: center;
}
.stat {
    text-align: center;
    line-height: 1.5;
    padding: 5px;
    box-sizing: border-box;
}
.small-title {
    text-align: left;
    font-weight: bold;
    font-size: 20px;
}
.field-list {
    padding: 5px;
}
.field {
    margin: 5px 0;
    cursor: pointer;
    text-align: left;
    font-size: 16px;
    color: #6e9bc5;
    transition: color 0.3s;
}
.field:hover {
    color: #106898;
}
hr {
    border: 0;
    height: 1px;
    background: #ccc;
    margin: 20px 0;
}

</style>
