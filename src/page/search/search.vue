<template>
  <div class="top-bar">
    <div class="left-topbar">
      <img src="@/asset/search/开源.png" alt="Logo" class="logo" />
      <button class="jumpButton" @click="jumpBoard">
        <p style="color: aliceblue">首页</p>
      </button>
      <button @click="jumpSearch" class="jumpButton">
        <p style="color: aliceblue">项目搜索</p>
      </button>
      <button class="jumpButton" @click="jumpScholar">
        <p style="color: aliceblue">开发者搜索</p>
      </button>
    </div>
    <div class="search-container">
      <input
        type="text"
        placeholder="Search..."
        class="search-input"
        v-model="searchQuery"
      />
      <button class="search-button" @click="handleSearch">搜索一下</button>
      <el-popover
        ref="popover"
        v-model="visible"
        title="选项"
        width="400"
        trigger="click"
        popper-append-to-body
      >
        <p>License</p>
        <el-checkbox-group v-model="license" label="License">
          <el-checkbox
            v-for="item in licenses"
            :key="item.value"
            :label="item.value"
            >{{ item.value }}</el-checkbox
          >
        </el-checkbox-group>
        <p>Language</p>
        <el-checkbox-group v-model="language" label="Language">
          <el-checkbox label="JavaScript">JavaScript</el-checkbox>
          <el-checkbox label="Python">Python</el-checkbox>
          <el-checkbox label="Java">Java</el-checkbox>
          <el-checkbox label="C++">C++</el-checkbox>
          <el-checkbox label="C#">C#</el-checkbox>
          <el-checkbox label="Go">Go</el-checkbox>
        </el-checkbox-group>
        <template #reference>
          <el-button class="advanced-search" @click="togglePopover"
            >高级搜索</el-button
          >
        </template>
      </el-popover>
    </div>
    <div class="right-topbar">
      <button class="profile">
        <img
          src="@/asset/search/开源.png"
          alt="Button Image"
          class="button-image"
        />
      </button>
    </div>
  </div>
</template>

<script lang="ts">
import { useStore } from "vuex";
import { register, resetPassword, sendEmail } from "@/api/example";
import { callSuccess, callError, callInfo, callWarning } from "@/call";
import { defineComponent, ref, defineEmits } from "vue";
import { ElButton, ElPopover, ElCheckbox, ElCheckboxGroup } from "element-plus";
import router from "@/router/index.js";

export default defineComponent({
  name: "search",
  components: {
    ElButton,
    ElPopover,
    ElCheckbox,
    ElCheckboxGroup,
  },
  setup(props, { emit }) {
    const jumpSearch = () => {
      console.log("jumpSearch click success");
      router.push("/search");
    };
    const jumpScholar = () => {
      console.log("jumpScholar click success");
      router.push("/scholarSearch");
    };
    const jumpBoard = () => {
      console.log("jumpBoard click success");
      router.push("/Board");
    };
    const visible = ref<boolean>(false);
    const checkedOptions = ref<string[]>([]);
    const license = ref([]);
    const language = ref([]);
    const searchQuery = ref<string>("");
    const content= ref<string[]>([]);

    const handleSearch = () => {
      content.value = searchQuery.value.split(' ');
      let param = {
        license: license.value,
        language: language.value,
        content: content.value,
      };
      emit("handleSearch", param);
      license.value = [];
      language.value = [];
      searchQuery.value = "";
    };

    const licenses = [
      { value: "All" },
      { value: "Apache" },
      { value: "MIT" },
      { value: "Other" },
      { value: "Unlicense" },
      { value: "GNU" },
      { value: "BSD3" },
      { value: "BSD0" },
      { value: "zlib" },
    ];

    const licenseName=new Map<string,string>([
      ['Apache','Apache License 2.0'],
      ['MIT','MIT License'],
      ['Other','Other'],
      ['Unlicense','The Unlicense'],
      ['GNU','GNU General Public License v3.0'],
      ['BSD3','BSD 3-Clause \"New\" or \"Revised\" License'],
      ['BSD0','BSD Zero Clause License'],
      ['zlib','zlib License']
    ])

    const togglePopover = () => {
      visible.value = !visible.value;
    };
    return {
      jumpSearch,
      jumpScholar,
      jumpBoard,
      visible,
      checkedOptions,
      handleSearch,
      togglePopover,
      license,
      language,
      licenses,
      searchQuery,
    };
  },
});
</script>

<style scoped>
.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1% 2% 1% 2%;
  background-color: #dd7050;
  height: 5%;
}

.left-topbar {
  display: flex;
  /* flex-direction: row; */
  justify-content: space-between;
  align-items: center;
  width: 300px;
}
.right-topbar {
  width: 250px;
  display: flex;
  justify-content: end;
}
.logo {
  height: 30px;
  margin-right: 10%;
}

.search-container {
  display: flex;
  align-items: center;
}

.search-input {
  width: 300px;
  height: 20px;
  border-radius: 5px;
  padding: 10px;
}

.search-button {
  padding: 10px 20px;
  height: 40px;
  background-color: #f3c382;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.search-button:hover {
  background-color: #fde8d3;
  color: black;
}

.profile {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: #f9bf6d;
  border: none;
  cursor: pointer;
}

.button-image {
  width: 20px; /* Adjust the size as needed */
  height: 20px;
}
.button-container {
  display: flex;
  flex-direction: row;
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
.advanced-search {
  color: white;
  background-color: #dd7050;
  border-color: #dd7050;
}
</style>
