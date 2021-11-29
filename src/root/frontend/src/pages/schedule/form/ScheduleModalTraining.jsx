import React from 'react';
import DateFormat from "../../../utils/DateConvert";
import TrainingService from "../../../services/training/TrainingService";
function ScheduleModalTraining({setModalActive, eventInfo, deleteTraining}) {

    return (eventInfo ?
        <div>
            <h4 className="title">Информация о тренировке</h4>
            <div className="d-flex justify-content-around">
                <div>
                    <label> Время начала </label>
                    <p><strong>{DateFormat.convertDataToTime(eventInfo.start)}, {DateFormat.convertDataToNormalData(eventInfo.start)}</strong></p>
                </div>
                <div>
                    <label> ФИО тренера </label>
                    <strong>ID</strong>
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

export default ScheduleModalTraining;