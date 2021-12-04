import React, { Component } from "react";

import Login from "./Login";

import isEmpty from "validator/es/lib/isEmpty";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import {TOP_CENTER, TOP_RIGHT} from "../../config/consts/ToastPosition";
import LoginApi from "../../services/api/LoginApi";

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

        LoginApi.login(this.state.userInfo).then(
            () => {
                ToastMessages.success("Добро пожаловать!", TOP_RIGHT);
                this.props.setIsAuth(true);
                this.props.history.push("/user");
            }).catch((error)=> {
               if (error?.response?.data?.message === "Bad credentials") {
                   ToastMessages.error("Неверные данные");
               } else {
                   ToastMessages.defaultError();
               }

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