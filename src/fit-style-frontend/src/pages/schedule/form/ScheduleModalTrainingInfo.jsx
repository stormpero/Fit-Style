import React from 'react';
import DateFormat from "../../../utils/DateConvert";
import TrainingService from "../../../services/training/ScheduleService";
export const ScheduleModalTrainingInfo = ({isActive, isCoach, eventInfo, deleteTraining, signTraining}) => {
    return (eventInfo && !eventInfo.action ?
        <div>
            <h4 className="title">Информация о тренировке</h4>
            <div className="d-flex justify-content-around">
                <div>
                    <label> Время начала </label>
                    <p><strong>{DateFormat.convertDataToTime(eventInfo.startDate)}, {DateFormat.convertDataToNormalData(eventInfo.startDate)}</strong></p>
                </div>
                <div>
                    <label> ФИО тренера </label>
                    <strong>{`${eventInfo.fitUser.surname} ${eventInfo.fitUser.name.slice(0, 1)}. ${eventInfo.fitUser.patronymic.slice(0, 1)}.`}</strong>
                </div>
                <div>
                    <label> Статус </label>
                    <strong>{TrainingService.getStatusName(eventInfo.status)}</strong>
                </div>
            </div>
            <center>
                {isCoach ?
                    <button className="btn btn-danger mt-5" onClick={() => deleteTraining(eventInfo)}>Удалить</button>
                    :
                    eventInfo.status === "LOGGED" ?
                    <button className="btn btn-primary mt-5" onClick={() => signTraining(eventInfo)}>Записаться</button>
                    :
                    null
                }

            </center>
        </div>
            :
            null
    );
}