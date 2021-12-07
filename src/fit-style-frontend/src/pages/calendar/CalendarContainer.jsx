import React, {useEffect, useState} from 'react';
import {CalendarView} from "./CalendarView";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import ScheduleApi from "../../services/api/ScheduleApi";
import TrainingService from "../../services/training/TrainingService";
import PermissionService from "../../services/security/permission/PermissionService";

export const CalendarContainer = () => {
    const isCoach = PermissionService.hasRole("COACH");
    const [trainingsList, setTrainingsList] = useState([]);

    useEffect(() => {
        if (isCoach) {
            ScheduleApi.getOccupiedCoachTrainings().then(
                response => {
                    let trainingsListTemp = TrainingService.concatTrainings(response.data);
                    setTrainingsList(trainingsListTemp);
                },
                error => {
                    ToastMessages.defaultError();
                });
        } else {
            ScheduleApi.getTrainings().then(
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
