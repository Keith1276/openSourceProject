<template>
  <div class="main-contanier">
    <search @handleSearch="clickEven"></search>
    <div class="content-container">
      <!-- 左侧筛选栏 -->
      <div class="sidebar">
        <div class="sidebar-block">
          <p style="color: #657166">筛选</p>
          <el-card style="width: 100%">
            <p style="text-align: left; font-weight: bold">语言</p>
            <div style="width: 100%" v-for="(button, index) in languages">
              <el-button
                class="button-box"
                :key="index"
                @click="languageFilter(index)"
                text
              >
                <span
                  :style="{ 'background-color': button.color }"
                  class="square"
                ></span>
                {{ button.text }}
              </el-button>
            </div>
          </el-card>
          <el-card style="width: 100%">
            <p style="text-align: left; font-weight: bold">License</p>
            <div style="width: 100%" v-for="(button, index) in licenses">
              <el-button
                class="button-box"
                :key="index"
                @click="licenseFilter(index)"
                text
              >
                <span
                  :style="{ 'background-color': button.color }"
                  class="square"
                ></span>
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
              <p style="color: #657166">开源项目({{ resultCnt }})</p>
            </div>
          </div>
          <!-- 文章块 -->
          <el-card style="width: 100%" shadow="always">
            <el-card
              class="paper-card"
              shadow="hover"
              v-for="resposity in repositeries"
              :key="resposity.id"
            >
              <div class="paper-container">
                <div class="paper-content">
                  <div class="paper-header">
                    <div class="source-icon">
                      <img src="@/asset/search/github.png" />
                    </div>
                    <div class="project-name" :style="{ color: '#657166' }">
                      {{ resposity.name }}
                    </div>
                  </div>
                  <div class="description-license-language-row">
                    <div class="project-license">
                      <!-- zjq快来 zjq写好了但测不了 -->
                      <span class="label">开发者：</span>
                      <!-- <a href="/balabala/{{ resposity.ownerLogin }}"> -->
                      <button
                        @click="jumpPersonal(resposity.ownerId)"
                        class="jumpButton"
                      >
                        <span class="author-block">{{
                          resposity.ownerLogin
                        }}</span>
                      </button>
                      <!-- </a> -->
                    </div>
                    <div class="project-description">
                      <div class="label">项目简介：</div>
                      <div class="abstract-text">
                        {{ resposity.description }}
                      </div>
                    </div>
                    <div class="project-description">
                      <span class="label">最近更新时间</span>
                      <div class="others-block">{{ resposity.updatedAt }}</div>
                    </div>
                    <div class="project-license">
                      <span class="label">License：</span>
                      <span class="license-block">{{ resposity.license }}</span>
                    </div>
                    <div class="project-languages">
                      <span class="label">Language：</span>
                      <span
                        :style="{
                          'background-color': languageColor.get(
                            resposity.language
                          ),
                        }"
                        class="square"
                      ></span
                      >{{ resposity.language }}
                    </div>
                  </div>
                </div>
                <div class="paper-image">
                  <a
                    :href="resposity.htmlUrl"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <img src="@/asset/search/跳转.png" alt="Project Logo" />
                  </a>
                </div>
              </div>
              <div class="line"></div>
              <div class="citation-container">
                <div class="citation">
                  <a
                    :href="resposity.htmlUrl"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <img
                      src="@/asset/search/github-star.png"
                      alt=""
                      class="citation-icon"
                    />
                  </a>
                  <span>Star: {{ resposity.stargazersCount }}</span>
                </div>
                <div class="citation">
                  <a
                    :href="resposity.htmlUrl"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <img
                      src="@/asset/search/code-fork.png"
                      alt=""
                      class="citation-icon"
                    />
                  </a>
                  <span>Fork: {{ resposity.forksCount }}</span>
                </div>
                <div class="citation">
                  <a
                    :href="resposity.htmlUrl"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <img
                      src="@/asset/search/icon_github_issue.png"
                      alt=""
                      class="citation-icon"
                    />
                  </a>
                  <span>Issues: {{ resposity.hasIssues }}</span>
                </div>
                <div class="citation">
                  <a
                    :href="resposity.htmlUrl"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <img
                      src="@/asset/search/watch.png"
                      alt=""
                      class="citation-icon"
                    />
                  </a>
                  <span>Watchers: {{ resposity.watchersCount }}</span>
                </div>
              </div>
            </el-card>

            <div class="page">
              <el-pagination
                background
                layout="prev, pager, next"
                :total="10 * pagination.total"
                :current-page="pagination.currentPage"
                :page-size="pagination.pageSize"
                @update:current-page="handleCurrentChange"
              />
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import search from "./search.vue";
import { ref, onMounted, onUnmounted } from "vue";
import { useRoute } from "vue-router";
import { setNav } from "@/nav/set";
import { ElMessageBox, ElMessage } from "element-plus";
import { defineComponent, defineEmits } from "vue";
import { color } from "echarts";
import { searchRepos } from "@/api/searchRepos";
import router from "@/router/index.js";
export default defineComponent({
  methods: {
    getCitationIcon(paper) {
      return paper.citationClicked
        ? require("@/asset/search/yinyong_1.png")
        : require("@/asset/search/yinyong.png");
    },
    toggleCollection(paper) {
      paper.citationClicked = !paper.citationClicked;
    },
  },
  data() {
    return {
      currentInfoIndex: null,
      timeIndex: -1,
      filedIndex: -1,
    };
  },
  components: { search },
  setup() {
    const resultCnt = ref("3");
    const sort = ref("star");
    const checkList = ref([]);
    const options = [
      { value: "star", label: "按Star数降序" },
      { value: "fork", label: "按Fork数降序" },
      { value: "help-wanted-issues", label: "按help-wanted-issues个数" },
      { value: "updated", label: "按最新updated" },
    ];
    const languages = [
      { text: "Python", color: "#3572a5" },
      { text: "Java", color: "#b07219" },
      { text: "C", color: "#555555" },
      { text: "C++", color: "#f34b7d" },
      { text: "HTML", color: "#e34c26" },
      { text: "Verilog", color: "#b2b7f8" },
      { text: "JavaScript", color: "#f1e05a" },
      { text: "Vue", color: "#41b883" },
    ];

    const licenses = [
      { text: "Apache", color: "#3572a5" },
      { text: "MIT", color: "#b07219" },
      { text: "Other", color: "#555555" },
      { text: "Unlicense", color: "#f34b7d" },
      { text: "GNU", color: "#e34c26" },
      { text: "BSD3", color: "#b2b7f8" },
      { text: "BSD0", color: "#f1e05a" },
      { text: "zlib", color: "#41b883" },
    ];

    const licenseName = new Map<string, string>([
      ["Apache", "Apache License 2.0"],
      ["MIT", "MIT License"],
      ["Other", "Other"],
      ["Unlicense", "The Unlicense"],
      ["GNU", "GNU General Public License v3.0"],
      ["BSD3", 'BSD 3-Clause "New" or "Revised" License'],
      ["BSD0", "BSD Zero Clause License"],
      ["zlib", "zlib License"],
    ]);

    const languageColor = new Map<string, string>([
      ["Python", "#3572a5"],
      ["Java", "#b07219"],
      ["C", "#555555"],
      ["C++", "#f34b7d"],
      ["HTML", "#e34c26"],
      ["Verilog", "#b2b7f8"],
      ["JavaScript", "#f1e05a"],
      ["Vue", "#41b883"],
    ]);
    const dialogVisible = ref(false);
    const radio3 = ref("1");
    const newFavourite = ref("");

    const route = useRoute();

    const repositeries = ref([
      {
        id: 0,
        name: "project name",
        ownerLogin: "owner name",
        htmlUrl: "https://github.com/",
        description: "tolang 指 “toy lang”，是一款用于教学目的的简单编程语言。",
        updatedAt: "2024-12-05",
        stargazersCount: 20,
        watchersCount: 15,
        language: "Python",
        hasIssues: 30,
        hasDiscussions: 10,
        forksCount: 1,
        license: "MIT",
        ownerId: 1,
      },
      {
        id: 1,
        name: "project name",
        ownerLogin: "owner name",
        htmlUrl: "https://github.com/",
        description: "tolang 指 “toy lang”，是一款用于教学目的的简单编程语言。",
        updatedAt: "2024-12-05",
        stargazersCount: 20,
        watchersCount: 15,
        language: "Python",
        hasIssues: 30,
        hasDiscussions: 10,
        forksCount: 1,
        license: "MIT",
        ownerId: 1,
      },
      {
        id: 2,
        name: "project name",
        ownerLogin: "owner name",
        htmlUrl: "https://github.com/",
        description: "tolang 指 “toy lang”，是一款用于教学目的的简单编程语言。",
        updatedAt: "2024-12-05",
        stargazersCount: 20,
        watchersCount: 15,
        language: "Python",
        hasIssues: 30,
        hasDiscussions: 10,
        forksCount: 1,
        license: "MIT",
        ownerId: 1,
      },
    ]);

    const license = ref<string[]>([]);
    const language = ref<string[]>([]);
    const content = ref<string[]>([]);

    var pagenum = 1;

    const clickEven = (val) => {
      val.license = val.license.map((key) => licenseName.get(key) || key);
      console.log(val.license); // 一个数组，里面放的是license种类的名字
      console.log(val.language); // 语言
      console.log(val.content); // 搜索内容
      license.value = val.license;
      language.value = val.language;
      content.value = val.content;

      if (license.value == null) license.value = [];
      if (license.value[0] == "All") license.value = [];
      let param = {
        license: license.value,
        language: language.value,
        content: content.value,
        pageNumber: 1,
        pageSize: 10,
      };
      console.log(param);
      clickEvent(param);
      // TODO: 在这里发请求，把值赋给projects
    };
    const clickEvent = async (val: {
      license: string[];
      language: string[];
      content: string[];
      pageNumber: number;
      pageSize: number;
    }) => {
      try {
        pagenum = val.pageNumber;
        const data = {
          keywords: val.content,
          languages: val.language,
          licenses: val.license,
          pageNum: val.pageNumber,
          pageSize: val.pageSize,
        };
        console.log(data);
        const results = await searchRepos(data);
        repositeries.value = results["repositories"];
        pagination.value.total = results["total"] / 10;
        console.log(results);
      } catch (error) {
        console.error("请求失败:", error);
      }
    };

    var timeIndex = ref(-1);
    var filedIndex = ref(-1);

    const languageFilter = (index): void => {
      language.value = [];
      language.value[0] = languages[index].text;
      let param = {
        license: license.value,
        language: language.value,
        content: content.value,
        pageNumber: 1,
        pageSize: 10,
      };
      clickEvent(param);
    };

    const licenseFilter = (index): void => {
      license.value = [];
      license.value[0] = licenses[index].text;
      license.value = license.value.map((key) => licenseName.get(key) || key);
      let param = {
        license: license.value,
        language: language.value,
        content: content.value,
        pageNumber: 1,
        pageSize: 10,
      };
      clickEvent(param);
    };

    let curPapers = ref<any>([]);

    const pagination = ref({
      total: 10,
      currentPage: 1,
      pageSize: 10,
    });

    const handleCurrentChange = (e) => {
      pagination.value.currentPage = e;
      console.log(pagination.value.currentPage);
      if (language.value == null) language.value = [];
      if (license.value == null) license.value = [];
      if (content.value == null) content.value = [];
      let param = {
        license: license.value,
        language: language.value,
        content: content.value,
        pageNumber: pagenum + 1,
        pageSize: 10,
      };
      clickEvent(param);
      // pagination.value.currentPage = e;
    };

    // Receive message from searchbar
    const receivedMessage = ref("");
    const handleinputSend = (message) => {
      receivedMessage.value = message;
      console.log("Received message from Child: ", receivedMessage.value);
      console.log(timeIndex.value);
      // update papers
    };

    const getlicense = (str): string => {
      var obj, nameValue;
      if (str != "null") {
        obj = JSON.parse('{"license": ' + str + "}");
        nameValue.value = obj.license.name;
      }
      nameValue.value = "NULL";
      return nameValue.value;
    };
    const jumpPersonal = (id) => {
      console.log("jumpPersonal click success");
      router.push("/personal/" + id);
    };
    onMounted(() => {
      handleCurrentChange(1);
    });

    onUnmounted(() => {
      setNav(false);
    });

    return {
      resultCnt,
      sort,
      checkList,
      options,
      curPapers,
      languages,
      handleCurrentChange,
      pagination,
      receivedMessage,
      handleinputSend,
      dialogVisible,
      radio3,
      newFavourite,
      clickEven,
      languageColor,
      repositeries,
      languageFilter,
      jumpPersonal,
      getlicense,
      licenses,
      licenseFilter,
    };
  },
});
</script>
<style scoped>
.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1% 2% 1% 2%;
  background-color: #dd7050;
  height: 5%;
}

