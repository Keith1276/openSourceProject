<template>
  <div class="main-contanier">
    <search></search>
    <div class="top-contanier">
      <h2 style="color: #5976ba">学者搜索</h2>
      <div style="height: 40px">
        <el-input
          v-model="input3"
          style="max-width: 600px; height: 100%"
          placeholder="Please input"
          class="input-with-select"
        >
          <template #prepend>
            <el-select
              v-model="select"
              placeholder="Select"
              style="width: 115px"
            >
              <el-option label="姓名" value="1" />
              <el-option label="机构" value="2" />
            </el-select>
          </template>
          <template #append>
            <el-icon @click="handleSearch"><Search /></el-icon>
          </template>
        </el-input>
      </div>
    </div>
    <div class="result-container">
      <p style="margin-left: 5%; color: #5976ba; font-size: 18px">
        为您查询到 <b style="font-weight: bold">{{ resultCnt }}</b> 条结果
      </p>
    </div>
    <div class="content-container">
      <div class="sidebar">
        <div class="sidebar-block">
          <p style="color: #5976ba">筛选</p>
          <el-card style="width: 100%">
            <p style="text-align: left; font-weight: bold">机构</p>
            <div>
              <el-checkbox-group v-model="checkList" class="check-box">
                <el-checkbox
                  v-for="(item, index) in organizations"
                  :key="index"
                  :label="item.label"
                />
              </el-checkbox-group>
            </div>
          </el-card>
        </div>
      </div>
      <div class="content">
        <div class="content-block">
          <div class="content-top">
            <div class="content-top-left">
              <p style="color: #5976ba">学者({{ resultCnt }})</p>
            </div>
            <div class="content-top-right">
              <p style="color: #5976ba">排序</p>
              <el-select
                v-model="value"
                placeholder="Select"
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
                  <p style="font-weight: bold; margin-top: 1px">
                    {{ region.name }}
                  </p>
                  <p style="font-size: small; margin-top: 1px">
                    {{ region.organization }}
                  </p>
                  <div class="fields">
                    <p
                      v-for="item in region.fields"
                      style="
                        font-size: small;
                        margin-top: 0.1px;
                        color: #4994c4;
                      "
                    >
                      {{ item }}
                      <el-divider direction="vertical" />
                    </p>
                  </div>
                </div>

                <div class="region-top-end">
                  <p>
                    文献数量：<b style="font-weight: bold; color: #4994c4">{{
                      region.articleCnt
                    }}</b>
                  </p>
                  <p style="margin-left: 15px">
                    被引用数：<b style="font-weight: bold; color: #4994c4">{{
                      region.citations
                    }}</b>
                  </p>
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
    const checkList = ref([]);
    const input3 = ref("");
    const select = ref("");
    const route = useRoute();
    const organizations = ref([
      { value: false, label: "Tsinghua University" },
      { value: false, label: "Peking University" },
    ]);
    var fregions = ref([
      {
        name: "Zehuan Mo",
        organization: "Peking University",
        fields: ["Biology", "Gene"],
        articleCnt: "1",
        citations: "0",
      },
    ]);
    const options = ref([
      { value: "Option1", label: "按匹配程度递减" },
      { value: "Option2", label: "按文献数量递减" },
      { value: "Option3", label: "按文献数量递增" },
      { value: "Option4", label: "按被引用次数递减" },
      { value: "Option5", label: "按被引用次数递增" },
    ]);
    const regions = ref([
      {
        name: "Zehuan Mo",
        organization: "Peking University",
        fields: [
          "Biology",
          "Gene",
          "Biology",
          "Biology",
          "Biology",
          "Biology",
          "Biology",
          "Biology",
          "Biology",
          "Biology",
          "Biology",
        ],
        articleCnt: "1",
        citations: "0",
      },
      {
        name: "Zehuan Mo",
        organization: "Peking University",
        fields: ["Biology", "Gene"],
        articleCnt: "1",
        citations: "0",
      },
      {
        name: "Zehuan Mo",
        organization: "Peking University",
        fields: ["Biology", "Gene"],
        articleCnt: "1",
        citations: "0",
      },
      {
        name: "Zehuan Mo",
        organization: "Tsinghua University",
        fields: ["Biology", "Gene"],
        articleCnt: "1",
        citations: "0",
      },
      {
        name: "Zehuan Mo",
        organization: "Tsinghua University",
        fields: ["Biology", "Gene"],
        articleCnt: "1",
        citations: "0",
      },
      {
        name: "Zehuan Mo",
        organization: "Tsinghua University",
        fields: ["Biology", "Gene"],
        articleCnt: "1",
        citations: "0",
      },
    ]);
    const filteredRegions = () => {
      if (select.value === "1") {
        return regions.value.filter((region) =>
          region.name.includes(input3.value)
        );
      } else if (select.value === "2") {
        return regions.value.filter((region) =>
          region.organization.includes(input3.value)
        );
      } else {
        return regions.value;
      }
    };
    const handleSearch = () => {
      console.log("click success");
      updateTotal();
      fregions.value = filteredRegions();
    };
    const pagination = ref({
      total: 0,
      currentPage: 1,
      pageSize: 5,
    });
    const updateTotal = () => {
      pagination.value.total = filteredRegions().length;
    };

    const pagedRegions = () => {
      const start =
        (pagination.value.currentPage - 1) * pagination.value.pageSize;
      const end = start + pagination.value.pageSize;
      return fregions.value.slice(start, end);
    };
    const handleCurrentChange = (e) => {
      pagination.value.currentPage = e;
    };
    onMounted(() => {
      //window.location.reload();
      input3.value = route.params.input as string;
      select.value = route.params.select as string;
      updateTotal();
      handleCurrentChange(1);
      fregions.value = regions.value;
    });
    return {
      resultCnt,
      value,
      checkList,
      options,
      regions,
      organizations,
      input3,
      select,
      pagination,
      pagedRegions,
      handleCurrentChange,
      filteredRegions,
      handleSearch,
      fregions,
    };
  },
};
</script>
<style scoped>
.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1% 2% 1% 2%;
  background-color: #003d74;
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
  width: 30%;
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
