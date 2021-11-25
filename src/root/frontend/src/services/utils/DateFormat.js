class DateFormat {
    convertDataToNormalData(str) {
        const date = new Date(str);
        return date.toLocaleString('ru', {
            day: 'numeric',
            month: 'numeric',
            year: 'numeric'
        })
    }
    convertDataTimeToData(str) {
        return this.convertDataToNormalData(str.split('T')[0]);
    }
    addHour(date) {

    }
}

export default new DateFormat()