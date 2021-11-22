import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment'
import localization from 'moment/locale/ru'
import "react-big-calendar/lib/css/react-big-calendar.css"
import {views} from "react-big-calendar/lib/utils/constants";
import {formats, messagesRu} from "../../services/utils/consts/Calendar";

const myEvents = [
    {
        id: 0,
        title: 'Тренировка',
        start: new Date(2021, 10, 23, 12 - 1, 0, 0),
        end: new Date(2021, 10, 23, 14, 0, 0),
    },
    {
        id: 1,
        title: 'Тренировка',
        start: new Date(2021, 10, 21, 12, 0, 0),
        end: new Date(2021, 10, 21, 13, 0, 0),
    },
    {
        id: 2,
        title: 'DTS STARTS',
        start: new Date(2021, 10, 13, 12, 0, 0),
        end: new Date(2021, 10, 13, 13, 0, 0),
    },

    {
        id: 3,
        title: 'DTS ENDS',
        start: new Date(2016, 10, 6, 0, 0, 0),
        end: new Date(2016, 10, 13, 0, 0, 0),
    },
]

const localize = momentLocalizer(moment)

function CalendarView(props) {
    moment.locale(localization.locale);


    return (
        <div className="container bg-white" >
            <Calendar
                localizer={localize}
                events={myEvents}
                style={{ height: 900 }}
                views={['month', 'week', 'day']}
                defaultView={views.WEEK}
                messages={messagesRu}
                formats={formats}
            />
        </div>
    );
}

export default CalendarView;