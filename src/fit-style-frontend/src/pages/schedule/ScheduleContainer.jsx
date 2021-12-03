import React, {useEffect, useState} from 'react';
import Schedule from "./Schedule";
import "./Schedule.css"
import ToastMessages from "../../components/toastmessages/ToastMessages";
import Modal from "../../components/modal/Modal";
import ScheduleModalTrainingInfo from "./form/ScheduleModalTrainingInfo";
import PermissionService from "../../services/security/permission/PermissionService";
import ScheduleApi from "../../services/api/ScheduleApi";
import TrainingService from "../../services/training/TrainingService";
import {ScheduleModalTrainingCreate} from "./form/ScheduleModalTrainingCreate";

export const ScheduleContainer = () => {
    const isCoach = PermissionService.hasRole("COACH");

    const [eventList, setEventList] = useState([]);
    const [coachList, setCoachList] = useState(null);
    const [modalActiveInfo, setModalActiveInfo] = useState(false);
    const [modalActiveCreate, setModalActiveCreate] = useState(false);
    const [eventInfo, setEventInfo] = useState(null)
    const [selectCoach, setSelectCoach] = useState("DEFAULT")
    const [isReady, setIsReady] = useState(false);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        if (isCoach) {
            ScheduleApi.getCoachTrainings().then(
                response => {
                    let trainingsListTemp = TrainingService.concatTrainings(response.data);
                    setEventList(trainingsListTemp);
                },
                error => {
                    ToastMessages.defaultError();
                }).finally(() => setIsReady(true));
        } else {
            ScheduleApi.getCoachesList().then(
                response => {
                    setCoachList(response.data?.coaches)
                },
                error => {
                    ToastMessages.defaultError();
                }
            ).finally(() => setIsReady(true));
        }

    }, [isCoach, reload])

    useEffect(() => {
        if (selectCoach !== "DEFAULT") {
            ScheduleApi.getCoachTrainings(selectCoach).then(
                response => {
                    let trainingsListTemp = TrainingService.concatTrainings(response.data);
                    setEventList(trainingsListTemp);
                },
                error => {
                    ToastMessages.defaultError();
                });
        }
    }, [selectCoach])

    const handleSelectSlot = (event) => {
        if (event.start.getDay() !== event.end.getDay()) {
            ToastMessages.error("Тренировки можно назначать только на определенное время")
            return
        }
        setEventInfo(event);
        setModalActiveCreate(true)

        return;

        let date = new Date(event.start.getFullYear(), event.start.getMonth(), event.start.getDate());
        let count = event.end.getHours() - event.start.getHours();
        let startHours = event.start.getHours();
        let trainings = [];
        for (let i = 0; i < count; i++) {
            let temp = createTraining(i, date, startHours++);
            if (!temp) continue;
            trainings.push(temp)
        }
        setEventList(prevState => prevState.concat(trainings))
        if (trainings.length === 0) {
            ToastMessages.error("Тренировка(и) не создана")
        } else {
            ToastMessages.success("Тренировка(и) созданы")
        }
    }

    const createTraining = (id, date, hours) => {
        let startDate = new Date(new Date(date.getTime()).setHours(hours));
        if (eventList.some(value => value.start.getTime() === startDate.getTime()) ||
            startDate.getTime() <= Date.now()) return null;

        let endDate = new Date(startDate.getTime()).setHours(startDate.getHours() + 1);
        return {
            id: id,
            title: "Тренировка",
            start: startDate,
            end: endDate,
            status: "LOGGED"
        }
    }

    const deleteTraining = (event) => {
        setEventList(prevState => prevState.filter(value => value.start.getTime() !== event.start.getTime()))
        setModalActiveInfo(false)
    }

    const handleSelectInput = (event) => {
        const {value} = event.target;
        setSelectCoach(value);
    }

    const handleSelectEvent = (event) => {
        setModalActiveInfo(true);
        setEventInfo(event)
    }

    return (
        <div>
            { isReady &&
            <Schedule
                lists={{
                    trainings: eventList,
                    coaches: coachList,
                }}
                isCoach={isCoach}
                selectInput={{
                    handleSelect: handleSelectInput,
                    selectValue: selectCoach
                }}
                schedule={{
                    handleSelectSlot: handleSelectSlot,
                    handleSelectEvent: handleSelectEvent
                }}
            />}
           <Modal active={modalActiveInfo} setActive={setModalActiveInfo} options={{closeBackground: true}}>
                <ScheduleModalTrainingInfo isActive={modalActiveInfo} eventInfo={eventInfo} deleteTraining={deleteTraining}/>
           </Modal>
            <Modal active={modalActiveCreate} setActive={setModalActiveCreate} options={{closeBackground: true}}>
                <ScheduleModalTrainingCreate setActive={setModalActiveCreate} eventInfo={eventInfo} setReload={setReload}/>
            </Modal>
        </div>
    );

}

export default ScheduleContainer;