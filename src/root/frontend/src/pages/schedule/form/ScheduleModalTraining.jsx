import React from 'react';

function ScheduleModalTraining({setModalActive, event, deleteTraining}) {
    return (
        <div>
            <button onClick={() => deleteTraining(event)}>Удалить</button>
        </div>
    );
}

export default ScheduleModalTraining;