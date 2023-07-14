<template>
    <div class="relative flex flex-col w-full bg-[var(--md-sys-color-background)] h-screen max-h-screen">
        <nav ref="topNavigationRef" class="sticky top-0 z-50 w-full">
            <TopNavigation></TopNavigation>
        </nav>
        <div class="relative flex" :style="mainAvalidHeight">
            <template v-if="account.getUser.isAdmin && account.isLogin">
                <NavigationRail :router-list="routerList" class="flex-none"></NavigationRail>
            </template>
            <template v-else>
                <NavigationRail :router-list="routerList.filter(e => !e.needAdmin)" class="flex-none"></NavigationRail>
            </template>
            <main class="relative w-full mr-2 overflow-clip">
                <div class="relative rounded-3xl bg-[var(--md-sys-color-surface-container-lowest)] p-4 md:p-8 overflow-scroll" :style="mainAvalidHeight">
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
import { NavButton } from './store/useNavigationRail';
import { useAccountStore } from './store/useAccountStore';

const account = useAccountStore()

/**
 * 用于NavigationRail按钮的路由表
 */
 const routerList: NavButton[] = [
    {
        label: '首页',
        path: '/',
        icon: 'home',
        needAdmin: false,
        needLogin: false,
    },
    {
        label: '用户管理',
        path: '/userlist',
        icon: 'manage_accounts',
        needAdmin: true,
        needLogin: true,
    },
    {
        label: '个人中心',
        path: '/profile',
        icon: 'person',
        needAdmin: false,
        needLogin: false,
    },
    {
        label: '实验室',
        path: '/willpush',
        icon: 'labs',
        needAdmin: false,
        needLogin: false,
    },
]



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