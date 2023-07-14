<template>
    <nav class="relative  h-full" :class="navStore.isOpen ? 'w-44' : 'w-20'">
        <div class="relative flex flex-col justify-between px-3 pb-4 h-full bg-[var(--md-sys-color-surface)]">
            <div>
                <ul class="relative flex flex-col gap-2 w-full">
                    <template v-for="e in routerList" :key="e.path">
                        <NavigationRailButton :router-item="e"></NavigationRailButton>
                    </template>
                </ul>
            </div>

            <div>
                <DarkSwitch></DarkSwitch>
            </div>
        </div>
    </nav>
</template>

<script setup lang="tsx">
import DarkSwitch from './DarkSwitch.vue';
import { NavButton, useNavigationRailStore } from '@/store/useNavigationRail';
import { onMounted, onUnmounted } from 'vue';
import NavigationRailButton from '@/components/NavigationRailButton.vue'

/**
 * 用于按钮的路由表
 */
const routerList: NavButton[] = [
    {
        label: '首页',
        path: '/',
        icon: 'home',
    },
    {
        label: '用户管理',
        path: '/userlist',
        icon: 'manage_accounts',
    },
    {
        label: '个人中心',
        path: '/profile',
        icon: 'person',
    },
]

const navStore = useNavigationRailStore()
onMounted(() => {
    navStore.watchWindowSizeAndSetWindowSize()
})
onUnmounted(() => {
    navStore.unWatchWindowSizeAndSetWindowSize()
})
</script>

<style scoped>
nav,
li {
    transition: all 0.15s;
}
li h1 {
    animation: opacityFromZeroToFull 0.5s;
    opacity: 1;
}
@keyframes opacityFromZeroToFull {
    from {
        opacity: 0;
    } to {
        opacity: 1;
    }
}
* {
    @apply select-none;
}
</style>