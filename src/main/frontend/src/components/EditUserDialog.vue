<template>
    <Teleport to="#app">
        <md-dialog :open="props.isOpenDialog">
            <h1 slot="header">编辑用户</h1>

            <div class="flex flex-col gap-2 mt-2">
                <md-outlined-select label="用户类型">
                    <md-select-option :selected="!props.user.isAdmin" :value="false" headline="普通用户"></md-select-option>
                    <md-select-option :selected="props.user.isAdmin" :value="true" headline="管理员"></md-select-option>
                </md-outlined-select>
                <md-outlined-text-field v-model="props.user.u_name" label="用户名"></md-outlined-text-field>
                <md-outlined-text-field v-model="props.user.u_pwd" label="密码"></md-outlined-text-field>
                <md-outlined-text-field v-model="props.user.u_id" disabled label="ID"></md-outlined-text-field>
            </div>

            <md-text-button slot="footer" @click="props.closeDialog">返回</md-text-button>
            <md-filled-button slot="footer" @click="props.submitDialog(props.user)">提交</md-filled-button>
        </md-dialog>

    </Teleport>
</template>

<script setup lang="ts">
import { User } from '@/store/useUserStore';

const props = defineProps<{
    user: User,
    isOpenDialog: boolean,
    closeDialog: () => void,
    submitDialog: (e: User) => void,
}>()
</script>

<style scoped>

</style>