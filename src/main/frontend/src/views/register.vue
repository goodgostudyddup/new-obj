<template>
    <div>
        <header>
            <Display msg="注册"></Display>
            <p class="text-xs">已经拥有账户了？前往<a @click="router.push('/login')">登录</a>。</p>
        </header>

        <main>
            <form>
                <md-outlined-text-field 
                    :error="registerActive.isExists"
                    :supportingText="registerActive.isExists ? '账户已存在' : '账户可用'"
                    v-model="user.get().u_id"
                    maxlength="11"
                    label="手机号"
                >
                    <md-icon slot="leadingicon">phone</md-icon>
                </md-outlined-text-field>
                <md-outlined-text-field 
                    v-model="user.get().u_name"
                    maxlength="255"
                    label="用户名"
                >
                    <md-icon slot="leadingicon">face</md-icon>
                </md-outlined-text-field>
                <md-outlined-text-field
                    v-model="user.get().u_pwd"
                    maxlength="255"
                    type="password"
                    label="密码"
                >
                    <md-icon slot="leadingicon">password</md-icon>
                </md-outlined-text-field>
                
                <div class="flex justify-end gap-2">
                    <md-text-button type="reset">重置</md-text-button>
                    <md-filled-button @click="submit" :disabled="!registerActive.isPass()">
                        <template v-if="registerActive.pendding">
                            <md-icon slot="icon" class="rotate">cached</md-icon>
                        </template>
                        <template v-else>
                            <md-icon slot="icon">input</md-icon>
                        </template>
                        提交
                    </md-filled-button>
                </div>

                <div v-if="registerActive.isRegisterError" class="text-[var(--md-sys-color-error)] text-right text-xs">
                    <p type="error">
                        似乎出现了意外
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
import { reactive, watchEffect } from 'vue';
import { useRouter } from 'vue-router';

const account = useAccountStore()
const user = useUser()

const registerActive = reactive({
    isExists: false,
    isPass: () => user.isNotNull() && !registerActive.isExists,
    pendding: false,
    isRegisterError: false,
})


watchEffect(() => {
    if (user.isValid(user.get().u_id)) {
        account.isExist(user.get().u_id).then(res => {
            registerActive.isExists = res.data
        })
    }
})


const router = useRouter()

const submit = async () => {
    registerActive.pendding = true
    let isSuccess = await account.register({
        u_id: user.get().u_id,
        u_name: user.get().u_name,
        u_pwd: user.get().u_pwd,
    })

    registerActive.pendding = false

    if (isSuccess) {
        router.push('/login')
    }

    registerActive.isRegisterError = true
}


</script>

<style scoped></style>