<template>
  <div class="main-contanier">
    <search></search>
    <div class="top-contanier">
      <h2 style="color: #dd7050">作者搜索</h2>
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
            <el-icon><Search /></el-icon>
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
          <p style="color: #cca663">筛选</p>
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
              <p style="color: #cca663">学者({{ resultCnt }})</p>
            </div>
            <div class="content-top-right">
              <p style="color: #cca663">排序</p>
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
                  <button class="jumpButton" @click="jumpPersonal">
                    <p style="font-weight: bold; margin-top: 1px">
                      {{ region.name }}
                    </p>
                  </button>
                  <p style="font-size: small; margin-top: 1px">
                    {{ region.organization }}
                  </p>
                  <div class="fields">
                    <p
                      v-for="item in region.fields"
                      style="
                        font-size: small;
                        margin-top: 0.1px;
                        color: #92af83;
                      "
                    >
                      {{ item }}
                      <el-divider direction="vertical" />
                    </p>
                  </div>
                </div>

                <div class="region-top-end">
                  <p>
                    文献数量：<b style="font-weight: bold; color: #cca663">{{
                      region.articleCnt
                    }}</b>
                  </p>
                  <p style="margin-left: 15px">
                    被引用数：<b style="font-weight: bold; color: #cca663">{{
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
    const jumpPersonal = () => {
      console.log("jumpPersonal click success");
      router.push("/personal");
    };
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
      fregions,
      jumpPersonal,
    };
  },
};
</script>
<style scoped>
.button-image {
  width: 20px;
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
  background-color: #92af83;
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
  background-color: #92af83;
}
.jumpButton {
  border: none;
  background: none;
  padding: 0;
  margin: 0;
  outline: none;
  cursor: pointer;
  color: #5a7860;
  font-family: inherit;
  font-size: inherit;
}
</style>
