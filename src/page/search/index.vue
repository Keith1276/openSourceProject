<template>
  <div class="main-contanier">
    <search></search>
    <div class="result-container">
      <p style="margin-left: 5%; color: #5976ba; font-size: 18px">
        为您查询到 <b style="font-weight: bold">{{ resultCnt }}</b> 条结果
      </p>
    </div>
    <div class="content-container">
      <!-- 左侧筛选栏 -->
      <div class="sidebar">
        <div class="sidebar-block">
          <p style="color: #5976ba">筛选</p>
          <el-card style="width: 100%">
            <p style="text-align: left; font-weight: bold">时间</p>
            <div style="width: 100%" v-for="(button, index) in timeButtons">
              <el-button
                class="button-box"
                :key="index"
                @click="timeFilter(index)"
                text
              >
                {{ button.text }}
              </el-button>
            </div>
          </el-card>
          <el-card style="width: 100%">
            <p style="text-align: left; font-weight: bold">领域</p>
            <div style="width: 100%" v-for="(button, index) in fieldButtons">
              <el-button
                class="button-box"
                :key="index"
                text
              >
                {{ button.text }}
              </el-button>
            </div>
          </el-card>
        </div>
      </div>
      <!-- 右侧文章栏 -->
      <div class="content">
        <div class="content-block">
          <!-- 两个小选择 -->
          <div class="content-top">
            <div class="content-top-left">
              <p style="color: #5976ba">文章({{ resultCnt }})</p>
            </div>
            <div class="content-top-right">
              <p style="color: #5976ba">排序</p>
              <el-select
                v-model="value"
                placeholder="按相关性"
                style="width: 240px; padding-left: 20px"
              >
                <el-option
                  v-for="(item, index) in options"
                  :key="index"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </div>
          </div>
          <!-- 文章块 -->
          <el-card style="width: 100%" shadow="always">
            <el-card class="paper-card" shadow="hover" v-for="paper in papers" :key="paper.id">
              <div class="title">
                <b>{{ paper.title }}</b>
              </div>
              <div class="authors">
                <b>Authors:</b>
                <a class="author">{{ paper.authors }}</a>
              </div>
              <div class="abstract">
                <b style="background-color: #d4e5ef; color: #003d74">Abstract:</b>
                <div class="abstract-content">
                  {{ paper.abstract }}
                </div>
              </div>
              <div class="submitted">
                <b>Submitted:</b>
                {{ paper.submitted }}
              </div>
              <div class="submitted">
                <b>Comments:</b>
                {{ paper.comments }}
              </div>
              <div class="label-container">
                <div
                  v-for="(item, index) in paper.type"
                  :key="index"
                  class="block"
                  :class="{ 'first-block': index === 0, 'info-box': index !== 0 }"
                >
                  {{ item }}
                </div>
              </div>
              <div class="line"></div>
              <div class="citation-container">
                <div class="citation" @click="toggleCitation(paper)">
                  <div>{{ paper.citationClicked }}</div>
                  <img :src="getCitationIcon(paper)" alt="" class="citation-icon">
                  <span>引用</span>
                </div>
                <div class="citation" @click="toggleCollection(paper)">
                  <img :src="getCollection(paper)" alt="" class="citation-icon">
                  <span>收藏</span>
                </div>
                <span>被引量: {{ paper.citations }}</span>
              </div>
            </el-card>
          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { text } from "stream/consumers";
