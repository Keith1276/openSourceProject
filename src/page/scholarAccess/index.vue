<template>
  <div class="bg-contanier"></div>
  <div class="main-contanier">
    <search></search>
    <div class="top-contanier">
      <div>
        <h1 style="font-size: 6dvh">Access Experts</h1>
      </div>
      <div class="input">
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
            <el-icon @click="handleJump"><Search /></el-icon>
          </template>
        </el-input>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import search from "./search.vue";
import router from "@/router/index.js";

import { ref, computed, onMounted, watch } from "vue";
export default {
  components: { search },
  setup() {
    const input3 = ref("");
    const select = ref("");
    const handleJump = () => {
      console.log("handleJump click success");
      router.push("/scholarSearch/" + input3.value + "/" + select.value);
      //window.location.reload();
    };
    return {
      input3,
      select,
      handleJump,
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
  height: 5vh;
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
  width: 100vw;
  height: 100vh;
  position: fixed;
}
.top-contanier {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 50%;
  height: 95vh;
  align-items: flex-start;
  padding-left: 10%;
}
.bg-contanier {
  background: url("@/asset/scholarSearch/bg2.jpg");
  opacity: 0.5;
  background-size: cover;
  position: fixed;
  height: 100vh;
  width: 100vw;
  z-index: -2;
  top: 0;
  left: 0;
}
.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
}
.input {
  height: 40px;
  width: 80%;
}
</style>
