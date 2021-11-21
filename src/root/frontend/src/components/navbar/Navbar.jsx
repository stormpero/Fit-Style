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


const Navbar = (props) => {
    return (
        <div className="menu">
            <Link to={"/news"} className="menu-link-logo">
                <img className="svg-logo" src={logo} alt="Fit-Style"/>
                <span className="menu-link-logo-name">Fit-Style</span>
            </Link>
            <div className="menu-user">
                <div className="menu-user-container">
                    <Link to={ "/user"} className="menu-link">
                        <img className="svg-icon" src={search} alt="Fit-Style"/>
                        <span className="menu-link-name">Контент</span>
                    </Link>
                </div>
            </div>
            <div className="menu-user">
                <Link to={"/news"} className="menu-link">
                    <img className="svg-icon" src={news} alt="Fit-Style"/>
                    <span className="menu-link-name">Новости</span>
                </Link>
            </div>
            <div className="menu-user">
                <Link to={"/map"} className="menu-link">
                    <img className="svg-icon" src={map} alt="Fit-Style"/>
                    <span className="menu-link-name">Карта</span>
                </Link>
            </div>
            <div className="menu-user">
                <Link to={"/info"} className="menu-link">
                    <img className="svg-icon" src={info} alt="Fit-Style"/>
                    <span className="menu-link-name">О нас</span>
                </Link>
            </div>
            { props.isAdmin && (
                <div className="menu-user">
                    <div className="menu-user-container">
                        <Link to={"/register"} className="menu-link">
                            <img className="svg-icon" src={register} alt="Fit-Style"/>
                            <span className="menu-link-name">Регистрация</span>
                        </Link>
                    </div>
                </div>
            )}
            <div className="menu-user">
                <div className="menu-user-container">
                    <Link to={"/login"} className="menu-link-exit" onClick={props.logOut}>
                        <img className="svg-icon" src={exit} alt="Fit-Style"/>
                        <span className="menu-link-name">Выход</span>
                    </Link>
                </div>
            </div>
            <Link to={"/profile"} className="menu-link-profile">
                <img className="svg-icon" src={user} alt="Fit-Style"/>
                <span className="menu-link-name">Профиль</span>
            </Link>
        </div>
    );
};

export default Navbar;