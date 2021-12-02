import React, {useEffect, useState} from 'react';
import {CalendarView} from "./CalendarView";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import ScheduleApi from "../../services/api/schedule/ScheduleApi";

export const CalendarContainer = () => {
    const [trainingsList, setTrainingsList] = useState([]);

    useEffect(() => {
        ScheduleApi.getTrainings().then(
            response => {
                let trainingsListTemp = [];
                addTraining(trainingsListTemp, response.data?.personalTrainings, true);
                addTraining(trainingsListTemp, response.data?.groupTrainings, false);

                setTrainingsList(trainingsListTemp);
            },
            error => {
                console.error(error);
                ToastMessages.error("Произошла ошибка, повторите ошибку позже");
            }
        )
    }, [])

    const addTraining = (trainingsListTemp, array, isPersonal) => {
        array.forEach(element => {
            let date = new Date(element.date);
            date.setHours(date.getHours() + 1);
            trainingsListTemp.push({
                id: element.id + " ",
                title: "Тренер: " + element?.coachId,
                startDate: new Date(element.date),
                endDate: date,
                isPersonal: isPersonal,
            })
        })
    }

    return (<CalendarView events={trainingsList} />);
}
