import React from 'react';
import {Link} from "react-router-dom";

import "./Navbar.css"

import logo from "../../assets/logo3.png";
import search from "../../assets/search.png";
import news from "../../assets/newspaper.png";
import register from "../../assets/voting.png";
import exit from "../../assets/exit.png";
import user from "../../assets/user.png";
import map from "../../assets/map.png";
import info from "../../assets/info.png";
import calendar from "../../assets/calendar.png"
import workout from "../../assets/workout.png"
import {
    URL_CALENDAR,
    URL_INFO,
    URL_LOGIN,
    URL_MAP,
    URL_NEWS,
    URL_USER,
    URL_PROFILE,
    URL_REGISTER,
    URL_SCHEDULE
} from "../../config/consts/urlsPages";


const Navbar = ({isModer, logOut}) => {
    return (
        <div className="menu">
            <Link to={"/news"} className="menu-link-logo">
                <img className="svg-logo" src={logo} alt="Fit-Style"/>
                <span className="menu-link-logo-name">Fit-Style</span>
            </Link>

            <div className="menu-user">
                <Link to={URL_NEWS} className="menu-link">
                    <img className="svg-icon" src={news} alt="Fit-Style"/>
                    <span className="menu-link-name">Новости</span>
                </Link>
            </div>
            <div className="menu-user">
                <Link to={URL_CALENDAR} className="menu-link">
                    <img className="svg-icon" src={calendar} alt="Fit-Style"/>
                    <span className="menu-link-name">Календарь</span>
                </Link>
            </div>
            <div className="menu-user">
                <Link to={URL_SCHEDULE} className="menu-link">
                    <img className="svg-icon" src={workout} alt="Fit-Style"/>
                    <span className="menu-link-name">Расписание тренировок</span>
                </Link>
            </div>
            <div className="menu-user">
                <Link to={URL_MAP} className="menu-link">
                    <img className="svg-icon" src={map} alt="Fit-Style"/>
                    <span className="menu-link-name">Карта</span>
                </Link>
            </div>
            <div className="menu-user">
                <Link to={URL_INFO} className="menu-link">
                    <img className="svg-icon" src={info} alt="Fit-Style"/>
                    <span className="menu-link-name">Контакты</span>
                </Link>
            </div>
            { isModer && (
                <div className="menu-user-container">
                    <div className="menu-user">
                        <Link to={URL_REGISTER} className="menu-link">
                            <img className="svg-icon" src={register} alt="Fit-Style"/>
                            <span className="menu-link-name">Регистрация</span>
                        </Link>
                    </div>
                    <div className="menu-user">
                        <Link to={URL_USER} className="menu-link">
                            <img className="svg-icon" src={search} alt="Fit-Style"/>
                            <span className="menu-link-name">Пользователи</span>
                        </Link>
                    </div>
                </div>
            )}
            <div className="menu-user">
                <div className="menu-user-container">
                    <Link to={URL_LOGIN} className="menu-link-exit" onClick={logOut}>
                        <img className="svg-icon" src={exit} alt="Fit-Style"/>
                        <span className="menu-link-name">Выход</span>
                    </Link>
                </div>
            </div>
            <Link to={URL_PROFILE} className="menu-link-profile">
                <img className="svg-icon" src={user} alt="Fit-Style"/>
                <span className="menu-link-name">Профиль</span>
            </Link>
        </div>
    );
};

export default Navbar;