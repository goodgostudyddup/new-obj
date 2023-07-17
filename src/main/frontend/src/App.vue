<template>
    <div class="relative flex flex-col w-full bg-[var(--md-sys-color-background)] h-screen max-h-screen">
        <nav ref="topNavigationRef" class="sticky top-0 z-50 w-full">
            <TopNavigation>
                <template #header>
                    <OpenNavigationRailButton></OpenNavigationRailButton>
                    <section>
                        <Title></Title>
                        <Subtitle></Subtitle>
                    </section>
                </template>

                <!-- Open ASIDE PANEL -->
                <SettingIconButton @click="isOpenAsidePanel.set(!isOpenAsidePanel.get())"></SettingIconButton>
            </TopNavigation>
        </nav>
        <div class="relative flex" :style="mainAvalidHeight">

            <!-- Left NavRail -->
            <LeftNavigationRail class=""></LeftNavigationRail>

            <!-- Content -->
            <main class="relative w-full ml-2 md:ml-0 mr-2 overflow-clip">
                <div class="relative rounded-3xl bg-[var(--md-sys-color-surface-container-lowest)] p-4 md:p-8 overflow-scroll" :style="mainAvalidHeight">
                    <router-view v-slot="{ Component }">
                        <component :is="Component"></component>
                    </router-view>
                </div>
            </main>

            <!-- Right Content ASIDE PANEL -->
            <AsideWindow :is-open="isOpenAsidePanel.get()" :set-is-open="isOpenAsidePanel.set" :style="mainAvalidHeight">
                <SettingsContent></SettingsContent>
            </AsideWindow> 
        </div>
    </div>
</template>

<script setup lang="tsx">
import { computed, onMounted, onUnmounted, ref } from 'vue';
import OpenNavigationRailButton from '@/components/OpenNavigationRailButton.vue';
import NavigationRail from './components/NavigationRail.vue'
import TopNavigation from './components/TopNavigation.vue';
import { NavButton, useNavigationRailStore } from './store/useNavigationRail';
import { useAccountStore } from './store/useAccountStore';
import NavigationRailButton from './components/NavigationRailButton.vue';
import DarkSwitch from '@/components/DarkSwitch.vue'
import { Title, Subtitle } from '@/components/Title'
import { SettingIconButton } from './components/SettingIconButton';
import { useState } from './hooks/useState';
import AsideWindow from '@/components/AsideWindow.vue'

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
const LeftNavigationRail = () => (
    <div
        class={[
            useNavigationRailStore().isOpen ? 'translate-x-0' : '-translate-x-full',
            "transition fixed mt-[72px] md:mt-0 md:relative top-0 md:translate-x-0 md:left-auto h-screen z-50"
        ]}
    >
        <NavigationRail class="flex-none">
        {
            account.getUser.isAdmin && account.isLogin ?
                routerList.map(e => <NavigationRailButton key={e.path} routerItem={e}></NavigationRailButton>) : 
                routerList.filter(e => !e.needAdmin).map(e => <NavigationRailButton key={e.path} routerItem={e}></NavigationRailButton>)
        }
        </NavigationRail>
    </div>
)


const isOpenAsidePanel = useState(false)
const SettingsContent = () => (
    <div>
        <label>
            <p>深色模式</p>
            <DarkSwitch></DarkSwitch>
        </label>

    </div>
)


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
