import React, {useEffect, useState} from 'react';
import {CalendarView} from "./CalendarView";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import ScheduleApi from "../../services/api/schedule/ScheduleApi";
import TrainingService from "../../services/training/TrainingService";

export const CalendarContainer = () => {
    const [trainingsList, setTrainingsList] = useState([]);

    useEffect(() => {
        ScheduleApi.getTrainings().then(
            response => {
                let trainingsListTemp = TrainingService.concatTrainings(response.data);
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
