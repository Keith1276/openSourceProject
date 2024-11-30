<template>
  <div class="admin-container">
    <!-- 整体布局 -->
    <el-container>
      <el-aside width="15%" class="menu">
        <el-menu :default-active="activeMenu" @select="handleMenuSelect" class="custom-menu">
          <el-menu-item index="overview">
            <i class="el-icon-s-check"></i>
            <span>平台总览</span>
          </el-menu-item>

          <el-menu-item index="reviewClaim">
            <i class="el-icon-s-check"></i>
            <span>审核认领</span>
          </el-menu-item>

          <el-menu-item index="residentScholars">
            <i class="el-icon-s-custom"></i>
            <span>入驻学者</span>
          </el-menu-item>

        </el-menu>
      </el-aside>

      <!-- 右侧内容区 -->
      <el-main>
        <div v-show="activeMenu === 'overview'">
          <h1>平台概况</h1>
          <!-- 平台信息概况 -->
          <div class="overview-summary">
            <el-row :gutter="20">
              <el-col :xs="12" :sm="6" v-for="(item, index) in overviewStats" :key="index">
                <div class="stat-box">
                  <p>{{ item.title }}</p>
                  <h3>{{ item.count }}</h3>
                </div>
              </el-col>
            </el-row>
          </div>

          <!-- 用户构成和平台活跃度图表 -->
          <el-row :gutter="20" class="overview-details">
            <el-col :xs="24" :sm="12">
              <h3>用户构成</h3>
              <el-card class="chart-container">
                <div ref="userCompositionChart" class="echart"></div>
              </el-card>
            </el-col>
            <el-col :xs="24" :sm="12">
              <h3>平台活跃度</h3>
              <el-card class="chart-container">
                <div ref="activityChart" class="echart"></div>
              </el-card>
            </el-col>
          </el-row>
        </div>



        <div v-show="activeMenu === 'reviewClaim'">
          <h1>审核认领</h1>

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
                  :total="15"
                  layout="prev, pager, next"
                  @current-change="handlePageChange"
                  size="big"
                  background
              />
            </div>

          </div>
        </div>

        <div v-show="activeMenu === 'residentScholars'">
          <h1>入驻学者</h1>
          <el-table
            :data="paginatedData"
            :default-sort="{ prop: 'date', order: 'descending' }"
            style="width: 100%"
            >
            <el-table-column label="文献" min-width="400">
              <template #default="scope">
                <span>{{ scope.row.title }}</span>
                ><br />
                <span>{{ scope.row.authors }}</span>
              </template>
            </el-table-column>

            <el-table-column prop="date" sortable label="日期" />
            <el-table-column prop="citations" sortable label="引用次数" />
          </el-table>

        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { onMounted, watch } from 'vue';
import * as echarts from 'echarts';
import search from "./search.vue";
export default {
  name: "Administrator",

  data() {
    return {
      pageSize:5,
      currentPage:1,

      paginatedData:[
        {
          date: "2023-11-15",
          citations: 323,
          title: "Image-based vehicle searching method based on deep learning",
          authors: "RoisyL, Yu J, Wang X, Liu H",
        },
        {
          date: "2023-11-15",
          citations: 323,
          title: "Image-based vehicle searching method based on deep learning",
          authors: "RoisyL, Yu J, Wang X, Liu H",
        },
        {
          date: "2023-11-15",
          citations: 323,
          title: "Image-based vehicle searching method based on deep learning",
          authors: "RoisyL, Yu J, Wang X, Liu H",
        },
        {
          date: "2023-11-15",
          citations: 323,
          title: "Image-based vehicle searching method based on deep learning",
          authors: "RoisyL, Yu J, Wang X, Liu H",
        },
        {
          date: "2023-11-15",
          citations: 323,
          title: "Image-based vehicle searching method based on deep learning",
          authors: "RoisyL, Yu J, Wang X, Liu H",
        },
        {
          date: "2023-11-15",
          citations: 323,
          title: "Image-based vehicle searching method based on deep learning",
          authors: "RoisyL, Yu J, Wang X, Liu H",
        },
        {
          date: "2023-11-15",
          citations: 323,
          title: "Image-based vehicle searching method based on deep learning",
          authors: "RoisyL, Yu J, Wang X, Liu H",
        },
        {
          date: "2023-11-15",
          citations: 323,
          title: "Image-based vehicle searching method based on deep learning",
          authors: "RoisyL, Yu J, Wang X, Liu H",
        },
      ],

      activeMenu: 'reviewClaim',

      overviewStats: [
        { title: "注册用户数", count: 1024 },
        { title: "文献综述", count: 450 },
        { title: "作者总数", count: 320 },
        { title: "入驻学者总数", count: 56 }
      ],

      tableData:[
        {
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
      ],
      activityData: {
        dates: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
        values: [120, 200, 150, 80, 70, 110, 130]
      },
      userCompositionData: [
        { value: 400, name: '学生' },
        { value: 300, name: '高校研究人员' },
        { value: 200, name: '企业研究人员' },
        { value: 100, name: '其他' }
      ],
      activityChart: null,
      userChart: null
    };
  },
  methods: {
    handlePageChange(newPage)
    {
      this.currentPage=newPage;
    },
    handleMenuSelect(index) {
      this.activeMenu = index;
    },

    initCharts() {
      if (this.activityChart) this.activityChart.dispose();
      if (this.userChart) this.userChart.dispose();

      // 平台活跃度折线图
      this.activityChart = echarts.init(this.$refs.activityChart);
      this.activityChart.setOption({
        title: { text: '平台活跃度' },
        tooltip: { trigger: 'axis' },
        xAxis: { type: 'category', data: this.activityData.dates },
        yAxis: { type: 'value' },
        series: [
          {
            name: '活跃度',
            type: 'line',
            smooth: true,
            data: this.activityData.values
          }
        ],
      });

      // 用户构成饼图
      this.userChart = echarts.init(this.$refs.userCompositionChart);
      this.userChart.setOption({
        title: { text: '用户构成', left: 'center' },
        tooltip: { trigger: 'item' },
        legend: { orient: 'vertical', left: '15%' },
        series: [
          {
            name: '用户类型',
            type: 'pie',
            radius: '70%',
            data: this.userCompositionData,
            emphasis: {
              itemStyle: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: 'rgba(0, 0, 0, 0.5)' }
            }
          }
        ]
      });
    }
  },
  mounted() {
    this.initCharts();
  },
  watch: {
    activeMenu(newVal) {
      if (newVal === 'overview')
      {
        this.$nextTick(() => this.initCharts());
      }
    }
  }
};
</script>

<style scoped>
@import "style.css";
</style>
