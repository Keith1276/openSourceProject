<template>
    <div class="bg" />
    <div class="common-layout">
        <el-container>
            <el-header>
                <div class="steps">
                    <el-steps style="max-width: 600px" :active="active" finish-status="success" align-center>
                        <el-step title="基本信息填写" :icon="Edit" />
                        <el-step title="学者身份验证" :icon="Collection" />
                        <el-step title="完成" :icon="CircleCheck" />
                    </el-steps>
                </div>
            </el-header>
            <div class="divider" />
            <el-main>
                <div class="form" v-if="active == 0">
                    <BasicInfo v-model:basicInfo="basicInfo"/>
                </div>
                <div class="form" v-if="active == 1">
                    <Authentication v-model:authInfo="authInfo" />
                </div>
                <div class="form" v-if="active == 2">
                    <Confirm :authInfo="authInfo" :basicInfo="basicInfo" />
                </div>
            </el-main>
            <el-footer>
                <el-button v-if="active != 0" style="margin-top: 12px" @click="pre">Previous step</el-button>
                <el-button v-if="active != 2" style="margin-top: 12px" @click="next">Next step</el-button>
                <el-button v-if="active == 2" style="margin-top: 12px" @click="submit">Submit</el-button>
            </el-footer>
        </el-container>
    </div>
</template>

<script lang="ts" setup>
import { Edit, Collection, CircleCheck } from "@element-plus/icons-vue";
import { ref } from "vue";
import { reactive } from 'vue'
import BasicInfo from "./forms/basicInfo.vue";
import Authentication from "./forms/authentication.vue";
import Confirm from "./forms/confirm.vue";

const active = ref(0);

const authInfo = reactive({
    orcid: '',
})

const basicInfo = reactive({
    name: '',
})

function pre() {
    if (active.value > 0) active.value--;
};

function next() {
    if (active.value < 2) active.value++;
};

function submit() {
    console.log(authInfo, basicInfo);
};
</script>

<style scoped>
/* 
  整体布局
 */
 .bg {
  background-color: #f4f4f4;
  position: fixed;
  height: 100vh;
  width: 100vw;
  z-index: -3;
}

.common-layout {
  padding: 5vh 8vw;
  word-break: break-all;
  word-wrap: break-word;
}

.el-container {
    margin: 3vh 0 3vh 3vw;
    background-color: white;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    border-radius: 4px;
}

.el-header {
    height: 70px;
    margin-bottom: 20px;
    margin-top: 60px;
    display: flex;
    justify-content: center;
}

.steps {
    width: 480px;
}

.divider {
    width: 50%;
    height: 1px;
    background-color: rgba(0, 0, 0, 0.15);
    margin: 10px auto;
    justify-items: center;
}
</style>
