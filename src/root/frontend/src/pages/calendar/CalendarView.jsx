import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment'
import localization from 'moment/locale/ru'
import "react-big-calendar/lib/css/react-big-calendar.css"
import {views} from "react-big-calendar/lib/utils/constants";
import {formats, messagesRu} from "../../config/calendar/Calendar";
import "./CalendarView.css"
import withDragAndDrop from "react-big-calendar/lib/addons/dragAndDrop";

const localize = momentLocalizer(moment)
const DnDCalendar = withDragAndDrop(Calendar);

function CalendarView({events}) {
    moment.locale(localization.locale);
    return (
        <div className="calendar-container" >
            <h1 className="calendar-title">Календарь</h1>
            <DnDCalendar
                localizer={localize}
                events={events}
                style={{ height: "94vh"}}
                views={['month', 'week', 'day']}
                defaultView={views.WEEK}
                messages={messagesRu}
                formats={formats}
                min={new Date(0, 0, 0, 7, 0, 0)}
                max={new Date(0, 0, 0, 23, 0, 0)}
                step={60}
                timeslots={1}
                eventPropGetter={
                    (event, start, end, isSelected) => {
                        return {
                            style: {
                                backgroundColor: event.isPersonal ? "#008080" : "#FF8C00",
                                borderColor: event.isPersonal ? "#008080" : "#FF8C00"
                            }
                        }
                    }
                }
            />
        </div>
    );
}

export default CalendarView;