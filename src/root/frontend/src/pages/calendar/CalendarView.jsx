import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment'
import localization from 'moment/locale/ru'
import "react-big-calendar/lib/css/react-big-calendar.css"
import {views} from "react-big-calendar/lib/utils/constants";
import {formats, messagesRu} from "../../services/utils/consts/Calendar";

const localize = momentLocalizer(moment)

function CalendarView(props) {
    moment.locale(localization.locale);
    return (
        <div className="container bg-white" >
            <Calendar
                localizer={localize}
                events={props.events}
                style={{ height: 900 }}
                views={['month', 'week', 'day']}
                defaultView={views.WEEK}
                messages={messagesRu}
                formats={formats}
                min={new Date(0, 0, 0, 7, 0, 0)}
            />
        </div>
    );
}

export default CalendarView;