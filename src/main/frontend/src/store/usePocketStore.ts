import { defineStore } from "pinia";
import axios from "axios";
import { Pocket } from "@/hooks/usePocket";

const usePocketStore = defineStore('pocket_store', {
    state: () => ({

    }),
    getters: {

    },
    actions: {
        async push(e: Pocket) {
            return await axios.get('/api/zb/pushOne', {
                params: {
                    ...e,
                    lx: e.lx.toString()
                }
            })
        }
    }
})

export { usePocketStore }
