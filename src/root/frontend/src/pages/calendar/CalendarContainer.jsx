import React, {Component} from 'react';
import CalendarView from "./CalendarView";
import UserService from "../../services/api/user/UserService";
import ToastMessages from "../../components/toastmessages/ToastMessages";

class CalendarContainer extends Component {

    state = {
        trainingsList: [],
    }

    componentDidMount() {
        UserService.getTrainings().then(
            response => {
                let trainingsListTemp = [];
                this.addTraining(trainingsListTemp, response.data?.personalTrainings, true);
                this.addTraining(trainingsListTemp, response.data?.groupTrainings, false);

                this.setState({
                    trainingsList: trainingsListTemp,
                })
            },
            error => {
                console.error(error);
                ToastMessages.error("Произошла ошибка, повторите ошибку позже");
            }
        )
    }

    addTraining(trainingsListTemp, array, isPersonal) {
        array.forEach(element => {
            let date = new Date(element.date);
            date.setHours(date.getHours() + 1);
            trainingsListTemp.push({
                id: element.id +" ",
                title: "Тренер: " + element?.coachId,
                start: new Date(element.date),
                end: date,
                isPersonal: isPersonal,
            })
        })
    }



    render() {
        return (
            <CalendarView events={this.state.trainingsList} />
        );
    }
}

export default CalendarContainer;