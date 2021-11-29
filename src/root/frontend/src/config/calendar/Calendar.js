export const messagesRu = {
    allDay: 'Весль день',
    previous: '<',
    next: '>',
    today: 'Сегодня',
    month: 'Месяц',
    week: 'Неделя',
    day: 'День',
    agenda: 'Список',
    date: 'Дата',
    time: 'Время',
    event: 'Ивент',
    showMore: total => `Показать больше (${total})`
};

export const formats = {
    dateFormat: 'dd',
    monthHeaderFormat: 'MMMM YYYY',
    dayHeaderFormat: 'DD MMMM YYYY',
    dayRangeHeaderFormat: ({ start, end }, culture, local) => {
        return local.format(start, 'DD MMMM', culture) + ' – ' +
            local.format(end, 'DD MMMM', culture);
    },
}

export const scheduleStyle = () => {
    return {
        style: {
            "min-height": "200px"
        }
    }
}