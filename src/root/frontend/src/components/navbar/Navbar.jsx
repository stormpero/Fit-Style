import React, {Component} from 'react';
import {Link} from "react-router-dom";
import LStorageUser from "../../services/LStorageUser";
import AuthService from "../../services/AuthService";
import "./Navbar.css"
import logo from "../../assets/logo3.png";
import search from "../../assets/search.png";
import news from "../../assets/newspaper.png";
import register from "../../assets/voting.png";
import exit from "../../assets/exit.png";
import user from "../../assets/user.png";

export default class Navbar extends Component {
    state = {
        currentUser: null,
        isAdmin: false
    }

    componentDidMount() {
        const user = LStorageUser.getUser();

        if (user) {
            this.setState({
                currentUser: user,
                isAdmin: user?.roles.includes("ROLE_MODERATOR"),
            });
        }
    }

    logOut() {
        AuthService.logout();
        console.log('Выполнился')
    }

    render() {
        const { currentUser, isAdmin } = this.state;

        return (
            <div className="menu">
                    { currentUser && (
                            <a href="#" className="menu-link-logo">
                                <img className="svg-logo" src={logo} alt="Fit-Style"/>
                                <span className="menu-link-logo-name">Fit-Style</span>
                            </a>
                    )}

                    { currentUser && (
                        <div className="menu-user">
                            <div className="menu-user-container">
                            <Link to={{
                                pathname: "/user",
                                state: {
                                    username: currentUser.username
                                }
                            }} className="menu-link">
                                <img className="svg-icon" src={search} alt="Fit-Style"/>
                                <span className="menu-link-name">Контент</span>
                            </Link>
                            </div>
                        </div>
                    )}
                    { currentUser && (
                        <div className="menu-user">
                                <Link to={"/news"} className="menu-link">
                                    <img className="svg-icon" src={news} alt="Fit-Style"/>
                                    <span className="menu-link-name">Новости</span>
                                </Link>
                        </div>
                    )}
                    { isAdmin && (
                        <div className="menu-user">
                            <div className="menu-user-container">
                                <Link to={"/register"} className="menu-link">
                                    <img className="svg-icon" src={register} alt="Fit-Style"/>
                                    <span className="menu-link-name">Регистрация</span>
                                </Link>
                            </div>
                        </div>
                    )}
                { currentUser && (
                    <div className="menu-user">
                        <div className="menu-user-container">
                            <a href="/login" className="menu-link-exit" onClick={this.logOut}>
                                <img className="svg-icon" src={exit} alt="Fit-Style"/>
                                <span className="menu-link-name">Выход</span>
                            </a>
                        </div>
                    </div>
                )}

                { currentUser && (
                    <Link to={"/profile"} className="menu-link-profile">
                        <img className="svg-icon" src={user} alt="Fit-Style"/>
                        <span className="menu-link-name">Профиль</span>
                    </Link>

                )}
            </div>
        );
    }
}
