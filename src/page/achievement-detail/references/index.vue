<template>
    <div class="references">
        <ul>
            <li v-for="paper in papers" :key="paper.id">
                [{{ paper.id }}] {{ paper.title }}
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

    },
    setup() {
        const views = 99, likes = 824, comments = 4353, favorites = 1e9+7;
        const papers = [
            {
                id: 1,
                title: 'Advanced Characterization Techniques for Thin Film Solar Cells',
                authors: 'Daniel Abou-Ras, Thomas Kirchartz, Uwe Rau'
            },
            {
                id: 2,
                title: 'Advanced Characterization Techniques for Thin Film Solar Cells',
                authors: 'Daniel Abou-Ras, Thomas Kirchartz, Uwe Rau'
            },
            {
                id: 3,
                title: 'Advanced Characterization Techniques for Thin Film Solar Cells',
                authors: 'Daniel Abou-Ras, Thomas Kirchartz, Uwe Rau'
            },
            {
                id: 4,
                title: 'Advanced Characterization Techniques for Thin Film Solar Cells',
                authors: 'Daniel Abou-Ras, Thomas Kirchartz, Uwe Rau'
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
