import { defineStore } from "pinia";
import axios from "axios";
import { User, Users } from "@/hooks/useUser";


/**
 * useUserStore管理服务器中的user表数据
 */

const useUserStore = defineStore('user_store', {
    state: () => ({
        users: [] as Users,
    }),
    getters: {
        getUsers: (s) => s.users,
    },
    actions: {

        /**
         * 请求服务器获取用户列表
         */
        async request(): Promise<void> {
            try {
                this.users = await (await axios.get('/api/user/findAll')).data
            } catch {
                this.users = [
                    {
                        u_id: '1',
                        u_name: '小红 样例',
                        u_pwd: 'wow 样例',
                        isAdmin: false
                    },
                    {
                        u_id: '2',
                        u_name: 'name 1 样例',
                        u_pwd: '00 样例',
                        isAdmin: false
                    },
                    {
                        u_id: '3',
                        u_name: '小李 1 样例',
                        u_pwd: 'l 样例',
                        isAdmin: false
                    }
                ]    
            }
        },

        /**
         * 查找用户列表的每一个元素的ID、返回用户名、密码，三者任意一个属性包含了指定字符串的对象的数组
         */
        searchByAnyField(keyword: string): Users {
            return this.users.filter(e => e.u_id.toString() == keyword || e.u_name.includes(keyword) || e.u_pwd.includes(keyword) || ((keyword === '管理员' || keyword === '管理') && e.isAdmin) || ((keyword === '普通用户' || keyword === '普通') && !e.isAdmin))
        },

        /**
         * 重新从服务器获取用户列表
         * @see 返回一个Promise<boolean>对象，请在then中处理刷新后的操作或使用await
         */
        async refresh(): Promise<boolean> {
            try {
                this.users = await (await axios.get('/api/user/findAll')).data
            } catch {
                return false
            }
            return true
        },

        /**
         * 移除指定的对象（包括服务器和本地）
         */
        async remove(e: User) {
            await axios.delete('/api/user/deleteOneById', {
                params: {
                    u_id: e.u_id
                }
            }).then(() => {
                this.users.splice(this.users.indexOf(e), 1)    
            })
        },
        
        edit(target: User, e: User) {
            console.log(target);
            axios.post('/api/user/alterUser', {
                u_id: e.u_id,
                u_name: e.u_name,
                u_pwd: e.u_pwd
            }).then(() => {
                console.log('success');
            })
        }
    }
})

export { useUserStore }
export type { User }