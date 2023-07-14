import { createRouter, createWebHashHistory } from "vue-router";

import index from '../views/index.vue'
import userlist from '../views/userList.vue'
import register from '../views/register.vue'
import login from '../views/login.vue'
import profile from '@/views/profile.vue'
import willPush from '@/views/willPush.vue'

const routes = [
    {
        path: '/',
        component: index
    },
    {
        path: '/userlist',
        component: userlist
    },
    {
        path: '/login',
        component: login
    },
    {
        path: '/register',
        component: register
    },
    {
        path: '/profile',
        component: profile
    },
    {
        path: '/willpush',
        component: willPush
    },

]
const Router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default Router