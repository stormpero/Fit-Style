export const messagesRu = {
    allDay: 'Весь день',
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

export const maxTime = new Date(0, 0, 0, 23, 0, 0);
export const minTime = new Date(0, 0, 0, 7, 0, 0);
export const views = ['month', 'week', 'day'];
export const defaultView = 'week';
export const step = 60;
export const timeslots = 1;