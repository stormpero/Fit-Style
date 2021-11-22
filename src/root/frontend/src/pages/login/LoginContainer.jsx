import React, { Component } from "react";

import Login from "./Login";

import AuthService from "../../services/api/AuthService";
import isEmpty from "validator/es/lib/isEmpty";
import ToastMessages from "../../services/utils/ToastMessages";
import {TOP_CENTER, TOP_RIGHT} from "../../services/utils/consts/ToastPosition";

export default class LoginContainer extends Component {

    state = {
        userInfo: {
            email: "",
            password: "",
        }
    }

    handleInputChange = (event) => {
        const {name, value} = event.target;

        this.setState({
            userInfo: {
                ...this.state.userInfo,
                [name]: value
            }
        });
    }

    handleLogin = (event) => {
        event.preventDefault();
        if (isEmpty(this.state.userInfo.email) ||
            isEmpty(this.state.userInfo.password)) {
            const errorMsg = "Заполните поля";
            ToastMessages.error(errorMsg, TOP_CENTER);
            return;
        }

        AuthService.login(this.state.userInfo).then(
            () => {
                ToastMessages.success("Добро пожаловать!", TOP_RIGHT);
                this.props.setIsAuth(true);
                this.props.history.push("/user");
            }).catch((error)=> {
                let errorMsg = error?.response?.data?.message === "Bad credentials" ? "Неверные данные" : "Заполните поля";
                ToastMessages.error(errorMsg);
        });
    }

    render() {
        return (
            <Login
                handleFunc={{
                    login: this.handleLogin,
                    input: this.handleInputChange,
                }}
                value={this.state.userInfo}
            />
        );
    }
}