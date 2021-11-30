import React, {useEffect, useState} from 'react';
import Schedule from "./Schedule";
import "./Schedule.css"
import ToastMessages from "../../components/toastmessages/ToastMessages";
import Modal from "../../components/modal/Modal";
import ScheduleModalTraining from "./form/ScheduleModalTraining";
import PermissionService from "../../services/security/permission/PermissionService";
import ScheduleApi from "../../services/api/schedule/ScheduleApi";

export const ScheduleContainer = () => {
    const isCoach = PermissionService.hasRole("COACH");

    const [eventList, setEventList] = useState([]);
    const [coachList, setCoachList] = useState(null);
    const [modalActive, setModalActive] = useState(false);
    const [eventInfo, setEventInfo] = useState(null)
    const [selectCoach, setSelectCoach] = useState("")
    const [isReady, setIsReady] = useState(false);

    useEffect(() => {
        ScheduleApi.getCoachesList().then(
            response => {
                setCoachList(response.data?.coaches)
            },
            error => {
                ToastMessages.defaultError();
            }
        ).finally(() => setIsReady(true));
    }, [])

    const handleSelect = (event) => {
        if (event.start.getDay() !== event.end.getDay()) {
            ToastMessages.error("Тренировки можно назначать только в определенный день")
            return
        }

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

    const handleSelectInput = (event) => {
        const {value} = event.target;
        setSelectCoach(value);

        if (value !== "DEFAULT") {
            ScheduleApi.getCoachTrainings(value).then(
                response => {

                },
                error => {
                    ToastMessages.defaultError();
                });
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
        setModalActive(false)
    }

    const eventSelectHandle = (event) => {
        setModalActive(true);
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
                    handleSelect: handleSelect,
                    eventSelectHandle: eventSelectHandle
                }}
            />}
           <Modal active={modalActive} setActive={setModalActive} options={{closeBackground: true}}>
                <ScheduleModalTraining setActive={setModalActive} eventInfo={eventInfo} deleteTraining={deleteTraining}/>
            </Modal>

        </div>
    );

}

export default ScheduleContainer;