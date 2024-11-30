<template>
  <div class="bg"/>
  <div class="common-layout">
    <el-container>
      <el-header>
        <div class="header-co1">
          <el-avatar
            :size="130"
            src="https://s2.loli.net/2024/10/31/SjOP7wGIo1Qn8zR.jpg"
          />
        </div>
        <div class="header-co2">
          <div class="header1">{{ profile.name }}</div>
          <div class="organization">
            <el-icon :size="23"><OfficeBuilding /></el-icon>
            {{ profile.organization }}
          </div>
          <div class="areas">
            <el-icon :size="23"><CollectionTag /></el-icon>
            {{ profile.areas }}
          </div>
        </div>
        <div class="header-co3">
          <div class="data">
            <p class="data-number">{{ profile.article_cnt }}</p>
            <p class="data-type">文章数</p>
          </div>
          <div class="divider"></div>
          <div class="data">
            <p class="data-number">{{ profile.cited_cnt }}</p>
            <p class="data-type">被引数</p>
          </div>
        </div>
        <div class="header-co4">
          <el-button v-if="!isCurrentUser"
            ><el-icon><Phone /></el-icon>认领</el-button
          >
        </div>
      </el-header>
      <el-container>
        <el-aside>
          <el-card class="contact-card">
            <template #header>
              <div class="header2">
                <a>联系方式</a>
                <el-button
                  v-if="isCurrentUser && !editing"
                  @click="editProfile"
                  class="edit-botton"
                  ><el-icon><EditPen /></el-icon>编辑资料</el-button
                >
                <el-button
                  v-if="isCurrentUser && editing"
                  @click="confirmEdit"
                  class="edit-botton"
                  ><el-icon><CircleCheck /></el-icon>完成编辑</el-button
                >
              </div>
            </template>
            <div v-if="!editing">
              <div class="contact-item">
                <el-icon :size="23"><Message /></el-icon>
                {{ profile.email != null ? profile.email : "missing..." }}
              </div>
              <div class="contact-item">
                <el-icon :size="23"><HomeFilled /></el-icon>
                {{ profile.website != null ? profile.website : "missing..." }}
              </div>
            </div>
            <div v-else>
              <div class="contact-item">
                <el-icon :size="23"><Message /></el-icon>
                <input type="text" v-model="profile.email" />
              </div>
              <div class="contact-item">
                <el-icon :size="23"><HomeFilled /></el-icon>
                <input type="text" v-model="profile.website" />
              </div>
            </div>
          </el-card>
          <el-card>
            <template #header>
              <div class="header2">合著作者</div>
            </template>
            <div
              v-for="author in coauthors"
              :key="author.name"
              class="coauthor-item"
            >
              <el-avatar :size="45" class="coauthor-avatar"> user </el-avatar>
              <span>{{ author.name }}</span>
              <el-link class="jump-1" :href="author.href">
                <el-icon><ArrowRightBold /></el-icon>
              </el-link>
            </div>
          </el-card>
        </el-aside>
        <el-main>
          <el-tabs class="tabs" v-model="activeName">
            <el-tab-pane label="学术成果" name="profile_achieve">
              <ProfileAchieveComponent />
            </el-tab-pane>
            <el-tab-pane label="统计分析" name="profile_analysis">
              <ProfileAnalysisComponent />
            </el-tab-pane>
          </el-tabs>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from "vue";
import { RouterLink, RouterView } from "vue-router";
import { ref } from "vue";
import type { TabsPaneContext } from "element-plus";
import ProfileAnalysisComponent from "./tabs/analysis.vue";
import ProfileAchieveComponent from "./tabs/achieve.vue";

// Tabs 组件的 v-model
const activeName = ref("profile_achieve");
// TODO:当前用户是否是此门户主人，逻辑后续实现
const isCurrentUser = ref(false);
// 控制编辑模式的布尔值
const editing = ref(false);

// 个人信息接口
interface Profile {
  name: string;
  organization: string;
  areas: string;
  email: string | null;
  website: string | null;
  article_cnt: number;
  cited_cnt: number;
}

