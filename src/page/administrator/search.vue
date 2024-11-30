<template>
  <div class="top-bar">
    <div class="left-topbar">
      <img src="@/asset/search/search_icon.png" alt="Logo" class="logo" />
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

export default {
  name: "search",
  setup() {},
};
</script>

<style scoped>
@import "style.css";
</style>
