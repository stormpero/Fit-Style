import React, {useEffect, useState} from 'react';
import ScheduleApi from "../../../services/api/schedule/ScheduleApi";
import ScheduleService from "../../../services/training/ScheduleService";
import ToastMessages from "../../../components/toastmessages/ToastMessages";

export const ScheduleModalTrainingCreate = ({setActive, eventInfo, setReload}) => {
    const [checked, setChecked] = useState(false);
    const [trainingId, setTrainingId] = useState("");
    const [groupTrainingTypes, setGroupTrainingTypes] = useState([]);
    const [isReady, setIsReady] = useState(false)

    useEffect(() => {
        ScheduleApi.getTrainingsName().then(
            response => {
                setGroupTrainingTypes(response.data?.trainingNames)
            },
            error => {
                console.log(error)
                ToastMessages.defaultError();
            }
        ).finally(() => setIsReady(true))
    }, [])

    const handleSubmit = () => {
        const dateList = ScheduleService.getTrainingsListFromDate(eventInfo.start, eventInfo.end);
        if (checked) {
            if (trainingId === "DEFAULT") return ToastMessages.error("Выберите вид тренировки!");

            dateList.forEach(value => {
                addGroupTraining({
                    date: value,
                    trainingId: trainingId
                });
            })

        } else {
            dateList.forEach(value => {
                addPersonalTraining({
                    date: value
                });
            })
        }
    }

    const addGroupTraining = (data) => {
        ScheduleApi.addGroupTraining(data).then(
            response => {
                ToastMessages.success("Тренировка(и) созданы")
                setReload(prev => !prev);
                setActive(false);
            },
            error => {
                ToastMessages.defaultError();
                console.log(error)
            }
        )
    }

    const addPersonalTraining = (data) => {

        ScheduleApi.addPersonalTraining(data).then(
            response => {
                ToastMessages.success("Тренировка(и) созданы")
                setReload(prev => !prev);
                setActive(false);
            },
            error => {
                ToastMessages.defaultError();
                console.log(error)
            }
        )
    }

    return (
        <div>
            {isReady &&
                <div>
                    <h3>Создание тренировки(ок)</h3>
                    <label>
                        <input
                            type="checkbox"
                            checked={checked}
                            onChange={() => setChecked(prev => !prev)}
                        />
                        <span className="m-lg-2">Групповая тренировка</span>
                    </label>
                    {checked &&
                    <select className="form-control mb-3 mt-3" name="groupTrainingTypes"
                            onChange={(e) => setTrainingId(e.target.value)}
                            value={trainingId}>
                        <option value="DEFAULT">Выберите вид тренировки</option>
                        {groupTrainingTypes.map((value, index) => <option value={value.id} key={index}>{value.name}</option>)}
                    </select>
                    }
                    <div className="d-flex justify-content-around">
                        <button className="btn-primary" onClick={handleSubmit}>Создать</button>
                        <button className="btn-danger" onClick={() => setActive(false)}>Отменить</button>
                    </div>
                </div>
            }
        </div>
    );
};