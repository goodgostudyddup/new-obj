<template>
    <li
        :class="{ 'bg-[var(--md-sys-color-primary-container)] text-[var(--md-sys-color-on-primary-container)]': router.currentRoute.value.path === props.routerItem.path }"
        class="rounded-[16px] relative w-full h-14" @click="() => router.push(props.routerItem.path)"
    >
        <md-ripple></md-ripple>
        <div
            class="flex items-center h-full rounded-xl"
            :class="navStore.isOpen ? 'justify-start gap-2 pl-4' : 'justify-center'"
        >
            <md-icon :class="{ 'text-[var(--md-sys-color-primary)]': router.currentRoute.value.path !== props.routerItem.path }">
                {{ props.routerItem.icon }}
            </md-icon>
            <h1
                :class="{ 'text-[var(--md-sys-color-on-primary-container)]': router.currentRoute.value.path === props.routerItem.path }"
                class="text-sm" v-show="navStore.isOpen"
            >
                {{ props.routerItem.label }}
            </h1>
        </div>
    </li>
</template>

<script setup lang="ts">
import { NavButton, useNavigationRailStore } from '@/store/useNavigationRail';
import { useRouter } from 'vue-router';

const router = useRouter()
const navStore = useNavigationRailStore()

const props = defineProps<{
    routerItem: NavButton
}>()
</script>

<style scoped></style>