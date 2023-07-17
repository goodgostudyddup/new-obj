import { defineStore } from "pinia";
import { reactive, watch } from "vue";


const useThemeStore = defineStore('theme_store', () => {
    const theme = reactive({
        isDark: false
    })

    watch(() => theme.isDark, () => {
        document.documentElement.classList[theme.isDark ? 'add': 'remove']('dark')
    })

    const setDark = (e: boolean) => {
        theme.isDark = e
    }
    const toggleDark = () => {
        theme.isDark = !theme.isDark
    }

    return {
        theme,
        setDark,
        toggleDark,        
    }
}, {
    persist: true,
})

export { useThemeStore }
