import React from 'react';

import "./Map.css"
import dumbbell from "../../assets/dumbbell.png";
import pool from "../../assets/swimming.png";
import group from "../../assets/group.png";
import boxing from "../../assets/boxing.png";
import reception from "../../assets/reception.png";

export const Map = () => {
    return (
        <div className="map">
            <h1 className="map-title">Карта зала</h1>
            <div className="map-picture-container">
                <center>
                <div className="grid-wrapper">
                    <div className="reception right bottom">
                        <img className="map-block-logo" src={reception} alt="Fit-Style"/>
                        <div className="map-content">
                            <h4>Reception</h4>
                            <span>Время работы</span>
                            <br/>
                            <span>Пн-Вс: 6:00-23:00</span>
                        </div>
                    </div>
                    <div className="right bottom left">
                        <img className="map-block-log" src={dumbbell} alt="Fit-Style"/>
                        <div className="map-content">
                            <h4>Тренажерный зал</h4>
                            <span>Время работы</span>
                            <br/>
                            <span>Пн-Вс: 10:00-22:00</span>
                        </div>
                    </div>
                    <div className="tall left bottom">
                        <img className="map-block-logo" src={group} alt="Fit-Style"/>
                        <div className="map-content">
                            <h4>Зал групповых занятий</h4>
                            <span>Время работы</span>
                            <br/>
                            <span>Пн-Вс: 8:00-23:00</span>
                        </div>
                    </div>
                    <div className="wide top right bottom">
                        <img className="map-block-logo" src={boxing} alt="Fit-Style"/>
                        <div className="map-content">
                            <h1>Зал бокса</h1>
                            <span>Время работы</span>
                            <br/>
                            <span>Пн, Ср, Пт: 10:00-21:00</span>
                        </div>
                    </div>
                    <div className="top right bottom">
                        <img className="map-block-logo" src={dumbbell} alt="Fit-Style"/>
                        <div className="map-content">
                            <h4>Тренажерный зал</h4>
                            <span>Время работы</span>
                            <br/>
                            <span>Пн-Вс: 10:00-22:00</span>
                        </div>
                    </div>
                    <div className="big top left">
                        <img className="map-block-logo" src={pool} alt="Fit-Style"/>
                        <div className="map-content">
                            <h1>Бассейн</h1>
                            <span>Время работы</span>
                            <br/>
                            <span>Пн-Сб: 10:00-20:00</span>
                        </div>
                    </div>
                    <div className="top right">
                        <img className="map-block-logo" src={dumbbell} alt="Fit-Style"/>
                        <div className="map-content">
                            <h4>Тренажерный зал</h4>
                            <span>Время работы</span>
                            <br/>
                            <span>Пн-Вс: 10:00-22:00</span>
                        </div>
                    </div>
                </div>
                </center>
            </div>
            <br/>
            <br/>
        </div>
    );
};

