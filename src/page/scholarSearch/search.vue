<template>
  <div class="top-bar">
    <div class="left-topbar">
      <img src="@/asset/search/开源.png" alt="Logo" class="logo" />
      <button style="margin-right: 10%" @click="jumpSearch" class="jumpButton">
        <p style="color: aliceblue">成果搜索</p>
      </button>
      <button class="jumpButton" @click="jumpScholar">
        <p style="color: aliceblue">学者搜索</p>
      </button>
    </div>
    <div class="search-container">
      <input type="text" placeholder="Search..." class="search-input" />
      <button class="search-button">搜索一下</button>
    </div>
    <div class="right-topbar">
      <button class="profile">
        <img
          src="@/asset/search/search_icon.png"
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
import { ref } from "vue";
import { callSuccess, callError, callInfo, callWarning } from "@/call";
import router from "@/router/index.js";

export default {
  name: "search",
  setup() {
    const jumpSearch = () => {
      console.log("jumpSearch click success");
      router.push("/search");
    };
    const jumpScholar = () => {
      console.log("jumpScholar click success");
      router.push("/scholarSearch");
    };
    return {
      jumpSearch,
      jumpScholar,
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
  flex-direction: row;
  align-items: center;
  width: 250px;
}
.right-topbar {
  width: 250px;
  display: flex;
  justify-content: end;
}
.logo {
  height: 30px;
  margin-right: 20%;
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
</style>
