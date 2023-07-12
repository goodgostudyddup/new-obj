<template>
    <main class="flex flex-row min-h-screen relative">
        <nav class="fixed top-0 left-0">
            <NavigationRail :open="open" :setOpen="setOpen" :togglePanel="togglePanel"></NavigationRail>
        </nav>

        <main class="flex-grow bg-[var(--md-sys-color-surface-container-low)]" :class="open ? 'ml-44' : 'ml-20'">
            <TopNavigation></TopNavigation>
            <div class="max-w-6xl mx-auto px-4">
                <router-view v-slot="{ Component }">
                    <component :is="Component"></component>
                </router-view>
            </div>
        </main>

    </main>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import NavigationRail from './components/NavigationRail.vue'
import TopNavigation from './components/TopNavigation.vue';

const open = ref(false)
const setOpen = (e: boolean) => {
    open.value = e
}
const togglePanel = () => {
    open.value = !open.value
}
</script>

<style scoped>

</style>