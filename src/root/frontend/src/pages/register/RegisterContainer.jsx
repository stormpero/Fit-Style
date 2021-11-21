import React, { Component } from "react";

import Register from "./Register";

import AuthService from "../../services/api/AuthService";
import isEmpty from "validator/es/lib/isEmpty";
import Validation from "../../services/utils/Validation";

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
            imageData: null
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

    handleImgInputChange = (event) => {
        const {files} = event.target;
        let formData = new FormData();
        formData.append('file', files[0]);
        this.setState({
            userInfo: {
                ...this.state.userInfo,
                imageData: files[0]
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

        let res = Validation.validateSingUp(this.state.userInfo);
        if (res.result) {
            const errorMsg = res.msg;

            this.setState({
                message: errorMsg,
            });
        } else {
            this.setState({
                message: "Всё Хорошо!",
            });
        }
        return;

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
            inputImg: this.handleImgInputChange,
        }}
        value={this.state.userInfo}
        message={this.state.message}
        />);
    }
}
