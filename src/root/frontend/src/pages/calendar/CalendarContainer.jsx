import React, {Component} from 'react';
import CalendarView from "./CalendarView";
import UserService from "../../services/api/UserService";

const myEvents = [
    {
        id: 0,
        title: 'Тренировка 1',
        start: new Date(2021, 10, 23, 12 - 1, 0, 0),
        end: new Date(2021, 10, 23, 14, 0, 0),
    },
    {
        id: 1,
        title: 'Тренировка 2 ',
        start: new Date(2021, 10, 21, 12, 0, 0),
        end: new Date(2021, 10, 21, 13, 0, 0),
    },
    {
        id: 2,
        title: 'Групповая тренировка',
        start: new Date(2021, 10, 13, 12, 0, 0),
        end: new Date(2021, 10, 13, 13, 0, 0),
    },
]

class CalendarContainer extends Component {

    state = {
        trainingsList: [],
    }

    componentDidMount() {
        UserService.getTrainings().then(
            response => {
                console.log(response.data)
                let trainingsListTemp = [];
                response.data?.personalTrainings.forEach((element) => {
                    let date = new Date(element.date);
                    date.setHours(date.getHours() + 1);
                    trainingsListTemp.push({
                        id: element.id +" ",
                        title: "Тренер: " + element?.coachId,
                        start: new Date(element.date),
                        end: date,
                    })
                })

                response.data?.groupTrainings.forEach((element, index) => {
                    let date = new Date(element.date);
                    date.setHours(date.getHours() + 1);
                    trainingsListTemp.push({
                        id: element.id +" ",
                        title: "Тренер: " + element?.coachId,
                        start: new Date(element.date),
                        end: date,
                    })
                })

                this.setState({
                    trainingsList: trainingsListTemp,
                })
            },
            error => {

            }
        )
    }

    render() {
        return (
            <CalendarView events={this.state.trainingsList}/>
        );
    }
}

export default CalendarContainer;