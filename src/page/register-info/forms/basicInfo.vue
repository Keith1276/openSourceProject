<template>
    <div class="common-layout">
        <el-form :model="localBasicInfo" label-width="auto" style="max-width: 700px">
            <el-form-item label="姓名">
                <el-input v-model="localBasicInfo.name" />
            </el-form-item>
        </el-form>
    </div>
</template>

<script lang="ts" setup>
import { reactive, watch } from 'vue';
import { defineProps, defineEmits } from 'vue';

// 接收父组件传入的 basicInfo
const props = defineProps({
    basicInfo: {
        type: Object,
        required: true,
    },
});

// 定义事件，用于向父组件传值
const emit = defineEmits(['update:basicInfo']);

// 创建一个本地的 basicInfo，以便实现双向绑定
const localBasicInfo = reactive({ ...props.basicInfo });

// 监听 localBasicInfo 的变化并同步到父组件
watch(
    () => localBasicInfo,
    (newVal) => {
        emit('update:basicInfo', newVal);
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