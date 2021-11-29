class DateFormat {
    convertDataToNormalData(str, time = false) {
        const date = new Date(str);
        const options = {
            day: 'numeric',
            month: 'numeric',
            year: 'numeric'
        };
        const optionsTime = {
            hour: 'numeric',
            minute: 'numeric',
            day: 'numeric',
            month: 'numeric',
            year: 'numeric',
        };

        return date.toLocaleString('ru', time ? optionsTime : options);
    }
    convertDataToTime(str) {
        const date = new Date(str);
        const optionsTime = {
            hour: 'numeric',
            minute: 'numeric',
        };
        return date.toLocaleString('ru', optionsTime);
    }
    convertDataTimeToData(str) {
        return this.convertDataToNormalData(str.split('T')[0]);
    }
}

export default new DateFormat()