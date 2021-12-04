import React from 'react';
import DateFormat from "../../../utils/DateConvert";
import TrainingService from "../../../services/training/ScheduleService";
function ScheduleModalTrainingInfo({isActive, eventInfo, deleteTraining}) {
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
                    <strong>{`${eventInfo.coach.surname} ${eventInfo.coach.name.slice(0, 1)}. ${eventInfo.coach.patronymic.slice(0, 1)}.`}</strong>
                </div>
                <div>
                    <label> Статус </label>
                    <strong>{TrainingService.getStatusName(eventInfo.status)}</strong>
                </div>
            </div>
            <center>
                    <button className="btn btn-danger mt-5" onClick={() => deleteTraining(eventInfo)}>Удалить</button>
            </center>
        </div>
            :
            null
    );
}

export default ScheduleModalTrainingInfo;