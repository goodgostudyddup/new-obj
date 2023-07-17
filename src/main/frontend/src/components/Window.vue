<template>
    <Teleport to="#app">
        <div
            v-show="props.isOpen"
            class="overflow-scroll rounded-[16px] z-[999] p-2 bg-[var(--md-sys-color-surface-container-lowest)]"
            :class="{
                'fixed top-0 left-0 w-screen h-screen': state.isFullscreen,
                'fixed right-2 bottom-2 w-auto h-2/3 shadow': !state.isFullscreen && props.fixed
            }"
        >
            <div
                class="relative"
            >
                <nav class="flex justify-end items-center gap-2 mb-2">
                    <template v-if="props.fullscreen">
                        <ExitFullscreen v-if="state.isFullscreen"></ExitFullscreen>
                        <Fullscreen v-else></Fullscreen>
                    </template>
                    <Close></Close>
                </nav>
                <slot></slot>
            </div>
        </div>
    </Teleport>

</template>

<script setup lang="tsx">
import { reactive } from 'vue';


const props = withDefaults(defineProps<{
    isOpen: boolean,
    setIsOpen: (e: boolean) => void,
    fullscreen?: boolean,
    fixed?: boolean,
}>(), {
    fullscreen: false,
    fixed: false,
})

const state = reactive({
    isFullscreen: false
})
const setFullscreen = (e: boolean) => {
    state.isFullscreen = e
}

const NavButton = ({ icon, clickFn }: { icon: string, clickFn: () => void}) => (
    <md-standard-icon-button onClick={clickFn}>
        <md-icon>{ icon }</md-icon>
    </md-standard-icon-button>
)
const ExitFullscreen = () => <NavButton icon="fullscreen_exit" clickFn={() => setFullscreen(false)}></NavButton>
const Fullscreen = () => <NavButton icon="fullscreen" clickFn={() => setFullscreen(true)}></NavButton>
const Close = () => <NavButton icon="close" clickFn={() => props.setIsOpen(false)}></NavButton>

</script>

<style scoped></style>