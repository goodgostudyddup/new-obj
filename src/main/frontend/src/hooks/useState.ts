import { UnwrapRef, reactive } from "vue"

function useState<T>(defaultValue: T) {
    var target = reactive({
        value: defaultValue
    })

    const set = (e: UnwrapRef<T>) => {
        target.value = e
    }
    const get = () => target.value

    return {
        set,
        get
    }
}

export {
    useState
}