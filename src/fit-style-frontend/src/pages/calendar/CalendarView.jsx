import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment'
import localization from 'moment/locale/ru'
import * as custom from "../../config/calendar/Calendar";

import "react-big-calendar/lib/css/react-big-calendar.css"
import "./CalendarView.css"
import ScheduleService from "../../services/training/ScheduleService";
import TrainingService from "../../services/training/ScheduleService";
import solo from "../../assets/solo-training.png";
import group from "../../assets/group.png";

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
                    let fio = event.isPersonal ? ScheduleService.getCutFio(event?.fitUser) : event.numberOfUsers + "/20";
                    return event.isPersonal ? fio : event.title + " " + fio;
                }}
                eventPropGetter={
                    (event) => {
                        return {
                            style: {
                                backgroundColor: "#4e54c8",
                                borderColor: "#383c91"
                            }
                        }
                    }
                }
                components={{
                    event: CalendarViewEvent
                }}
            />
        </div>
    );
}
const CalendarViewEvent = ({ event }) => {
    return (
        <div className="container-training">
            <div className="info-training">
                {event.isPersonal &&<strong>{ScheduleService.getCutFio(event?.fitUser)}</strong>}
                {!event.isPersonal && <p className="p-0 m-0">{event?.title} {event?.numberOfUsers + "/20"}</p>}
            </div>
            <div className="picture-training">
                <img className="training-picture" src={event.isPersonal? solo : group } alt={event.title + "-training-picture"}/>
            </div>
        </div>
    )
}
