<template>
    <div class="common-layout">
        <el-card shadow="hover" style="max-width: 700px; margin: 0 auto;">
            <h2>确认信息</h2>
            <el-table :data="infoList" style="width: 100%; margin-top: 16px">
                <el-table-column prop="key" width="200" />
                <el-table-column prop="value" />
            </el-table>
        </el-card>
    </div>
</template>

<script lang="ts" setup>
import { computed } from "vue";
import { defineProps } from "vue";

// 接收父组件传入的 authInfo 和 basicInfo
const props = defineProps({
    authInfo: {
        type: Object,
        required: true,
    },
    basicInfo: {
        type: Object,
        required: true,
    },
});

// 组合 authInfo 和 basicInfo 的数据用于展示
const infoList = computed(() => {
    return [
        { key: "姓名", value: props.basicInfo.name || "未填写" },
        { key: "ORCID", value: props.authInfo.orcid || "未填写" },
    ];
});
</script>
