import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment'
import "react-big-calendar/lib/css/react-big-calendar.css"

const localizer = momentLocalizer(moment)
const myEvents = [
    {
        id: 0,
        title: 'All Day Event very long title',
        allDay: true,
        start: new Date(2015, 3, 0),
        end: new Date(2015, 3, 1),
    },
    {
        id: 1,
        title: 'Long Event',
        start: new Date(2015, 3, 7),
        end: new Date(2015, 3, 10),
    },

    {
        id: 2,
        title: 'DTS STARTS',
        start: new Date(2022, 1, 13, 0, 0, 0),
        end: new Date(2022, 1, 14 + 1, 0, 0, 0),
    },

    {
        id: 3,
        title: 'DTS ENDS',
        start: new Date(2016, 10, 6, 0, 0, 0),
        end: new Date(2016, 10, 13, 0, 0, 0),
    },
]
function CalendarView(props) {
    return (
        <div className="container bg-white" >
            <Calendar
                localizer={localizer}
                events={myEvents}
                startAccessor="start"
                endAccessor="end"
                style={{ height: 800 }}
            />
        </div>
    );
}

export default CalendarView;