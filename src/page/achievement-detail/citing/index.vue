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
    name: "citing",
    methods: {
        navigateTo(url) {
            // window.open(url, '_blank');
        }
    },
    setup() {
        const views = 99, likes = 824, comments = 4353, favorites = 1e9+7;
        const papers = [
            {
                id: 1,
                title: 'Advanced Characterization Techniques for Thin Film Solar Cells',
                authors: 'Daniel Abou-Ras, Thomas Kirchartz, Uwe Rau',
                year: 2023,
                journal: 'Nature Materials',
                link: 'https://doi.org/10.1038/s41563-023-01456-y',
                url: 'www.baidu.com',
            },
            {
                id: 2,
                title: 'Efficient Perovskite-Based Tandem Solar Cells',
                authors: 'Michael Grätzel, Nam-Gyu Park',
                year: 2022,
                journal: 'Science',
                link: 'https://doi.org/10.1126/science.abf9876',
                url: 'www.baidu.com',
            },
            {
                id: 3,
                title: 'The Role of Interfaces in Organic Solar Cells',
                authors: 'Jean-Luc Brédas, Serdar Sarıçiftçi',
                year: 2021,
                journal: 'Advanced Materials',
                link: 'https://doi.org/10.1002/adma.202101234',
                url: 'www.baidu.com',
            },
            {
                id: 4,
                title: 'Recent Advances in Quantum Dot Solar Cells',
                authors: 'Victor I. Klimov, Edward H. Sargent',
                year: 2020,
                journal: 'Accounts of Chemical Research',
                link: 'https://doi.org/10.1021/acs.accounts.0c00012',
                url: 'www.baidu.com',
            },
            {
                id: 5,
                title: 'Organic-Inorganic Hybrid Perovskites for Photovoltaics: A Review',
                authors: 'Henry Snaith, Masatoshi Miyake',
                year: 2019,
                journal: 'Journal of the American Chemical Society',
                link: 'https://doi.org/10.1021/jacs.9b00123',
                url: 'www.baidu.com',
            }
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
