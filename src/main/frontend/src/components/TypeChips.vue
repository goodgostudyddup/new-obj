<template>
    <md-chip-set type="filter" ref="chipSetRef" @click="onChanged">
        <md-filter-chip v-for="e in props.chips" :key="e" :label="e" :selected="props.chipsDefaultSelect"></md-filter-chip>
    </md-chip-set>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { MdChipSpec } from '@/type/MDChips'

const props = defineProps<{
    chips: string[],
    chipsDefaultSelect: boolean,
    setCurrentChips: (keyword: string[]) => void
}>()

const chipSetRef = ref()
const getChipSetSelectedChips = () => (chipSetRef.value.chips as MdChipSpec[]).filter(e => e.selected)

const onChanged = () => {
    props.setCurrentChips(getChipSetSelectedChips().map(e => e.label))
}
</script>

<style scoped>

</style>