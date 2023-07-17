import { useAccountStore } from "@/store/useAccountStore"
import { useDate } from "./useDate"


const UseOfMoneyTypeList: string[] = [
    'shopping_cart',
    'sports_esports',
    'toys_fan',
    'restaurant',
    'volunteer_activism',
    'family_restroom'
]
type UseOfMoneyType = string
type UseOfMoneyTypes = UseOfMoneyType[]

type PocketFlowType = '收入' | '支出'


type Pocket = {
    u_id: string,
    szqk: PocketFlowType,
    money: string | number,
    lx: UseOfMoneyTypes,
    bz: string,
    date: string,
}

function usePocket({
    szqk = '收入',
    money = '',
    lx = [],
    bz = '',
    date = useDate('MM-DD-yyyy')
}: {
    szqk?: PocketFlowType,
    money?: string | number,
    lx?: UseOfMoneyTypes,
    bz?: string,
    date?: string,
}) {
    const target: Pocket = {
        u_id: useAccountStore().getUser.phone,
        szqk: szqk,
        money: money,
        lx: lx,
        bz: bz,
        date: date
    }

    return target
}

export type { Pocket, PocketFlowType, UseOfMoneyTypes, UseOfMoneyType }
export { usePocket, UseOfMoneyTypeList }