import search from "./search.vue";
import { ref } from "vue";
import { List } from "echarts";
export default {
  methods:{
    getCitationIcon(paper) {
      return paper.citationClicked
        ? require('@/asset/search/yinyong_1.png')
        : require('@/asset/search/yinyong.png');
    },
    toggleCollection(paper) {
      paper.citationClicked = !paper.citationClicked;
    }
  },
  data(){
    return{
      items: [
        { shortName: '块1块1块1块1块1', fullName: '小块内容的全名1' },
        { shortName: '块2', fullName: '小块内容的全名2' },
        { shortName: '块3', fullName: '小块内容的全名3' },
        // 更多小块...
      ],
      currentInfoIndex: null,
      timeIndex: -1,
      filedIndex: -1.
    }
  },
  components: { search },
  setup() {
    const resultCnt = ref("3");
    const value = ref("");
    const checkList = ref([]);
    const organizations = [
      { value: false, label: "Tsinghua University" },
      { value: false, label: "Peking University" },
    ];
    const options = [
      { value: "Option1", label: "按相关性" },
      { value: "Option2", label: "按被引量" },
      { value: "Option3", label: "按时间降序" },
    ];
    const timeButtons = [
      { text: "2024以来" },
      { text: "2023以来" },
      { text: "2022以来" },
    ];
    const fieldButtons = [
      { text: "计算机科学" },
      { text: "人工智能" },
      { text: "数学" },
    ];
    const paper = {
      title: "Your Paper Title",
      authors: "John Doe, Jane Smith",
      abstract:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
      submitted: "2023-10-31",
      comments: "NeurlPS Workshop 2024",
      type: ["cs.AR","cs.AI","cs.LG"]
    };

    const papers=[
      {
        id: 1,
        title: "Your Paper Title",
        authors: "John Doe, Jane Smith",
        abstract:
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
        submitted: "2023-10-31",
        comments: "NeurlPS Workshop 2024",
        type: ["cs.AR","cs.AI","cs.LG"],
        citations: 10,
        citationClicked:false,
        collectionClicked:false,
      },
      {
        id: 2,
        title: "Your Paper Title",
        authors: "John Doe, Jane Smith",
        abstract:
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
        submitted: "2023-10-31",
        comments: "NeurlPS Workshop 2024",
        type: ["cs.AR","cs.AI","cs.LG"],
        citations: 10,
        citationClicked:false,
        collectionClicked:false,
      },
      {
        id: 3,
        title: "Your Paper Title",
        authors: "John Doe, Jane Smith",
        abstract:
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
        submitted: "2023-10-31",
        comments: "NeurlPS Workshop 2024",
        type: ["cs.AR","cs.AI","cs.LG"],
        citations: 10,
        citationClicked:false,
        collectionClicked:false,
      }
    ];

    const toggleOpen = (): void => {
      const abstractContent = document.querySelector(".abstract-content");
      if (abstractContent) {
        if (abstractContent.classList.contains("open")) {
          abstractContent.classList.remove("open");
        } else {
          abstractContent.classList.add("open");
        }
      }
    };

    const toggleCitation = (paper): void => {
      paper.citationClicked = !paper.citationClicked;
      console.log(paper.citationClicked)
    };

    const toggleCollection = (paper): void =>{
      paper.collectionClicked = !paper.collectionClicked;
      console.log(paper.collectionClicked)
    }

    const getCitationIcon = (paper): string =>{
      return paper.citationClicked
      ? require('@/asset/search/yinyong_1.png')
      : require('@/asset/search/yinyong.png');
    }

    const getCollection = (paper): string =>{
      return paper.collectionClicked
      ? require('@/asset/search/shoucang_1.png') 
      : require('@/asset/search/shoucang.png');
    }
    var timeIndex = ref(-1);

    const timeFilter = (index): void =>{
      timeIndex.value=index;
    }

    const filterPapers = (): Array<any> => {
      if(timeIndex.value!=-1){
        if(timeIndex.value==0){
          return papers.filter(paper => {
            const submittedYear = new Date(paper.submitted).getFullYear();
            return submittedYear <= 2024;
          });
        }
        else if(timeIndex.value==1){
          return papers.filter(paper => {
            const submittedYear = new Date(paper.submitted).getFullYear();
            return submittedYear <= 2023;
          });
        }
        else if(timeIndex.value==2){
          return papers.filter(paper => {
            const submittedYear = new Date(paper.submitted).getFullYear();
            return submittedYear <= 2022;
          });
        }
      }
      return papers;
    };

    return {
      resultCnt,
      value,
      checkList,
      options,
      timeButtons,
      organizations,
      paper,
      toggleOpen,
      fieldButtons,
      papers,
      toggleCitation,
      toggleCollection,
      getCitationIcon,
      getCollection,
      timeFilter,
      filterPapers
    };
  },
  
};
</script>
<style scoped>
@import "style.css";
</style>
