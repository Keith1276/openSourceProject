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

<style scoped>.references {
    margin-top: 20px;
    margin-left: 20px;
}

.references h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
    font-size: 1.5rem;
}

.references ul {
    list-style: none;
    padding: 0;
}

.references li {
    margin-bottom: 10px;
    margin-left: 20px;
    font-size: 14px;
    line-height: 1.5;
}

.references li::before {
    content: "\2022";
    position: absolute;
    margin-left: -20px;
}

.references a {
    color: #007bff;
    text-decoration: none;
}

.references a:hover {
    text-decoration: underline;
}

@media (max-width: 768px) {
    .references h2 {
        font-size: 1.2rem;
    }

    .references li {
        margin-left: 10px;
    }
}

.paper-link {
    color: #007bff;
    text-decoration: none;
    transition: color 0.3s ease;
}

.paper-link:hover {
    color: #0056b3;
    text-decoration: underline;
}

</style>
