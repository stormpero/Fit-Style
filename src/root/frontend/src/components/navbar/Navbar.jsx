import React, {Component} from 'react';
import {Link} from "react-router-dom";
import AuthService from "../../services/authService";
import EventBus from "../../services/EventBus";
import "./Navbar.css"


export default class Navbar extends Component {
    state = {
        currentUser: null,
        isAdmin: false
    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();

        if (user) {
            this.setState({
                currentUser: user,
                isAdmin: user.roles.includes("ROLE_MODERATOR"),
            });
        }

        EventBus.on("logout", () => {
            this.logOut();
        });
    }

    logOut() {
        AuthService.logout();
        this.setState({
            isAdmin: false,
            currentUser: undefined,
        });
    }


    componentWillUnmount() {
        EventBus.remove("logout");
    }

    render() {
        const { currentUser, isAdmin } = this.state;

        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                { currentUser && (
                    <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link to={"/profile"} className="nav-link">
                                Профиль
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link to={{
                                pathname: "/user",
                                state: {
                                    username: currentUser.username
                                }
                            }} className="nav-link">
                                Контент
                            </Link>
                        </li>
                    </div>
                )}
                { isAdmin && (
                    <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link to={"/register"} className="nav-link">
                                Регистрация клиента
                            </Link>
                        </li>
                    </div>
                )}
                { currentUser && (
                    <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <a href="/login" className="nav-link" onClick={this.logOut}>
                                Выйти
                            </a>
                        </li>
                    </div>
                )}
                </nav>
            </div>
        );
    }
}
