import React from 'react';
import {Calendar, momentLocalizer} from "react-big-calendar";
import {views} from "react-big-calendar/lib/utils/constants";
import {formats, messagesRu} from "../../config/calendar/Calendar";
import moment from "moment";
import TrainingService from "../../services/training/ScheduleService";
import ScheduleService from "../../services/training/ScheduleService";
import group from "../../assets/group.png";
import solo from "../../assets/solo-training.png";

const localize = momentLocalizer(moment)

const Schedule = ({lists, isCoach, selectInput, schedule}) => {
    return (
        <div className="calendar-container" >
            <h1 className="calendar-title">Расписание тренеровок</h1>
            {!isCoach &&
                <div>
                    <select className="form-control" name=""
                            onChange={selectInput.handleSelect}
                            value={selectInput.selectValue}>
                        <option value="DEFAULT">Выберите тренера</option>
                        {lists.coaches?.map((coach, index) => <option value={coach?.id} key={index}>{coach?.surname} {coach?.name} {coach?.patronymic}</option>)}
                    </select>
                </div>
            }
            <Calendar
                selectable={isCoach}
                localizer={localize}
                startAccessor={'startDate'}
                endAccessor={'endDate'}
                events={lists.trainings}
                style={{ height: "94vh" }}
                views={['month', 'week', 'day']}
                defaultView={views.WEEK}
                messages={messagesRu}
                formats={formats}
                min={new Date(0, 0, 0, 7, 0, 0)}
                max={new Date(0, 0, 0, 23, 0, 0)}
                onSelectEvent={schedule.handleSelectEvent}
                onSelectSlot={schedule.handleSelectSlot}
                step={60}
                timeslots={1}
                eventPropGetter={(event) => {
                    console.log(event)
                    return TrainingService.getEventStatusColor(event.status)
                }}

                dayLayoutAlgorithm={'no-overlap'}
                components={{
                    event: ScheduleEvent,
                }}
            />
        </div>
    );
};

const ScheduleEvent = ({ event }) => {
    return (
        <div className="container-training">
            <div className="info-training">
                <strong>{ScheduleService.getCutFio(event?.fitUser)}</strong>
                {!event.isPersonal && <p className="p-0 m-0">{event?.title} {event?.numberOfUsers + "/20"}</p>}
                <p className="p-0 m-0">Статус: {TrainingService.getStatusName(event?.status)}</p>
            </div>
            <div className="picture-training">
                <img className="training-picture" src={event.isPersonal? solo : group } alt={event.title + "-training-picture"}/>
            </div>
        </div>
    )
}

export default Schedule;