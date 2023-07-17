<template>
    <md-standard-icon-button @click="theme.toggleDark">
        <md-icon>{{ theme.theme.isDark ? 'dark_mode' : 'light_mode'" }}</md-icon>
    </md-standard-icon-button>
</template>

<script setup lang="ts">
import { useThemeStore } from '@/store/useThemeStore';
import { ref, onMounted } from 'vue';

const theme = useThemeStore()

const switchRef = ref()

onMounted(() => {
    switchRef.value.selected = theme.theme.isDark
})
</script>

<style scoped>

</style>