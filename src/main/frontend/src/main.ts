import { createApp } from 'vue'
import App from './App.vue'

/**
 * Material about
 */
import 'material-symbols'
import './assets/material-web'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

/**
 * TailwindCSS
 */
import './assets/index.css'

/**
 * Store
 */
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

/**
 * Router
 */
import router from './router/index'

const app = createApp(App)
app
.use(pinia)
.use(router)
.mount('#app')

