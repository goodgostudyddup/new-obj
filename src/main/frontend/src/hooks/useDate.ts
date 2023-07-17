import moment from "moment";

function useDate(format: string) {
    return moment().format(format)
}

export { useDate }
