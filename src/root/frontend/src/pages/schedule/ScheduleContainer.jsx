import React, {Component} from 'react';
import Schedule from "./Schedule";

class ScheduleContainer extends Component {

    state = {
        eventsList: [
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
        ],
        isCoach: true
    }

    handleSelect = (event) => {
        console.log(event)
    }

    render() {
        return (
            <Schedule myEvents={this.state.eventsList} isCoach={this.state.isCoach} handleSelect={this.handleSelect}/>
        );
    }
}

export default ScheduleContainer;