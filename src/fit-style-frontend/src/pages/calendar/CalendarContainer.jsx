import React, {useEffect, useState} from 'react';
import {CalendarView} from "./CalendarView";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import TrainingService from "../../services/training/TrainingService";
import {useRole} from "../../customHooks/useRole";
import {training} from "../../packages/api";

export const CalendarContainer = () => {
    const isCoach = useRole("COACH");

    const [trainingsList, setTrainingsList] = useState([]);

    useEffect(() => {
        if (isCoach) {
            training.getOccupiedCoachTrainings().then(
                response => {
                    let trainingsListTemp = TrainingService.concatTrainings(response.data);
                    setTrainingsList(trainingsListTemp);
                },
                error => {
                    ToastMessages.defaultError();
                });
        } else {
            training.getTrainings().then(
                response => {
                    let trainingsListTemp = TrainingService.concatTrainings(response.data);
                    setTrainingsList(trainingsListTemp);
                },
                error => {
                    console.error(error);
                    ToastMessages.error("Произошла ошибка, повторите ошибку позже");
                }
            );
        }
    }, [isCoach])

    return (<CalendarView events={trainingsList} />);
}
