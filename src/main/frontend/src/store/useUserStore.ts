import { defineStore } from "pinia";
import axios from "axios";

type User = {
    u_id: string,
    u_name: string,
    u_pwd: string
}

const useUserStore = defineStore('user_store', {
    state: () => ({
        users: [] as User[],
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
                this.users = await axios.get('http://localhost:8080/getUsers')
            } catch {
                this.users = [
                    {
                        u_id: '1 样例',
                        u_name: '小红 样例',
                        u_pwd: 'wow 样例'
                    },
                    {
                        u_id: '2 样例',
                        u_name: 'name 1 样例',
                        u_pwd: '00 样例'
                    },
                    {
                        u_id: '3 样例',
                        u_name: '小李 1 样例',
                        u_pwd: 'l 样例'
                    }
                ]    
            }
        },

        /**
         * 查找用户列表的每一个元素的ID、返回用户名、密码，三者任意一个属性包含了指定字符串的对象的数组
         */
        searchByAnyField(keyword: string): User[] {
            return this.users.filter(e => e.u_id.includes(keyword) || e.u_name.includes(keyword) || e.u_pwd.includes(keyword))
        },

        /**
         * 重新从服务器获取用户列表
         * @see 返回一个Promise<boolean>对象，请在then中处理刷新后的操作或使用await
         */
        async refresh(): Promise<boolean> {
            try {
                this.users = await axios.get('http://localhost:8080/getUsers')
            } catch {
                return false
            }
            return true
        },

        /**
         * 移除指定的对象（包括服务器和本地）
         */
        remove(e: User) {
            console.log(e);
            
        }
    }
})

export { useUserStore }
export type { User }