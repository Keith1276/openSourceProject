<template>
  <div class="main-contanier">
    <search></search>
    <h2 style="color: #dd7050; margin-bottom: 30px">BUAA开源项目地图</h2>
    <div class="intro-container">
      <div v-for="(region, index) in regions" :key="index" class="box">
        <div class="icon_and_title">
          <el-icon style="margin-right: 10px; margin-top: 7%"><Search /></el-icon>
          <p>{{ region.title }}</p>
        </div>

        <p>{{ region.content }}</p>
      </div>
    </div>
    <div class="list-container">
      <div class="repo-list">
        <p style="font-size: large; font-weight: bold">热门项目排行</p>
        <el-table :data="repoData" style="width: 100%">
          <el-table-column prop="number" label="排行" width="80" />
          <el-table-column prop="name" label="项目名" width="170" />
          <el-table-column prop="owner_login" label="开发者" width="110" />
          <el-table-column
            prop="stargazers_count"
            label="Star"
            sortable
            width="100"
          />
          <el-table-column prop="forks_count" label="Fork" sortable />
        </el-table>
      </div>
      <div class="person-list">
        <p style="font-size: large; font-weight: bold">热门开发者排行</p>
        <el-table :data="personData" style="width: 100%">
          <el-table-column prop="number" label="排行" width="80" />
          <el-table-column prop="login" label="开发者" width="170" />
          <el-table-column
            prop="followers"
            label="followers"
            width="110"
            sortable
          />
          <el-table-column prop="public_repos" label="公开项目数" sortable />
        </el-table>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import search from "./search.vue";
import { ref, computed, onMounted, watch } from "vue";
export default {
  components: { search },
  setup() {
    const regions = ref([
      {
        title: "检索开源项目",
        content: "XXXX会根据开源项目的star数目、fork等指标对开源项目进行排序，学者输入开源项目内容相关信息后即可进行精确检索",
      },
      {
        title: "检索开发者",
        content: "用户可以对开发者的方向和信息进行搜索，XXXX监控开发者的仓库更新，搜索开发者名称或开发者方向均会结合开发者影响力和开发者相关程度给出结果",
      },
      {
        title: "获取开发者项目列表",
        content: "用户能够获取开发者的个人主页，xxxx会自动聚合开发者的项目成果，以列表的形式展示其概要信息，用户只需要点击相应项目即可查看项目详情。",
      },
    ]);
    const repoData = ref([
      {
        number: "1",
        name: "Some-Many-Books",
        owner_login: "Dujltqzv",
        stargazers_count: "11762",
        forks_count: "1467",
      },
      {
        number: "2",
        name: "Books-Free-Books",
        owner_login: "lTbgykio",
        stargazers_count: "10613",
        forks_count: "1137",
      },
      {
        number: "3",
        name: "x86-bare-metal-examples",
        owner_login: "cirosantilli",
        stargazers_count: "4850",
        forks_count: "408",
      },
    ]);
    const personData = ref([
      {
        number: "1",
        login: "jindongwang",
        followers: "5070",
        public_repos: "59",
      },
      {
        number: "2",
        login: "numbbbbb",
        followers: "4687",
        public_repos: "99",
      },
      {
        number: "3",
        login: "hiyouga",
        followers: "2524",
        public_repos: "55",
      },
    ]);
    return {
      regions,
      repoData,
      personData,
    };
  },
};
</script>
<style scoped>
.main-contanier {
  display: flex;
  flex-direction: column;
  /* background-color: #f4f4f4; */
  width: 100%;
  min-height: 100%;
}
.intro-container {
  display: flex;
  justify-content: space-between;
  height: 20%;
  margin-left: 10%;
  margin-right: 10%;
  margin-bottom: 3%;
}
.box
{
  height: 100%;
  width: 30%;
  border: 1px solid #fbf1e5;
  background-color: #fffdf9;
}
.box p
{
  //text-indent: 2em; /* 设置首行缩进，2em 相当于两个 'M' 字符的宽度 */
  padding-left: 2em; /* 设置左边距 */
  padding-right: 2em; /* 设置右边距 */
}
.icon_and_title {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 27%;
}
.icon_and_title p {
  margin-top: 12%;
  font-size: large;
}
.list-container {
  display: flex;
  justify-content: space-between;
  /* min-height: 50%; */
  margin-left: 10%;
  margin-right: 10%;
}
.repo-list {
  width: 46%;
  border: 1px solid #f4f4f4;
  padding-left: 1vw;
  padding-right: 1vw;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.person-list {
  width: 46%;
  border: 1px solid #f4f4f4;
  padding-left: 1vw;
  padding-right: 1vw;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
</style>
