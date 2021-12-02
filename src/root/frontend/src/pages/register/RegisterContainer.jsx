import React, { Component } from "react";

import Register from "./Register";

import Validation from "../../services/validation/Validation";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import {TOP_RIGHT} from "../../config/consts/ToastPosition";
import UserService from "../../services/api/user/UserApi";
import RegisterApi from "../../services/api/register/RegisterApi";

export default class RegisterContainer extends Component {

    state = {
        userInfo: {
            email: "",
            password: "",
            name: "",
            surname: "",
            patronymic: "",
            age: "",
            subscriptionTypeId: "",
            gender: "",
            birthdate: "",
            telephone: "",
            passport: "",
            address: "",
            contractNumber: "",
        },
        imageData: null,
        subscriptionTypes: [],
    }

    componentDidMount() {
        UserService.getSubscriptionType().then(
            response => {
                this.setState({
                    subscriptionTypes: response.data?.subscriptionTypes
                })
            },
            error => {
                ToastMessages.error(error.response);
            }
        );
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
        this.setState({
            imageData: files[0]
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
        console.log(this.state)

        let res = Validation.validateSingUp(this.state.userInfo);
        if (res.result) {
            const errorMsg = res.msg;
            ToastMessages.error(errorMsg, TOP_RIGHT)
            return;
        }

        const userData = this.state.userInfo;

        const blob = new Blob([JSON.stringify(userData)], {
            type: 'application/json',
        })

        const formData = new FormData();
        formData.append('request', blob);
        formData.append('image', this.state.imageData);

        RegisterApi.register(formData).then(
            (response) => {
                let msg = response.data.message === 'User registered successfully!' ? 'Пользователь успешно зарегистрирован' : "-_-";
                ToastMessages.success(msg, TOP_RIGHT)
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
                        address: "",
                        contractNumber: "",
                    }
                });
            }).catch((error)=> {
                let errorMsg = error.response.data.errorCode === 4 ? 'Ошибка. Пользователь с таким Email уже существует!' : 'Ошибка';
                ToastMessages.error(errorMsg, TOP_RIGHT)
            }
        );
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
        subscriptionTypes={this.state.subscriptionTypes}
        />);
    }
}
