import React, {useEffect, useState} from 'react';
import {CalendarView} from "./CalendarView";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import ScheduleApi from "../../services/api/schedule/ScheduleApi";

export const CalendarContainer = () => {
    const [trainingsList, setTrainingsList] = useState([]);

    useEffect(() => {
        ScheduleApi.getTrainings().then(
            response => {
                let {personalTrainings, groupTrainings} = response.data;
                personalTrainings.forEach(value => {
                    value.startDate = new Date(value.startDate);
                    value.endDate = new Date(value.endDate);
                    value.isPersonal = true;
                    return value;
                })

                groupTrainings.forEach(value => {
                    value.startDate = new Date(value.startDate);
                    value.endDate = new Date(value.endDate);
                    return value;
                })

                let trainingsListTemp = personalTrainings.concat(groupTrainings);
                console.log(trainingsListTemp)
                setTrainingsList(trainingsListTemp);
            },
            error => {
                console.error(error);
                ToastMessages.error("Произошла ошибка, повторите ошибку позже");
            }
        )
    }, [])

    return (<CalendarView events={trainingsList} />);
}
