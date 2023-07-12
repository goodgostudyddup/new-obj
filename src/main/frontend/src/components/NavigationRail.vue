<template>
    <nav class="bg-[var(--md-sys-color-surface)] h-screen" :class="props.open ? 'w-44' : 'w-20'">
        <div class="flex flex-col justify-between px-4 pb-4 h-full">
            <div class="flex flex-col" :class="props.open ? 'items-start' : 'items-center'">
                <div class="flex justify-center items-center h-14">
                    <md-standard-icon-button @click="props.togglePanel">
                        <md-icon>menu</md-icon>
                    </md-standard-icon-button>
                </div>
                <ul class="flex flex-col gap-2">
                    <li>
                        <!-- <img src="../images/1.png" alt=""> -->
                        <!-- <span class="text-xs">欢迎您！管理员</span> -->
                    </li>
                    <template v-for="e in routerList" :key="e.path">
                        <NavigationButton :label="e.label" :path="e.path" :icon="e.icon"></NavigationButton>
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
import { onMounted, onUnmounted, reactive, watch } from 'vue';
import { useRouter } from 'vue-router';
import DarkSwitch from './DarkSwitch.vue';

const props = defineProps<{
    open: boolean,
    setOpen: (e: boolean) => void,
    togglePanel: () => void
}>()

type NavButton = {
    label: string,
    path: string,
    icon: string
}


const routerList: NavButton[] = [
    {
        label: '后台首页',
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

const router = useRouter()


const NavigationButton = ({ label, path, icon }: NavButton) => (
    <li class="overflow-clip rounded-[16px] max-w-min">
        <md-fab class={props.open ? "w-32" : "w-14"} variant={router.currentRoute.value.path === path ? "primary" : "lowered"} label={props.open ? label : ''} onClick={() => router.push(path)}>
            <md-icon slot="icon">{icon}</md-icon>
        </md-fab>
    </li>
)




const windowSize = reactive({
    width: document.body.clientWidth,
    height: document.body.clientHeight
})
const setWindowSize = () => {
    windowSize.width = document.body.clientWidth,
        windowSize.height = document.body.clientHeight
}
onMounted(() => {
    window.addEventListener('resize', setWindowSize)
})
onUnmounted(() => {
    window.removeEventListener('resize', setWindowSize)
})
watch(windowSize, () => {
    if (windowSize.width >= 1280) {
        props.setOpen(true)
    } else if (windowSize.width <= 640) {
        props.setOpen(false)
    }
})
</script>

<style scoped>
nav {
    transition: all 0.15s;
}
</style>