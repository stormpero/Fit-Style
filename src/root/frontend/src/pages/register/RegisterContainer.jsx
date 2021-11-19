import React, { Component } from "react";

import Register from "./Register";

import AuthService from "../../services/api/AuthService";
import isEmpty from "validator/es/lib/isEmpty";

export default class RegisterContainer extends Component {

    state = {
        userInfo: {
            email: "",
            password: "",
            name: "",
            surname: "",
            patronymic: "",
            age: "",
            gender: "",
            birthdate: "",
            telephone: "",
            passport: "",
            address: "",
        },
        successful: false,
        message: ""
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

    handleAddressInputChange = (event) => {
        const value = event.value || '';
        this.setState({
            userInfo: {
                ...this.state.userInfo,
                address: value
            }
        })
    }

    handleRegister = (event) => {
        event.preventDefault();
        if (isEmpty(this.state.userInfo.name) ||
            isEmpty(this.state.userInfo.password) ||
            isEmpty(this.state.userInfo.email) ||
            isEmpty(this.state.userInfo.surname) ||
            isEmpty(this.state.userInfo.patronymic) ||
            isEmpty(this.state.userInfo.age) ||
            isEmpty(this.state.userInfo.gender) ||
            isEmpty(this.state.userInfo.birthdate) ||
            isEmpty(this.state.userInfo.telephone) ||
            isEmpty(this.state.userInfo.passport) ||
            isEmpty(this.state.userInfo.address)){
            const errorMsg = "Заполните поля";

            this.setState({
                successful: false,
                message: errorMsg,
            });
            return;
        }

        //TODO: Проверить данные на ошибки + на пустоту
        AuthService.register(this.state.userInfo).then(
            (response) => {
                this.setState({
                    userInfo: {
                        email: "",
                        password: "",
                        name: "",
                        surname: "",
                        patronymic: "",
                        age: "",
                        gender: "",
                        birthdate: "",
                        telephone: "",
                        passport: "",
                        address: ""
                    },
                    message: response.data.message,
                    successful: true
                });
            }).catch((error)=> {
            let errorMsg =  error.response?.data?.message || error.message;

            this.setState({
                successful: false,
                message: errorMsg
            });
        });
    }

    render() {
        return (
        <Register
        handleFunc={{
            registering: this.handleRegister,
            input: this.handleInputChange,
            inputAddress: this.handleAddressInputChange,
        }}
        value={this.state.userInfo}
        message={this.state.message}
        />);
    }
}
