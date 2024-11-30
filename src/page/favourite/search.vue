<template>
  <div class="top-bar">
    <div class="left-topbar">
      <img src="@/asset/search/search_icon.png" alt="Logo" class="logo" />
    </div>
    <div class="search-container">
      <input type="text" placeholder="Search..." class="search-input" v-model="inputValue" />
      <button class="search-button" @click="printInputValue">搜索一下</button>
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

<script lang="ts" setup>
import { useStore } from "vuex";
import { register, resetPassword, sendEmail } from "@/api/example";
import { ref, defineEmits } from "vue";
import { callSuccess, callError, callInfo, callWarning } from "@/call";

// send message
const emit = defineEmits(['inputSend']);
var inputValue = ref(null);
const printInputValue = (): void => {
  emit('inputSend', inputValue.value);
  inputValue.value=null;
};

</script>

<style scoped>
@import "style.css";
</style>
