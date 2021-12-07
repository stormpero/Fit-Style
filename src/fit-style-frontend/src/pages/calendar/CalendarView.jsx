import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment'
import localization from 'moment/locale/ru'
import * as custom from "../../config/calendar/Calendar";

import "react-big-calendar/lib/css/react-big-calendar.css"
import "./CalendarView.css"

const localize = momentLocalizer(moment)

export const CalendarView = ({events}) => {
    moment.locale(localization.locale);
    return (
        <div className="calendar-container" >
            <h1 className="calendar-title">Календарь</h1>
            <Calendar
                localizer={localize}
                startAccessor={'startDate'}
                endAccessor={'endDate'}
                events={events}
                style={{ height: "94vh"}}
                views={custom.views}
                defaultView={custom.defaultView}
                messages={custom.messagesRu}
                formats={custom.formats}
                min={custom.minTime}
                max={custom.maxTime}
                step={custom.step}
                timeslots={custom.timeslots}
                titleAccessor={event => {
                    let fio = `${event.fitUser.surname} ${event.fitUser.name.slice(0, 1)}. ${event.fitUser.patronymic.slice(0, 1)}.`;
                    return event.isPersonal ? fio : event.title + " " + fio;
                }}
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
