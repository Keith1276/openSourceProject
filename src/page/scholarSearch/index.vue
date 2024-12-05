<template>
  <div class="main-contanier">
    <search></search>
    <div class="top-contanier">
      <h2 style="color: #dd7050">开发者搜索</h2>
      <div style="height: 40px">
        <el-input
          v-model="input3"
          style="max-width: 600px; height: 100%"
          placeholder="Please input"
          class="input-with-select"
        >
          <template #prepend>
            <el-select v-model="select" placeholder="姓名" style="width: 115px">
              <el-option label="姓名" value="1" />
            </el-select>
          </template>
          <template #append>
            <el-icon @click="handleSearch"><Search /></el-icon>
          </template>
        </el-input>
      </div>
    </div>
    <div class="result-container">
      <p style="margin-left: 5%; color: #cca663; font-size: 18px">
        为您查询到 <b style="font-weight: bold">{{ resultCnt }}</b> 条结果
      </p>
    </div>
    <div class="content-container">
      <div class="sidebar">
        <div class="sidebar-block">
          <p style="color: #cca663">入驻开发者统计</p>
          <div class="sidebar-content">
            <div class="sidebar-bg"></div>
            <p
              style="
                margin: 0;
                letter-spacing: 2px;
                color: #dd7050;
                font-size: x-large;
              "
            >
              德才兼备
            </p>
            <p
              style="
                margin-top: 5px;
                margin-bottom: 5px;
                letter-spacing: 2px;
                color: #dd7050;
                font-size: x-large;
              "
            >
              知行合一
            </p>
            <el-button round color="#dd7050"
              ><p style="color: white">查看开发者情况统计</p></el-button
            >
          </div>
        </div>
      </div>
      <div class="content">
        <div class="content-block">
          <div class="content-top">
            <div class="content-top-left">
              <p style="color: #cca663">开发者({{ resultCnt }})</p>
            </div>
          </div>
          <el-card style="width: 100%">
            <div
              v-for="(region, index) in pagedRegions()"
              :key="index"
              class="region"
            >
              <div class="region-top">
                <button class="avatar">
                  <img
                    src="@/asset/scholarSearch/avatar.png"
                    alt="Button Image"
                    class="button-image"
                  />
                </button>
                <div class="region-top-middle">
                  <button
                    style="margin-right: 10%"
                    @click="jumpPersonal"
                    class="jumpButton"
                  >
                    <p
                      style="
                        font-weight: bold;
                        margin-top: 1px;
                        color: #cca663;
                        font-size: large;
                      "
                    >
                      {{ region.login }}
                    </p>
                  </button>
                  <p style="margin-top: 1px">
                    {{ region.email }}
                  </p>
                  <div class="follow_and_repos">
                    <p style="margin-top: 1px; margin-right: 10px">
                      followers:<b
                        style="
                          font-weight: bold;
                          color: #4994c4;
                          font-size: small;
                        "
                        >{{ region.followers }}
                      </b>
                    </p>
                    <p style="margin-top: 1px">
                      repositories:<b
                        style="
                          font-weight: bold;
                          color: #4994c4;
                          font-size: small;
                        "
                        >{{ region.public_repos }}</b
                      >
                    </p>
                  </div>
                </div>
                <div class="region-top-end">
                  <el-button type="info" style="width: 30%" text bg
                    >Follow</el-button
                  >
                </div>
              </div>
              <el-divider>
                <el-icon><Avatar /></el-icon>
              </el-divider>
            </div>
          </el-card>

          <div class="page">
            <el-pagination
              background
              layout="prev, pager, next"
              :total="pagination.total"
              :current-page="pagination.currentPage"
              :page-size="pagination.pageSize"
              @update:current-page="handleCurrentChange"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import search from "./search.vue";
import { ref, computed, onMounted, watch } from "vue";
import router from "@/router/index.js";
import { useRoute } from "vue-router";
export default {
  components: { search },
  setup() {
    const resultCnt = ref("3");
    const value = ref("");
    const input3 = ref("");
    const select = ref("");
    const route = useRoute();
    const regions = ref([
      {
        id: 1,
        login: "Coke_And_1ce",
        email: "skyshipwc@163.com",
        followers: 1,
        public_repos: 1,
      },
      {
        id: 2,
        login: "Berry",
        email: "skyshipwc@163.com",
        followers: 1,
        public_repos: 1,
      },
    ]);
    const handleSearch = () => {
      console.log("click success");
    };
    const jumpPersonal = () => {
      console.log("jumpPersonal click success");
      router.push("/personal");
    };
    const pagination = ref({
      total: 0,
      currentPage: 1,
      pageSize: 5,
    });
    const updateTotal = () => {
      pagination.value.total = regions.value.length;
    };
    const pagedRegions = () => {
      const start =
        (pagination.value.currentPage - 1) * pagination.value.pageSize;
      const end = start + pagination.value.pageSize;
      return regions.value.slice(start, end);
    };
    const handleCurrentChange = (e) => {
      pagination.value.currentPage = e;
    };
    onMounted(() => {
      input3.value = route.params.input as string;
      select.value = route.params.select as string;
      updateTotal();
      handleCurrentChange(1);
    });
    return {
      resultCnt,
      value,
      regions,
      input3,
      select,
      pagination,
      pagedRegions,
      handleCurrentChange,
      handleSearch,
      jumpPersonal,
    };
  },
};
</script>
<style scoped>
.follow_and_repos {
  display: flex;
  flex-direction: row;
  width: 100%;
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
.sidebar-content {
  /* background: url("@/asset/scholarSearch/statistic.jpg");
  opacity: 0.7;
  background-position: bottom left;
  background-size: cover; */
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 20vw;
  height: 20vw;
  border: 1px solid #cca663;
}
.sidebar-bg {
  height: 100%;
  width: 100%;
  background: url("@/asset/scholarSearch/statistic.jpg") no-repeat bottom right /
    cover;
  opacity: 0.7;
}
.main-contanier {
  display: flex;
  flex-direction: column;
  height: 100vh;
  width: 100vw;
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
  width: 30%;
  background-color: #f4f4f4;
  max-height: 100vh;
}

.sidebar-block {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding-left: 17%;
  padding-right: 6%;
  height: 100%;
}

.content {
  flex-grow: 1;
  background-color: #f4f4f4;
  overflow-y: auto;
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
  flex-grow: 1;
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
  background-color: #f1e0a8;
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
  align-items: center;
  width: 30%;
  justify-content: flex-end;
  margin-right: 5%;
}
.top-contanier {
  background-color: #f4f4f4;
  display: flex;
  flex-direction: column;
}
.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
}
.page {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 5%;
  margin-bottom: 5%;
}
.el-pagination.is-background .el-pager li:not(.disabled) {
  background-color: #6e9bc5;
}
</style>
