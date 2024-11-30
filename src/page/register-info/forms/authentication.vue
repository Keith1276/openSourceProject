<template>
    <div class="common-layout">
        <el-form :model="localAuthInfo" label-width="auto" style="max-width: 700px">
            <el-form-item label="ORCID">
                <el-input v-model="localAuthInfo.orcid" />
            </el-form-item>
        </el-form>
    </div>
</template>

<script lang="ts" setup>
import { reactive, watch } from 'vue';
import { defineProps, defineEmits } from 'vue';

// 接收父组件传入的 authInfo
const props = defineProps({
    authInfo: {
        type: Object,
        required: true,
    },
});

// 定义事件，用于向父组件传值
const emit = defineEmits(['update:authInfo']);

// 创建一个本地的 authInfo 以便双向绑定
const localAuthInfo = reactive({ ...props.authInfo });

// 监听 localAuthInfo 的变化并同步到父组件
watch(
    () => localAuthInfo,
    (newVal) => {
        emit('update:authInfo', newVal);
    },
    { deep: true }
);
</script>


<style scoped>
.common-layout {
    display: flex;
    justify-content: center;
}
</style>