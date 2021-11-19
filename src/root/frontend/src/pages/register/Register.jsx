import React from 'react';

import "./Register.css"

import {AddressSuggestions} from "react-dadata";
import 'react-dadata/dist/react-dadata.css';
import {DADATA_TOKEN} from "../../services/utils/consts/Dadata";

const Register = (props) => {
    return (
        <div className="col-md-12">

            <div className="card card-container non-margin">
                <h3 className="text-muted">Регистрация</h3>
                <form>
                    <div className="form-group">
                        <label htmlFor="name">Имя</label>
                        <input className="form-control mb-2"
                               required
                               name="name"
                               type="text"
                               onChange={props.handleFunc.input}
                               value={props.value.name}
                               placeholder="Имя"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="surname">Фамилия</label>
                        <input className="form-control mb-2"
                               required
                               name="surname"
                               type="text"
                               onChange={props.handleFunc.input}
                               value={props.value.surname}
                               placeholder="Фамилия"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="patronymic">Отчество</label>
                        <input className="form-control mb-2"
                               required
                               name="patronymic"
                               type="text"
                               onChange={props.handleFunc.input}
                               value={props.value.patronymic}
                               placeholder="Отчество"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Пароль</label>
                        <input className="form-control"
                               required
                               name="password"
                               type="password"
                               onChange={props.handleFunc.input}
                               value={props.value.password}
                               autoComplete="on"
                               placeholder="Пароль"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input className="form-control"
                               required
                               name="email"
                               type="email"
                               onChange={props.handleFunc.input}
                               value={props.value.email}
                               placeholder="Email"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="age">Age</label>
                        <input className="form-control mb-2"
                               required
                               name="age"
                               type="number"
                               onChange={props.handleFunc.input}
                               value={props.value.age}
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
                                       onChange={props.handleFunc.input}
                                       value="M"
                                />
                                <label htmlFor="gender">Мужчина</label>
                            </div>
                            <div className="form-check">
                                <input className="g-gender form-check-input mb-2"
                                       required
                                       name="gender"
                                       type="radio"
                                       onChange={props.handleFunc.input}
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
                               onChange={props.handleFunc.input}
                               value={props.value.birthdate}
                               placeholder="Birthdate"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="telephone">Телефон</label>
                        <input className="form-control mb-2"
                               required
                               name="telephone"
                               type="tel"
                               onChange={props.handleFunc.input}
                               value={props.value.telephone}
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
                               onChange={props.handleFunc.input}
                               value={props.value.passport}
                               placeholder="Passport"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="address">Адрес</label>
                        <AddressSuggestions
                            token={DADATA_TOKEN}
                            value={props.value.address | ''}
                            onChange={props.handleFunc.inputAddress}
                            count={5}
                        />
                    </div>
                    <br/>
                    <div className="form-group d-flex justify-content-between">
                        <button className="btn btn-primary btn-block"
                                onClick={props.handleFunc.registering}>
                            <span>Register</span>
                        </button>
                    </div>
                    {props.message && (
                        <div className="form-group">
                            <p style={{color: 'red'}}>{props.message}</p>
                        </div>
                    )}
                </form>
            </div>
        </div>
    );
};

export default Register;