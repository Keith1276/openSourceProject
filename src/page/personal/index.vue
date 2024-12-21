<template>
  <div class="main-contanier">
    <search></search>
    <div class="content-container">
      <div class="left-container">
        <div class="img-container">
          <el-avatar
            class="avatar"
            fit="fill"
            :src="require('@/asset/scholarSearch/lyh.jpg')"
          />
        </div>
        <div class="info-container">
          <h2 style="margin-bottom: 5%">{{ login }}</h2>
          <el-button type="info" text bg>Follow</el-button>
          <div class="follow-container">
            <el-icon><User /></el-icon>
            <p style="">
              <b style="font-weight: bold; color: #cca663">{{ followers }}</b>
              followers
            </p>
          </div>
        </div>
      </div>
      <div class="right-container">
        <el-divider />
        <div v-for="(region, index) in regions" :key="index" class="region">
          <div class="content">
            <div class="left-content">
              <div class="name_and_state">
                <p style="font-size: larger; color: #cca663">
                  {{ region.name }}
                </p>
                <span style="font-size: smaller; color: gray" class="state"
                  >Public</span
                >
              </div>
              <div class="lan_and_time">
                <span
                  :style="{
                    'background-color': languageColor.get(region.language),
                  }"
                  class="square"
                ></span>
                <p style="font-size: smaller; color: gray">
                  {{ region.language }}
                </p>
                <p style="font-size: smaller; color: gray; margin-left: 3%">
                  Update {{ region.updated_at }}
                </p>
              </div>
            </div>
            <div class="right-content">
              <a
                :href="region.html_url"
                target="_blank"
                rel="noopener noreferrer"
              >
                <el-button type="info" style="width: 30%" text bg
                  >Star</el-button
                >
              </a>
            </div>
          </div>
          <el-divider />
        </div>
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
</template>

<script lang="ts">
import search from "./search.vue";
import { ref, computed, onMounted, watch } from "vue";
import router from "@/router/index.js";
import { useRoute } from "vue-router";
import { get_repo_list } from "@/api/personal";
export default {
  components: { search },
  setup() {
    const login = ref("Coke_And_Ice");
    const followers = ref("100");
    const followings = ref("100");
    const html_url = "https://github.com/ifduyue/http-getit";
    const route = useRoute();
    const id = ref(1);
    const regions_get = ref([]);
    const regions = ref([
      {
        name: "Tutorial-2024",
        updated_at: "2 weeks ago",
        language: "JavaScript",
        color: "green",
        html_url: "https://github.com/",
      },
      {
        name: "Dysphagia-Diagnostic",
        updated_at: "Nov 1",
        language: "HTML",
        color: "red",
        html_url: "https://github.com/",
      },
      {
        name: "Tutorial-2024",
        updated_at: "2 weeks ago",
        language: "JavaScript",
        color: "green",
        html_url: "https://github.com/",
      },
      {
        name: "Tutorial-2024",
        updated_at: "2 weeks ago",
        language: "JavaScript",
        color: "green",
        html_url: "https://github.com/",
      },
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
    const pagination = ref({
      total: 0,
      currentPage: 1,
      pageSize: 4,
    });
    const handleCurrentChange = async (e) => {
      pagination.value.currentPage = e;
      try {
        regions_get.value = await get_repo_list(
          id.value,
          pagination.value.currentPage,
          pagination.value.pageSize
        );
        console.log(regions_get.value);
      } catch (error) {
        console.error("Failed to load repos:", error);
      }
    };
    onMounted(() => {
      id.value = Number(route.params.id as string);
      handleCurrentChange(1);
    });
    const jumpGit = (html_url) => {
      console.log("jumpGit click success");
      window.location.href = html_url;
    };
    return {
      login,
      followers,
      followings,
      regions,
      pagination,
      handleCurrentChange,
      jumpGit,
      html_url,
      languageColor,
      route,
      id,
      regions_get,
    };
  },
};
</script>
<style scoped>
.main-contanier {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
}
.content-container {
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 95%;
}
.left-container {
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  width: 30%;
  padding-top: 3%;
  padding-left: 8%;
  background-color: #f4f4f4;
  /* border: 1px solid #d3d2d2; */
}
.right-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 70%;
  background-color: #f4f4f4;
  padding-left: 5%;
  padding-right: 10%;
  max-height: 100vh;
}
.img-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 35vh;
  height: 35vh;
  border-radius: 50%;
  border: 1px solid #f1e0a8;
}
.avatar {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.info-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  flex: 1;
}
.info-container h2 {
  margin-bottom: 0.2em;
}
.el-button {
  width: 35vh;
  border-width: 1px;
  border-style: solid;
  border-color: #d3d2d2;
}
.follow-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.region {
  min-height: 20%;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.name_and_state {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
}
.state {
  border-radius: 40%;
  border: 1px solid gray;
  padding: 5px;
  margin-left: 1%;
}
.lan_and_time {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
}
.square {
  display: inline-block;
  width: 10px;
  height: 10px;
  margin-right: 5px;
}
.left-content {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 70%;
}
.content {
  display: flex;
  justify-content: space-between;
  width: 100%;
}
.right-content {
  display: flex;
  align-items: center;
  width: 30%;
  justify-content: flex-end;
}
.right-content a {
  width: 100%;
  display: flex;
  justify-content: flex-end;
}
.page {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 2%;
  margin-bottom: 5%;
}
</style>
