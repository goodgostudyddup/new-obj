import axios from "axios";
import { defineStore } from "pinia";
import { User } from "./useUserStore";

const useAccountStore = defineStore('account_store', {
    state: () => ({
        isLogin: false,
        user: {
            name: '',
            password: '',
            phone: '',
            isAdmin: false,
        },
    }),
    getters: {
        getUser: (s) => s.user,
        getIsLogin: (s) => s.isLogin,
    },
    actions: {
        async isExist(id: string) {
            return await axios.get('/api/user/findOneById', {
                params: {
                    u_id: id
                }
            })
        },
        async login({ u_id, u_pwd}:{
            u_id: string,
            u_pwd: string
        }) {
            let res = await axios.post('/api/user/login', {
                u_id: u_id,
                u_pwd: u_pwd,
            })
            if(res.data !== '') {
                this.setUser(res.data)
                return true
            }
            return false
        },
        async register(e: User): Promise<boolean> {
            var res = null
            try {
                res = await axios.post('/api/user/register', {
                    u_id: e.u_id,
                    u_name: e.u_name,
                    u_pwd: e.u_pwd,
                })
            } catch {
                return false
            }

            if(res?.data) {
                return true
            }
            return false
        },
        logout() {
            this.isLogin = false
        },
        setUser(e: User) {
            this.user.phone = e.u_id
            this.user.name = e.u_name
            this.user.password = e.u_pwd
            this.user.isAdmin = e.isAdmin
        }
    },
    persist: true,
})

export { useAccountStore }
