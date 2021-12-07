import React from 'react';
import {Calendar, momentLocalizer} from "react-big-calendar";
import {views} from "react-big-calendar/lib/utils/constants";
import {formats, messagesRu} from "../../config/calendar/Calendar";
import moment from "moment";
import TrainingService from "../../services/training/ScheduleService";

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
                        {lists.coaches.map((coach, index) => <option value={coach.id} key={index}>{coach.surname} {coach.name} {coach.patronymic}</option>)}
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
                eventPropGetter={(event) => TrainingService.getEventStatusColor(event.status)}
                titleAccessor={event => {
                    let fio = `${event.fitUser.surname} ${event.fitUser.name.slice(0, 1)}. ${event.fitUser.patronymic.slice(0, 1)}. Статус: ${TrainingService.getStatusName(event.status)}`;
                    return event.isPersonal ? fio : event.title + " " + fio + " " + event.numberOfUsers + "/20";
                }}
                dayLayoutAlgorithm={'no-overlap'}

            />
        </div>
    );
};

export default Schedule;