<template>
    <div class="relative flex flex-col w-full bg-[var(--md-sys-color-background)] h-screen max-h-screen">
        <nav ref="topNavigationRef" class="sticky top-0 z-50 w-full">
            <TopNavigation></TopNavigation>
        </nav>
        <div class="relative flex h-full">
            <NavigationRail class="flex-none"></NavigationRail>
            <main class="relative w-full mr-2">
                <div class="relative rounded-3xl bg-[var(--md-sys-color-surface-container-lowest)] p-4 md:p-8 overflow-y-scroll" :style="mainAvalidHeight">
                    <router-view v-slot="{ Component }">
                        <component :is="Component"></component>
                    </router-view>
                </div>
                <footer></footer>
            </main>
            <aside></aside>
        </div>
    </div>
</template>

<script setup lang="ts">
import { computed, onMounted, onUnmounted, ref } from 'vue';
import NavigationRail from './components/NavigationRail.vue'
import TopNavigation from './components/TopNavigation.vue';

/**
 * 控制main的元素高度
 */
const topNavigationRef = ref<HTMLElement>()
var innerHeight = ref(window.innerHeight)
const updateInnerHeight = () => {
    innerHeight.value = window.innerHeight
}
onMounted(() => {
    window.addEventListener('resize', updateInnerHeight)
})
onUnmounted(() => {
    window.removeEventListener('resize', updateInnerHeight)
})
const mainAvalidHeight = computed(() => {
    if (topNavigationRef.value == undefined) {
        return ''
    }
    return 'height:' + (innerHeight.value - topNavigationRef.value.clientHeight - 16) + 'px'
})
</script>

<style scoped></style>