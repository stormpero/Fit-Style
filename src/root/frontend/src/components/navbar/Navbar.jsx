import React, {Component} from 'react';
import {Link} from "react-router-dom";
import LStorageUser from "../../services/LStorageUser";
import AuthService from "../../services/AuthService";
import "./Navbar.css"

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
        this.setState({
            isAdmin: false,
            currentUser: undefined,
        });
    }

    render() {
        const { currentUser, isAdmin } = this.state;

        return (
            <div>
                <nav className="navbarcastom navbar navbar-expand-lg navbar-dark bg-dark ">
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
