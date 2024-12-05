<template>
  <el-container>

    <el-header>
      <search></search>
    </el-header>

      <el-main>
        <div class="left-container">
          <p>这里左端放热点开源项目</p>
        </div>

        <div class="right-container">
          <p>这里右端放热点开源开发者</p>
        </div>>
      </el-main>
  </el-container>

</template>

<script>
import router from "@/router/index.js";
export default
{
  name:"Board",
  data()
  {
    return{

    };
  },
};

</script>
<style scoped>

</style>
