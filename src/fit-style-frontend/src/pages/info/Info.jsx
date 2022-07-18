import React from 'react';
import "./Info.css"
import {Article} from "./Article";

export const Info = () => {
    const titles = ["Reception", "Тренажерный зал", "Бассейн", "Зал бокса"]
    return (
        <div className="info">
            <div className="info-header">
                <div className="container-info">
                    <h1 className="center">Контактная информация</h1>
                    {titles.map((el, i) => <Article key={i} title={el} number={i+1} />)}
                </div>
            </div>
        </div>
    );
};