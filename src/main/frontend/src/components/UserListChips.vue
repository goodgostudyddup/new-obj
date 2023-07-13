<template>
    <md-chip-set ref="chipSetRef" @click="onChangeChipSet">
        <md-filter-chip label="管理员"></md-filter-chip>
        <md-filter-chip label="普通用户"></md-filter-chip>
    </md-chip-set>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue';

type MdChipSpec = MdSelected & MdLabel
type MdSelected = {
    selected: boolean,
}
type MdLabel = {
    label: string,
}

const chipSetRef = ref()
const chipSet = computed(() => chipSetRef.value.chips)

const currentChips = ref<string[]>([])

const onChangeChipSet = () => {
    currentChips.value = []
    Array.from(chipSet.value).forEach(e => {
        if((e as MdChipSpec).selected) {
            currentChips.value.push((e as MdLabel).label)
        }
    })
    
}

</script>

<style scoped>

</style>