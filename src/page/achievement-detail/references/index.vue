<template>
    <div class="references">
        <ul>
            <li v-for="paper in papers" :key="paper.id">
                [{{ paper.id }}] <a :href="paper.url" @click.prevent="navigateTo(paper.url)" class="paper-link">
                    {{ paper.title }}
                </a>
                <br>
                {{ paper.authors }}
            </li>
        </ul>
    </div>
</template>

<script lang="ts">
export default {
    name: "references",
    methods: {
        navigateTo(url) {
            // window.open(url, '_blank');
        }
    },
    setup() {
        const papers = [
            {
                id: 1,
                title: 'Advanced Characterization Techniques for Thin Film Solar Cells',
                authors: 'Daniel Abou-Ras, Thomas Kirchartz, Uwe Rau',
                url: 'www.baidu.com',
            },
            {
                id: 2,
                title: 'Advanced Characterization Techniques for Thin Film Solar Cells',
                authors: 'Daniel Abou-Ras, Thomas Kirchartz, Uwe Rau',
                url: 'www.baidu.com',
            },
            {
                id: 3,
                title: 'Advanced Characterization Techniques for Thin Film Solar Cells',
                authors: 'Daniel Abou-Ras, Thomas Kirchartz, Uwe Rau',
                url: 'www.baidu.com',
            },
            {
                id: 4,
                title: 'Advanced Characterization Techniques for Thin Film Solar Cells',
                authors: 'Daniel Abou-Ras, Thomas Kirchartz, Uwe Rau',
                url: 'www.baidu.com',
            },
            // ... 其余文献
        ];
        return {
            papers
        };
    }
}
</script>

<style scoped>
@import "style.css";
</style>
