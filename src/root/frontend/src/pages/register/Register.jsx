import React, { Component } from "react";

import { AddressSuggestions } from 'react-dadata';
import 'react-dadata/dist/react-dadata.css';

import AuthService from "../../services/AuthService";
import isEmpty from "validator/es/lib/isEmpty";

import "./Register.css"
import {DADATA_TOKEN} from "../../services/utils/Dadata";

export default class Register extends Component {

    state = {
        username: "",
        email: "",
        password: "",
        surname: "",
        patronymic: "",
        age: "",
        gender: "",
        birthdate: "",
        telephone: "",
        passport: "",
        address: "",
        successful: false,
        message: ""
    }

    handleInputChange = (event) => {
        const {name, value} = event.target;
        this.setState({
            [name]: value
        });
    }
    handleAddrInputChange = (event) => {
        const value = event.value || '';
        this.setState({
            address: value
        })
    }

    handleRegister = (event) => {
        event.preventDefault();
        if (isEmpty(this.state.username) ||
            isEmpty(this.state.password) ||
            isEmpty(this.state.email) ||
            isEmpty(this.state.surname) ||
            isEmpty(this.state.patronymic) ||
            isEmpty(this.state.age) ||
            isEmpty(this.state.gender) ||
            isEmpty(this.state.birthdate) ||
            isEmpty(this.state.telephone) ||
            isEmpty(this.state.passport) ||
            isEmpty(this.state.address)){
            const errorMsg = "Заполните поля";


            this.setState({
                message: errorMsg,
                successful: false
            });
            return;
        }

        //TODO: Проверить дqанные на ошибки
        AuthService.register(this.state.username, this.state.email, this.state.password, this.state.surname, this.state.patronymic, this.state.age, this.state.gender, this.state.birthdate, this.state.telephone, this.state.passport, this.state.address).then(
            (response) => {
                this.setState({
                    username: "",
                    email: "",
                    password: "",
                    surname: "",
                    patronymic: "",
                    age: "",
                    gender: "",
                    birthdate: "",
                    telephone: "",
                    passport: "",
                    address: "",
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
                            <label htmlFor="username">Username</label>
                            <input className="form-control mb-2"
                                   required
                                   name="username"
                                   type="text"
                                   onChange={this.handleInputChange}
                                   value={this.state.username}
                                   placeholder="Username"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <input className="form-control"
                                   required
                                   name="password"
                                   type="password"
                                   onChange={this.handleInputChange}
                                   value={this.state.password}
                                   placeholder="Password"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <input className="form-control"
                                   required
                                   name="email"
                                   type="email"
                                   onChange={this.handleInputChange}
                                   value={this.state.email}
                                   placeholder="Email"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="surname">Surname</label>
                            <input className="form-control mb-2"
                                   required
                                   name="surname"
                                   type="text"
                                   onChange={this.handleInputChange}
                                   value={this.state.surname}
                                   placeholder="Surname"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="patronymic">Patronymic</label>
                            <input className="form-control mb-2"
                                   required
                                   name="patronymic"
                                   type="text"
                                   onChange={this.handleInputChange}
                                   value={this.state.patronymic}
                                   placeholder="Patronymic"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="age">Age</label>
                            <input className="form-control mb-2"
                                   required
                                   name="age"
                                   type="number"
                                   onChange={this.handleInputChange}
                                   value={this.state.age}
                                   placeholder="Age"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="gender">Gender</label>
                            <div>
                            <div className="form-check">
                                <input className="form-check-input mb-2"
                                       required
                                       name="gender"
                                       type="radio"
                                       onChange={this.handleInputChange}
                                       value="M"
                                />
                                <label htmlFor="gender">Male</label>

                            </div>
                            <div className="form-check">
                                <input className="g-gender form-check-input mb-2"
                                       required
                                       name="gender"
                                       type="radio"
                                       onChange={this.handleInputChange}
                                       value="F"
                                />
                            <label htmlFor="gender" className="g-gender">Female</label>

                            </div>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="birthdate">Birthdate</label>
                            <input className="form-control mb-2"
                                   required
                                   name="birthdate"
                                   type="date"
                                   onChange={this.handleInputChange}
                                   value={this.state.birthdate}
                                   placeholder="Birthdate"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="telephone">Telephone</label>
                            <input className="form-control mb-2"
                                   required
                                   name="telephone"
                                   type="tel"
                                   onChange={this.handleInputChange}
                                   value={this.state.telephone}
                                   placeholder="Telephone"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="passport">Passport</label>
                            <input className="form-control mb-2"
                                   required
                                   name="passport"
                                   type="text"
                                   onChange={this.handleInputChange}
                                   value={this.state.passport}
                                   placeholder="Passport"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="address">Address</label>

                            <AddressSuggestions token={DADATA_TOKEN} value={this.state.address | ''} onChange={this.handleAddrInputChange} count={5}/>
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
