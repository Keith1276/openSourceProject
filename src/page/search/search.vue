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

</style>