.left-topbar {
  display: flex;
  align-items: center;
}

.logo {
  height: 30px;
}

.search-container {
  display: flex;
  align-items: center;
}

.search-input {
  width: 350px;
  height: 20px;
  border-radius: 5px;
  padding: 10px;
}

.search-button {
  padding: 10px 20px;
  height: 40px;
  background-color: #4f6ef2;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.search-button:hover {
  background-color: #4662d9;
}

.profile {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: #aed0ee;
  border: none;
  cursor: pointer;
}

.button-image {
  width: 20px; /* Adjust the size as needed */
  height: 20px;
}

.main-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.sidebar {
  min-width: 25%;
  background-color: #f4f4f4;
  min-height: 100vh;
}

.sidebar-block {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding-left: 17%;
  padding-right: 6%;
}

.content {
  flex-grow: 1; /* 内容区域占据剩余空间 */
  background-color: #f4f4f4;
  overflow-y: auto; /* 允许垂直滚动 */
}

.content-block {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding-left: 3%;
  padding-right: 9%;
}
.content-container {
  display: flex;
  flex-direction: row;
  width: 100%;
}
.result-container {
  display: flex;
  height: 80px;
  align-items: center;
  background-color: #f4f4f4;
}
.content-top {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center; /* 如果需要垂直居中 */
}
.content-top-right {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.check-box {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.el-card {
  display: flex;
  flex-direction: column;
  width: 100%;
}
.region {
  height: 20vh;
  width: 100%;
  display: flex;
  flex-direction: column;
}
.region-top {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 100%;
}
.el-divider {
  margin-top: auto;
}
.avatar {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 65px;
  height: 65px;
  border-radius: 50%;
  background-color: #aed0ee;
  border: none;
  cursor: pointer;
  margin-left: 3%;
  margin-right: 3%;
}
.region-top-middle {
  width: 60%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.fields {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.region-top-end {
  display: flex;
  flex-direction: row;
}
.paper-card {
  width: 100%;
  margin-bottom: 10px;
  margin-top: 10px;
}
.paper-card {
  display: flex;
  flex-direction: column;
}
.paper-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.paper-content {
  flex: 0 0 70%; /* 左边占比80% */
}

.paper-image {
  display: flex;
  justify-content: left;
  align-items: center;
  flex: 0 0 20%;
  padding-right: 20px;
}

.paper-image img {
  max-width: 50%;
  height: auto;
}
.paper-header {
  display: flex;
  align-items: center;
}

.source-icon img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.project-name {
  margin-left: 10px;
}

.project-info {
  display: flex;
  flex-wrap: wrap; /* 允许内容换行 */
  align-items: flex-start; /* 左对齐 */
}

.description-license-language-row {
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* 垂直居中 */
}

.project-description,
.project-license,
.project-languages {
  margin-right: 20px;
  margin-top: 5px;
  margin-bottom: 5px;
}

.label {
  font-weight: bold; /* 加粗 */
  margin-right: 5px; /* 标签和内容之间的间距 */
}
.label,
.abstract-text {
  text-align: left;
  margin-top: 5px;
  width: 600px;
}

.abstract-text {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}

.license-block {
  display: inline-block;
  background-color: #936735; /* 黄色背景 */
  margin-right: 5px; /* 黄色块和文字之间的间距 */
  padding: 5px 5px;
  border-radius: 2px;
  color: white;
}

.author-block {
  display: inline-block;
  background-color: #c9736a; /* 黄色背景 */
  margin-right: 5px; /* 黄色块和文字之间的间距 */
  padding: 5px 5px;
  border-radius: 2px;
  color: white;
}

.others-block {
  display: inline-block;
  margin-right: 5px; /* 黄色块和文字之间的间距 */
  padding: 5px 5px;
  border-radius: 2px;
}

.language-block {
  display: inline-block;
  width: 10px;
  height: 10px;
  margin-right: 5px; /* 蓝色块和文字之间的间距 */
}
.submitted b {
  font-weight: bold;
  text-align: left;
}

.open .abstract-content {
  text-overflow: initial;
  white-space: normal;
  overflow: visible;
}
.button-box {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
}

.label-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.block {
  padding: 5px 10px;
  border-radius: 5px;
  margin: 5px;
  background-color: #936735;
  color: white;
  font-size: 12px;
}

.first-block {
  background-color: #936735;
  color: white;
  font-size: 12px;
}

.info-box {
  background-color: #f68f0a;
  color: black;
  font-size: 12px;
}

.info-box-content {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 5px;
  border-radius: 5px;
  font-size: 0.8em;
  white-space: nowrap;
}

.line {
  border-bottom: 1px solid #eaecef; /* 添加底部边框作为线 */
  margin-top: 10px;
  margin-bottom: 10px;
}

.citation-container {
  display: flex;
  align-items: center;
  font-size: 14px;
  justify-content: space-around;
}

.citation-icon {
  width: 20px;
  height: 20px;
  margin-right: 5px;
}

.citation {
  display: flex;
  align-items: center;
  font-size: 14px;
}

.button-box {
  display: inline-flex;
}

.square {
  display: inline-block;
  width: 10px;
  height: 10px;
  margin-right: 5px;
}
.jumpButton {
  border: none;
  background: none;
  padding: 0;
  margin: 0;
  outline: none;
  cursor: pointer;
  color: aliceblue;
  font-family: inherit;
  font-size: inherit;
}
</style>
