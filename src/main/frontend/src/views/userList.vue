<template>
    <div>
        <header>
            <Display msg="用户管理"></Display>
        </header>

        <main>
            <div class="flex items-center justify-between">
                <SearchInput></SearchInput>
                <RefreshButton></RefreshButton>
            </div>

            <table class="rounded-xl border overflow-clip w-full">
                <thead class="bg-[var(--md-sys-color-surface-container-lowest)] border-b border-[var(--md-sys-color-outline)]">
                    <tr class="font-semibold">
                        <th>编号</th>
                        <th>用户名</th>
                        <th>密码</th>
                        <th>修改</th>
                    </tr>
                </thead>
                
                <tbody class="bg-[var(--md-sys-color-surface-container-lowest)] rounded-xl space-y-4">
                    <tr v-for="e in filter" :key="e.u_id">
                        <td>{{ e.u_id }}</td>
                        <td>{{ e.u_name }}</td>
                        <td>{{ e.u_pwd }}</td>
                        <td>
                            <div>
                                <md-standard-icon-button @click="() => {
                                    currentEditUser = e
                                    setOpenDialog(true)
                                }">
                                    <md-icon>edit</md-icon>
                                </md-standard-icon-button>

                                <md-standard-icon-button @click="userStore.remove(e)">
                                    <md-icon>delete</md-icon>
                                </md-standard-icon-button>
                            </div>
                        </td>
                    </tr>
                </tbody>

            </table>
        </main>
    </div>

    <template v-if="currentEditUser != null">
        <EditUserDialog :user="currentEditUser" :is-open-dialog="isOpenDialog" :close-dialog="closeDialog" :submit-dialog="submitDialog"></EditUserDialog>
    </template>
</template>

<script setup lang="tsx">
import { Display } from '@/components/Text';
import { computed, onMounted, reactive, ref } from 'vue';
import { User, useUserStore } from '@/store/useUserStore'
import EditUserDialog from '@/components/EditUserDialog.vue';


const userStore = useUserStore()

/**
 * 获取用户数据
 */
onMounted(() => {
    userStore.request()
})

const pending = ref<boolean>(false)
const refresh = () => {
    pending.value = true
    userStore.refresh().then(e => {
        if(e) {
            console.log('refresh success');
        } else {
            console.log('refresh error');
        }
        pending.value = false
    })
}

/**
 * 不应该将userStore.getUsers直接输出到DOM，请经过filter方法
 */
const filter = computed(() => {
    return search.keyword.length !== 0 ? userStore.searchByAnyField(search.keyword) : userStore.getUsers
})

const search = reactive({
    keyword: ''
})



/**
 * TSX Area
 */
const SearchInput = () => (
    <input type="search" value={search.keyword} onInput={e => search.keyword = (e.target as HTMLInputElement).value} class="rounded-md px-4 py-2 outline-none hover:shadow-md" placeholder='Search'></input>
)

const RefreshButton = () => (
    <md-tonal-button disabled={pending.value} onClick={refresh}>
        {
            !pending.value ? 
                <md-icon slot="icon">refresh</md-icon> :
                <md-icon slot="icon" class="rotate">sync</md-icon>
        }
        刷新
    </md-tonal-button>
)



/**
 * 编辑功能
 */
const currentEditUser = ref()
const isOpenDialog = ref(false)
const setOpenDialog = (e: boolean) => {
    isOpenDialog.value = e
}
const submitDialog = (e: User) => {
    closeDialog()
    userStore.edit(currentEditUser.value, e)
}
const closeDialog = () => {
    setOpenDialog(false)
}
</script>

<style >
input {
    transition: all 0.15s;
}

th,
td {
    @apply px-4 py-2 text-start;
}

</style>