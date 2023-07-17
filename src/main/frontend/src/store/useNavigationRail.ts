import { defineStore } from "pinia";
import { reactive, ref, watch } from "vue";

type NavButton = {
    label: string,
    path: string,
    icon: string,
    needAdmin: boolean,
    needLogin: boolean,
}

const useNavigationRailStore = defineStore('navigationrail_store', () => {
    const isOpen = ref(false)
    const setIsOpen = (e: boolean) => {
        isOpen.value = e
    }
    const toggleisOpen = () => {
        isOpen.value = !isOpen.value
    }
    
    const windowSize = reactive({
        width: document.body.clientWidth,
        height: document.body.clientHeight
    })
    const setWindowSize = () => {
        windowSize.width = document.body.clientWidth,
        windowSize.height = document.body.clientHeight
    }
    
    const watchWindowSizeAndSetWindowSize = () => {
        window.addEventListener('resize', setWindowSize)
    }
    const unWatchWindowSizeAndSetWindowSize = () => {
        window.removeEventListener('resize', setWindowSize)
    }

    watch(windowSize, () => {
        if (windowSize.width >= 1280) {
            setIsOpen(true)
        } else if (windowSize.width <= 960) {
            setIsOpen(false)
        }
    })
    return {
        isOpen,
        setIsOpen,
        toggleisOpen,
        watchWindowSizeAndSetWindowSize,
        unWatchWindowSizeAndSetWindowSize
    }
})

export type { NavButton }
export { useNavigationRailStore }
