<template>
    <div>
        <header>
            <Display msg="个人中心"></Display>
        </header>

        <main>
            <template v-if="!account.getIsLogin">
                <div class="text-start">
                    <p>
                        您还没有登录，请尝试<a @click="router.push('/login')">登陆</a>后访问个人中心。
                    </p>
                </div>
            </template>
            <template v-else>
                <div>
                    <p>
                        欢迎, {{ account.getUser.name }}
                    </p>
                </div>
                <div>
                    <md-text-button @click="account.logout">
                        <md-icon slot="icon">logout</md-icon>
                        退出登录
                    </md-text-button>
                </div>
            </template>
        </main>
    </div>
</template>

<script setup lang="ts">
import { Display } from '@/components/Text';
import { useAccountStore } from '@/store/useAccountStore';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';


const account = useAccountStore()


const router = useRouter()

onMounted(() => {
})
</script>

<style scoped>

</style>