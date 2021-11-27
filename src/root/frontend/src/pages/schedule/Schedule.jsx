import React from 'react';
import {Calendar, momentLocalizer} from "react-big-calendar";
import {views} from "react-big-calendar/lib/utils/constants";
import {formats, messagesRu} from "../../services/utils/consts/Calendar";
import moment from "moment";

const localize = momentLocalizer(moment)

const Schedule = ({myEvents, isCoach, handleSelect}) => {
    return (
        <div className="calendar-container" >
            <h1 className="calendar-title">Расписание тренеровок</h1>
            <Calendar
                selectable={isCoach}
                localizer={localize}
                events={myEvents}
                style={{ height: "94vh" }}
                views={['month', 'week', 'day']}
                defaultView={views.WEEK}
                messages={messagesRu}
                formats={formats}
                min={new Date(0, 0, 0, 7, 0, 0)}
                max={new Date(0, 0, 0, 23, 0, 0)}
                onSelectEvent={event => {
                    console.log(event)
                }}
                onSelectSlot={handleSelect}
                step={60}
                timeslots={1}
            />
        </div>
    );
};

export default Schedule;