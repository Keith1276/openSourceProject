<template>
  <div class="tableContainer">
    <el-tabs type="border-card" tabPosition="left" v-model="activeName">
      <el-tab-pane label="论文" name="Article">
        <Article />
      </el-tab-pane>
      <el-tab-pane label="科研项目" name="Project"></el-tab-pane>
      <el-tab-pane label="专利" name="Patent"></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import Article from "./subtabs/article.vue";
// Tabs 组件的 v-model
const activeName = ref("Article");
</script>

<style scoped></style>
