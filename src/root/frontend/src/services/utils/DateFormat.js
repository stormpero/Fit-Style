class DateFormat {
    convert(str) {
        const date = new Date(str);
        return date.toLocaleString('ru', {
            day: 'numeric',
            month: 'numeric',
            year: 'numeric'
        })
    }
}

export default new DateFormat()