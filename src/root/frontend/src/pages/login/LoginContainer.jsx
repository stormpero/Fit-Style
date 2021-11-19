import React, { Component } from "react";

import Login from "./Login";

import AuthService from "../../services/api/AuthService";
import isEmpty from "validator/es/lib/isEmpty";


export default class LoginContainer extends Component {

    state = {
        userInfo: {
            email: "",
            password: "",
        },
        errorMsg: ""
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

            this.setState({
                errorMsg: errorMsg
            });
            return;
        }

        //TODO: Проверить данные на ошибки
        AuthService.login(this.state.userInfo).then(
            () => {
                this.props.setIsAuth(true);
                this.props.history.push("/user");
            }).catch((error)=> {
            let errorMsg = error?.response?.data?.message === "Bad credentials" ? "Неверные данные" : "Заполните поля";

            this.setState({
                errorMsg: errorMsg
            });
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
                error={this.state.errorMsg}
            />
        );
    }
}