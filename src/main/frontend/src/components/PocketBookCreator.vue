<template>
    <div class="bg-[var(--md-color-surface)] relative">
        <div class="flex flex-col gap-4 max-w-md">
            <div class="flex items-center justify-between gap-2 h-16">
                <PocketFlowTypeButton type="收入" icon="arrow_downward"></PocketFlowTypeButton>
                <PocketFlowTypeButton type="支出" icon="arrow_upward"></PocketFlowTypeButton>
            </div>

            <input v-model="data.money" :placeholder="data.szqk" type="number"
                class="h-16 w-full font-bold text-4xl text-right" />

            <TypeButtonGroup></TypeButtonGroup>

            <section class="flex flex-col gap-2">
                <p>日期</p>
                <div class="flex items-center justify-between gap-2">
                    <input class="flex-grow" type="date" v-model="data.date" />
                    <input class="flex-grow" type="time" v-model="data.time" />
                </div>
                <p>备注</p>
                <textarea class="w-full" v-model="data.bz" />
            </section>

            <div class="flex flex-col">
                <md-tonal-button v-if="!pendding" @click="submit">保存</md-tonal-button>
                <md-tonal-button v-else disabled @click="submit">
                    <md-icon slot="icon" class="rotate">sync</md-icon>
                    保存
                </md-tonal-button>
            </div>
        </div>
    </div>
</template>

<script setup lang="tsx">
import { useDate } from '@/hooks/useDate';
import { usePocket } from '@/hooks/usePocket';
import { PocketFlowType, UseOfMoneyTypeList } from '@/hooks/usePocket';
import { useAccountStore } from '@/store/useAccountStore';
import { usePocketStore } from '@/store/usePocketStore';
import { reactive, ref } from 'vue';

const data = reactive({
    u_id: useAccountStore().getUser.phone,
    szqk: '收入' as PocketFlowType,
    money: 0,
    lx: [] as string[],
    bz: '',
    date: useDate('y-MM-DD'),
    time: useDate('h:m:s')
})

const pocketStore = usePocketStore()

const pendding = ref(false)
const isError = ref(false)
const submit = async () => {
    pendding.value = true
    isError.value = false
    let res = await pocketStore.push(usePocket({
        szqk: data.szqk,
        money: data.money,
        lx: data.lx,
        bz: data.bz,
        date: data.date + ' ' + data.time
    }))
    if(res.status !== 200) {
        isError.value = true
    }
    pendding.value = false
}

const PocketFlowTypeButton = ({ icon, type }: { icon: string, type: PocketFlowType }) => (
    <div
        onClick={() => data.szqk = type}
        class={[
            data.szqk === type ?
                data.szqk === '支出' ?
                    "bg-[var(--md-sys-color-error-container)]" :
                    "bg-[var(--md-sys-color-primary-container)]" :
                'bg-[var(--md-sys-color-surface-container-low)]',
            "relative select-none hover:elevation rounded-xl text-[var(--md-sys-color-on-surface)] flex items-center justify-center w-full h-full"
        ]}
    >
        <md-elevation></md-elevation>
        <md-icon class="text-4xl font-[700]">{icon}</md-icon>
    </div>
)
const TypeIconButton = ({ icon }: { icon: string }) => (
    <md-filled-tonal-icon-button
        onClick={() => {
            if (data.lx.includes(icon)) {
                data.lx.splice(data.lx.indexOf(icon), 1)
            } else {
                data.lx.push(icon)
            }
        }}
        toggle
    >
        <md-icon>{icon}</md-icon>
    </md-filled-tonal-icon-button>
)
const TypeButtonGroup = () => (
    <div class="flex flex-wrap gap-2">
        {
            UseOfMoneyTypeList.map(e => <TypeIconButton icon={e}></TypeIconButton>)
        }
    </div>
)
</script>

<style scoped></style>