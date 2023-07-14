<template>
    <TypeChips :chips="type" :chips-default-select="false" :set-current-chips="setType"></TypeChips>
    <TypeChips :chips="props.seachKeyword.filter(e => !type.includes(e))" :chips-default-select="true" :set-current-chips="setCustomType"></TypeChips>
</template>

<script setup lang="ts">
import TypeChips from '@/components/TypeChips.vue'
import { reactive } from 'vue';

const props = defineProps<{
    seachKeyword: string[],
    setSeachKeyword: (keyword: string[]) => void
}>()

/**
 * 预定义的用户类型
 */
const type = ['管理员', '普通用户']

/**
 * 用户类型在TypeChips中存在冲突，需要单独存储用于在setCustomType中
 */
const currentChips = reactive({
    type: [] as string[],
})

const setType = (e: string[]) => {
    currentChips.type = e
    props.setSeachKeyword([...props.seachKeyword.filter(e => !type.includes(e)), ...e])
}
const setCustomType = (e: string[]) => {
    props.setSeachKeyword([...e, ...currentChips.type])
}

</script>

<style scoped>

</style>