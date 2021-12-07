import React, {useEffect, useState} from 'react';
import Schedule from "./Schedule";
import "./Schedule.css"
import ToastMessages from "../../components/toastmessages/ToastMessages";
import Modal from "../../components/modal/Modal";
import {ScheduleModalTrainingInfo} from "./form/ScheduleModalTrainingInfo";
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
    }, [selectCoach, reload])

    const handleSelectSlot = (event) => {
        if (event.start.getDay() !== event.end.getDay()) {
            ToastMessages.error("Тренировки можно назначать только на определенное время")
            return
        }
        setEventInfo(event);
        setModalActiveCreate(true)
    }

    const deleteTraining = (event) => {
        const {isPersonal, id} = event;
        if (isPersonal) {
            ScheduleApi.deletePersonalTraining(id).then(
                response => {
                    ToastMessages.success("Тренировка удалена");
                },
                error => {
                    ToastMessages.defaultError();
                }
            ).finally(() => {
                setReload(prev => !prev);
                setModalActiveInfo(false);
            })
        } else {
            ScheduleApi.deleteGroupTraining(id).then(
                response => {
                    ToastMessages.success("Тренировка удалена");
                },
                error => {
                    ToastMessages.defaultError();
                }
            ).finally(() => {
                setReload(prev => !prev);
                setModalActiveInfo(false);
            })
        }
    }

    const signTraining = (event) => {
        const {isPersonal, id} = event;
        if (isPersonal) {
            ScheduleApi.signPersonalTraining(id).then(
                response => {
                    ToastMessages.success("Вы успешно записались на тренировку");
                },
                error => {
                    ToastMessages.defaultError();
                }
            ).finally(() => {
                setReload(prev => !prev);
                setModalActiveInfo(false);
            })
        } else {
            ScheduleApi.signGroupTraining(id).then(
                response => {
                    ToastMessages.success("Вы успешно записались на тренировку");
                },
                error => {
                    if (error.response.data.errorCode === 9) {
                        ToastMessages.error("Вы уже записались на эту тренировку!");
                    } else {
                        ToastMessages.defaultError();
                    }

                }
            ).finally(() => {
                setReload(prev => !prev);
                setModalActiveInfo(false);
            })
        }
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
                <ScheduleModalTrainingInfo isActive={modalActiveInfo} isCoach={isCoach} eventInfo={eventInfo} deleteTraining={deleteTraining} signTraining={signTraining}/>
           </Modal>
            <Modal active={modalActiveCreate} setActive={setModalActiveCreate} options={{closeBackground: true}}>
                <ScheduleModalTrainingCreate setActive={setModalActiveCreate} eventInfo={eventInfo} setReload={setReload} eventList={eventList}/>
            </Modal>
        </div>
    );

}