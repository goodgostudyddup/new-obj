<template>
    <div>
        <header>
            <Display msg="登录"></Display>
            <p class="text-xs">还没有账户？现在<a @click="router.push('/register')">创建</a>一个。</p>
        </header>

        <main>
            <form>
                <md-outlined-text-field 
                    :error="loginActive.isLoginError"
                    v-model.number="user.get().u_id"
                    type="number"
                    label="手机号"
                >
                    <md-icon slot="leadingicon">phone</md-icon>
                </md-outlined-text-field>
                <md-outlined-text-field
                    :error="loginActive.isLoginError"
                    v-model="user.get().u_pwd"
                    maxlength="255"
                    type="password"
                    label="密码"
                >
                    <md-icon slot="leadingicon">password</md-icon>
                </md-outlined-text-field>

                <div class="flex justify-end gap-2">
                    <md-text-button type="reset">重置</md-text-button>
                    <md-filled-button @click="submit" :disabled="!loginActive.isPass()">
                        <template v-if="loginActive.loginPendding">
                            <md-icon slot="icon" class="rotate">cached</md-icon>
                        </template>
                        <template v-else>
                            <md-icon slot="icon">input</md-icon>
                        </template>
                        提交
                    </md-filled-button>
                </div>

                <div v-if="loginActive.isLoginError" class="text-[var(--md-sys-color-error)] text-right text-xs">
                    <p type="error">
                        账户信息不匹配
                    </p>
                </div>
            </form>
        </main>
    </div>
</template>

<script setup lang="ts">
import { Display } from '@/components/Text';
import { useUser } from '@/hooks/useUser';
import { useAccountStore } from '@/store/useAccountStore';
import { reactive } from 'vue';
import { useRouter } from 'vue-router';

const account = useAccountStore()
const user = useUser()
user.setName('null')

const loginActive = reactive({
    isPass: () => user.isNotNull(),
    isLoginError: false,
    loginPendding: false,
})

const router = useRouter()

const submit = async () => {
    loginActive.loginPendding = true
    
    let isSuccess = await account.login({
        u_id: user.get().u_id,
        u_pwd: user.get().u_pwd,
    })

    loginActive.loginPendding = false

    if (isSuccess) {
        account.isLogin = true
        router.push('/')
    }

    loginActive.isLoginError = true
}
</script>

<style scoped>

</style>