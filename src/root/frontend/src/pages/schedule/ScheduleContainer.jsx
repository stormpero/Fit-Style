import React, {useState} from 'react';
import Schedule from "./Schedule";
import "./Schedule.css"
import ToastMessages from "../../services/utils/ToastMessages";
import Modal from "../../components/modal/Modal";
import NewsFormContainer from "../news/form/NewsFormContainer";
import ScheduleModalTraining from "./form/ScheduleModalTraining";

export const ScheduleContainer = () => {

    const [eventList, setEventList] = useState([]);
    const [modalActive, setModalActive] = useState(false);
    const [isCoach, setIsCoach] = useState(true);
    const [event, setEvent] = useState(null)

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
        setEvent(event)
    }


    const eventStatusStyle = (status) => {
        let styles = {
            style: {
                backgroundColor: "",
                borderColor: "",
                color: "black"
            }
        }
        switch (status) {
            case "LOGGED":
                styles.style.backgroundColor = "#27FB6B"
                styles.style.borderColor = "#27FB6B"
                break;
            case "ACTIVE":
                styles.style.backgroundColor = "#00E8FC"
                styles.style.borderColor = "#00E8FC"
                break;
            case "COMPLETED":
                styles.style.backgroundColor = "#e62222"
                styles.style.borderColor = "#e62222"
                break;
            case "ENDED":
                styles.style.backgroundColor = "#93ff8e"
                styles.style.borderColor = "#0bff00"
                break;
            default:
                console.error("Error, unknown training status");
        }
        return styles;
    }


    return (
        <div>
            { eventList && <Schedule myEvents={eventList}
                                   isCoach={isCoach}
                                   handleSelect={handleSelect}
                                   eventStatusStyle={eventStatusStyle}
                                   eventSelectHandle={eventSelectHandle}/> }
            <Modal active={modalActive} setActive={setModalActive} options={{closeBackground: true}}>
                <ScheduleModalTraining setActive={setModalActive} event={event} deleteTraining={deleteTraining}/>
            </Modal>
        </div>
    );

}

export default ScheduleContainer;