const profile = reactive<Profile>({
  name: "不文明小孩儿",
  organization: "北京航空航天大学软件学院",
  areas: "软件工程，人工智能",
  email: "22373442@buaa.edu.cn",
  website: null,
  article_cnt: 10,
  cited_cnt: 323,
});

// 合著作者信息接口
interface Coauthor {
  name: string;
  href: string;
  // avatar: string;
}

const coauthors = reactive<Coauthor[]>([
  { name: "Author 1", href: " " },
  { name: "Author 2", href: " " },
  { name: "Author 3", href: " " },
  { name: "Author 4", href: " " },
]);

function editProfile() {
  editing.value = true;
}

function confirmEdit() {
  editing.value = false;
}
</script>

<style scoped>
/* 
  整体布局
 */
.bg{
  background-color: #f4f4f4;
  position: fixed;
  height: 100vh;
  width: 100vw;
  z-index: -3;
}
.common-layout {
  padding: 5vh 8vw;
  word-break: break-all;
  word-wrap: break-word;
}

/* 
  HEADER部分
 */
@keyframes animated-border {
  0% {
    box-shadow: 0 0 0 0 rgba(215, 241, 252, 0.8);
  }
  100% {
    box-shadow: 0 0 0 60px rgba(215, 241, 252, 0.2);
  }
}
.el-header {
  animation: animated-border 1.5s infinite;
  background-color: white;
  height: 240px;
  display: flex;
  flex-direction: row;
  background-color: #82C5E9;
  border-radius: 4px;
  margin-bottom: 50px;
  margin-top: 50px;
}
.header-co1 {
  flex-basis: 20%;
  align-content: center;
}
.header-co2 {
  flex-basis: 30%;
  padding: 2vw;
  display: flex;
  flex-flow: column;
}
.header1,
.organization,
.areas {
  display: flex;
  align-items: center;
}
.header1 {
  font-size: 32px;
  font-weight: bold;
  flex-basis: 50%;
}
.organization,
.areas {
  flex-basis: 25%;
}
.el-icon {
  margin-right: 20px;
}
.header-co3 {
  flex-basis: 35%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
.divider {
  width: 2px;
  height: 50px;
  background-color: rgba(0, 0, 0, 0.15); /* 可以根据需要调整颜色 */
  margin: 0 10px; /* 设置左右间距 */
}
.data {
  align-content: center;
}
.data-type {
  color: rgba(0, 0, 0, 0.5);
}
.data-number {
  font-size: 24px;
}
.header-co4 {
  flex-basis: 15%;
  align-content: center;
}

/*
  主体部分
  */
.el-main {
  margin: 3vh 0 3vh 3vw;
  background-color: white;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  border-radius: 4px;
}
/* 一级tab部分 */
::v-deep .el-tabs__item {
  font-size: medium;
  font-weight: 800;
}
::v-deep .tabs > .el-tabs__header {
  display: flex;
  justify-content: center;
  border-bottom: 3px solid #82C5E9;
}
/*
  侧边栏
  */
.el-aside {
  margin: 3vh 0 3vh 0;
  width: 25%;
  background-color: #f4f4f4;
}
.edit-botton {
  margin-left: auto;
}
.el-card.is-always-shadow {
  /* 去阴影 */
  box-shadow: 0px 0px 0px rgba(0, 0, 0, 0.12);
}
.el-card {
  background-color: rgba(215, 241, 252, 0.6);
}
.header2 {
  font-size: 18px;
  font-weight: bold;
  display: flex;
  align-items: center;
}
.contact-card {
  margin-bottom: 12px;
}
.contact-item {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}
.coauthor-item {
  height: 70px;
  margin-bottom: 5px;
  display: flex;
  align-items: center;
  border-bottom: 1px solid #e9eef3;
}
.coauthor-avatar {
  margin-right: 12px;
}
.jump-1 {
  margin-left: auto;
}
</style>
