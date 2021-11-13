import React, { Component } from "react";

import { AddressSuggestions } from 'react-dadata';
import 'react-dadata/dist/react-dadata.css';

import AuthService from "../../services/AuthService";
import isEmpty from "validator/es/lib/isEmpty";

import "./Register.css"
import {DADATA_TOKEN} from "../../services/utils/Dadata";

export default class Register extends Component {

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

    handleAddrInputChange = (event) => {
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
                message: errorMsg,
                successful: false
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
            <div className="col-md-12">
                <div className="card card-container">
                    <h3 className="text-muted">Auth</h3>
                    <form>
                        <div className="form-group">
                            <label htmlFor="name">Имя</label>
                            <input className="form-control mb-2"
                                   required
                                   name="name"
                                   type="text"
                                   onChange={this.handleInputChange}
                                   value={this.state.userInfo.name}
                                   placeholder="Имя"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="surname">Фамилия</label>
                            <input className="form-control mb-2"
                                   required
                                   name="surname"
                                   type="text"
                                   onChange={this.handleInputChange}
                                   value={this.state.userInfo.surname}
                                   placeholder="Фамилия"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="patronymic">Отчество</label>
                            <input className="form-control mb-2"
                                   required
                                   name="patronymic"
                                   type="text"
                                   onChange={this.handleInputChange}
                                   value={this.state.userInfo.patronymic}
                                   placeholder="Отчество"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Пароль</label>
                            <input className="form-control"
                                   required
                                   name="password"
                                   type="password"
                                   onChange={this.handleInputChange}
                                   value={this.state.userInfo.password}
                                   placeholder="Пароль"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <input className="form-control"
                                   required
                                   name="email"
                                   type="email"
                                   onChange={this.handleInputChange}
                                   value={this.state.userInfo.email}
                                   placeholder="Email"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="age">Age</label>
                            <input className="form-control mb-2"
                                   required
                                   name="age"
                                   type="number"
                                   onChange={this.handleInputChange}
                                   value={this.state.userInfo.age}
                                   placeholder="Age"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="gender">Пол</label>
                            <div>
                            <div className="form-check">
                                <input className="form-check-input mb-2"
                                       required
                                       name="gender"
                                       type="radio"
                                       onChange={this.handleInputChange}
                                       value="M"
                                />
                                <label htmlFor="gender">Мужчина</label>

                            </div>
                            <div className="form-check">
                                <input className="g-gender form-check-input mb-2"
                                       required
                                       name="gender"
                                       type="radio"
                                       onChange={this.handleInputChange}
                                       value="F"
                                />
                            <label htmlFor="gender" className="g-gender">Женщина</label>

                            </div>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="birthdate">Дата рождения</label>
                            <input className="form-control mb-2"
                                   required
                                   name="birthdate"
                                   type="date"
                                   onChange={this.handleInputChange}
                                   value={this.state.userInfo.birthdate}
                                   placeholder="Birthdate"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="telephone">Телефон</label>
                            <input className="form-control mb-2"
                                   required
                                   name="telephone"
                                   type="tel"
                                   onChange={this.handleInputChange}
                                   value={this.state.userInfo.telephone}
                                   placeholder="Telephone"
                            />
                        </div>
                        {/*TODO: Добавить поля для паспортных данных */}
                        <div className="form-group">
                            <label htmlFor="passport">Паспортные данные</label>
                            <input className="form-control mb-2"
                                   required
                                   name="passport"
                                   type="text"
                                   onChange={this.handleInputChange}
                                   value={this.state.userInfo.passport}
                                   placeholder="Passport"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="address">Адрес</label>

                            <AddressSuggestions placeholder="Адрес" token={DADATA_TOKEN} value={this.state.userInfo.address | ''} onChange={this.handleAddrInputChange} count={5}/>
                        </div>
                        <br/>
                        <div className="form-group d-flex justify-content-between">
                            <button className="btn btn-primary btn-block"
                                    onClick={this.handleRegister}>
                                <span>Register</span>
                            </button>
                        </div>
                        {this.state.message && (
                            <div className="form-group">
                                <p style={{color: 'red'}}>{this.state.message}</p>
                            </div>
                        )
                        }
                    </form>
                </div>
            </div>
        );
    }
}
