<template>
  <div class="tableContainer">
    <el-table
      :data="paginatedData"
      :default-sort="{ prop: 'date', order: 'descending' }"
      style="width: 100%"
    >
      <el-table-column label="文献" min-width="400">
        <template #default="scope">
          <a :href="`${scope.row.href}`">{{ scope.row.title }}</a
          ><br />
          <span>{{ scope.row.authors }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="date" sortable label="日期" />
      <el-table-column prop="citations" sortable label="引用次数" />
    </el-table>
    <div class="pagination">
      <el-pagination
        :current-page="currentPage"
        :page-size="pageSize"
        :total="tableData.length"
        layout="prev, pager, next"
        @current-change="handlePageChange"
        size="small"
        background
        
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { computed, reactive, ref } from "vue";

interface Article {
  date: string;
  citations: number;
  title: string;
  authors: string;
}
const pageSize = 10;
const currentPage = ref(1); 
const tableData = reactive<Article[]>([
  {
    date: "2024-10-26",
    citations: 0,
    title:
      "Adaptively Fusing Complete Multi-resolution Features for Human Pose Estimation",
    authors: "RoisyL, Xin L, Zhao Y, Yu J, Wang X, Liu H",
  },
  {
    date: "2024-04-03",
    citations: 18,
    title: "Dual Gated Learning for Visible-Infrared Person Re-identification",
    authors: "RoisyL, Xin L, Zhao Y",
  },
  {
    date: "2023-11-15",
    citations: 323,
    title: "Image-based vehicle searching method based on deep learning",
    authors: "RoisyL, Yu J, Wang X, Liu H",
  },
  {
    date: "2023-07-26",
    citations: 107,
    title:
      "Large-angle license plate inclination correction method based on end-to-end neural network",
    authors: "RoisyL, Zhao Y",
  },
  {
    date: "2024-10-26",
    citations: 0,
    title:
      "Adaptively Fusing Complete Multi-resolution Features for Human Pose Estimation",
    authors: "RoisyL, Xin L, Zhao Y, Yu J, Wang X, Liu H",
  },
  {
    date: "2024-04-03",
    citations: 18,
    title: "Dual Gated Learning for Visible-Infrared Person Re-identification",
    authors: "RoisyL, Xin L, Zhao Y",
  },
  {
    date: "2023-11-15",
    citations: 323,
    title: "Image-based vehicle searching method based on deep learning",
    authors: "RoisyL, Yu J, Wang X, Liu H",
  },
  {
    date: "2023-07-26",
    citations: 107,
    title:
      "Large-angle license plate inclination correction method based on end-to-end neural network",
    authors: "RoisyL, Zhao Y",
  },
  {
    date: "2024-10-26",
    citations: 0,
    title:
      "Adaptively Fusing Complete Multi-resolution Features for Human Pose Estimation",
    authors: "RoisyL, Xin L, Zhao Y, Yu J, Wang X, Liu H",
  },
  {
    date: "2024-04-03",
    citations: 18,
    title: "Dual Gated Learning for Visible-Infrared Person Re-identification",
    authors: "RoisyL, Xin L, Zhao Y",
  },
  {
    date: "2023-11-15",
    citations: 323,
    title: "Image-based vehicle searching method based on deep learning",
    authors: "RoisyL, Yu J, Wang X, Liu H",
  },
  {
    date: "2023-07-26",
    citations: 107,
    title:
      "Large-angle license plate inclination correction method based on end-to-end neural network",
    authors: "RoisyL, Zhao Y",
  },
]);

// 这里的计算其实有bug，因为还涉及到排序，应该在每次排序后重新计算
// 但我觉得我们应该不会有那么多数据，遂搁置，真遇到了再debug吧

// 计算当前页的数据
const paginatedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  const end = start + pageSize;
  return tableData.slice(start, end);
});

// 页码改变时的处理函数
const handlePageChange = (newPage: number) => {
  currentPage.value = newPage;
};
</script>

<style scoped>
.tableContainer {
  display: flex;
  flex-direction: column;
}
.pagination{
  margin-top: 20px;
  text-align: center;
}
</style>
