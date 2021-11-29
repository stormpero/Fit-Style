import React from 'react';

function ScheduleModalTraining({setModalActive, event, deleteTraining}) {
    return (
        <div>
            <h4 className="title">Информация о тренировке</h4>
            <div className="d-flex justify-content-around">
                <p>
                    <label> Время </label>
                    <strong>20.11.2021</strong>
                </p>
                <p>
                    <label> ФИО тренера </label>
                    <strong>20.11.2021</strong>
                </p>
                <p>
                    <label> Статус </label>
                    <strong>20.11.2021</strong>
                </p>
            </div>
            <center>
                    <button className="btn btn-danger mt-5" onClick={() => deleteTraining(event)}>Удалить</button>
            </center>
        </div>
    );
}

export default ScheduleModalTraining;