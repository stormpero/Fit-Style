import React from 'react';
import {Calendar, momentLocalizer} from "react-big-calendar";
import {views} from "react-big-calendar/lib/utils/constants";
import {formats, messagesRu, scheduleStyle} from "../../services/utils/consts/Calendar";
import moment from "moment";
import Modal from "../../components/modal/Modal";
import NewsFormContainer from "../news/form/NewsFormContainer";

const localize = momentLocalizer(moment)

const Schedule = ({myEvents, isCoach, handleSelect, eventStatusStyle, eventSelectHandle}) => {
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
                onSelectEvent={eventSelectHandle}
                onSelectSlot={handleSelect}
                step={60}
                timeslots={1}
                eventPropGetter={(event) => eventStatusStyle(event.status)}
                titleAccessor={event => {
                    return "ФИО Тренера Статус"
                }}
                slotPropGetter={() => scheduleStyle}
                dayLayoutAlgorithm={'no-overlap'}
            />
        </div>
    );
};

export default Schedule;