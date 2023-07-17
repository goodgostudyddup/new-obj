<template>
    <nav class="relative h-full" :class="navStore.isOpen ? 'w-44' : 'w-20'">
        <div class="relative flex flex-col justify-between px-3 pb-4 h-full bg-[var(--md-sys-color-surface)]">
            <div>
                <ul class="relative flex flex-col gap-2 w-full">
                    <slot></slot>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script setup lang="tsx">
import { useNavigationRailStore } from '@/store/useNavigationRail';
import { onMounted, onUnmounted } from 'vue';

const navStore = useNavigationRailStore()
onMounted(() => {
    navStore.watchWindowSizeAndSetWindowSize()
})
onUnmounted(() => {
    navStore.unWatchWindowSizeAndSetWindowSize()
})
</script>

<style scoped>
nav {
    transition: all 0.15s;
}
</style>