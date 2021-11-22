import React, {Component} from 'react';
import CalendarView from "./CalendarView";

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

    }

    render() {
        return (
            <CalendarView events={myEvents}/>
        );
    }
}

export default CalendarContainer;