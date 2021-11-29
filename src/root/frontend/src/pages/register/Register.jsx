import React from 'react';

import "./Register.css"

import {AddressSuggestions} from "react-dadata";
import 'react-dadata/dist/react-dadata.css';
import {DADATA_TOKEN} from "../../config/dadata/Dadata";

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
                        <label htmlFor="age">Возраст</label>
                        <input className="form-control mb-2"
                               required
                               name="age"
                               type="number"
                               onChange={props.handleFunc.input}
                               value={props.value.age}
                               placeholder="Возраст"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="age">Вид абонемента</label>
                        <select className="form-control" name="subscriptionTypeId"
                                onChange={props.handleFunc.input} value={props.value.subscriptionTypeId}

                                defaultValue={"DEFAULT"}>
                            <option value="DEFAULT">Выберите абонемент</option>
                            {props.subscriptionTypes.map((param, index) => <option value={param.id} key={index}>{param.name}</option>)}
                        </select>
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
                               placeholder="Телефон"
                        />
                    </div>
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
                        <label htmlFor="contractNumber">Номер договора</label>
                        <input className="form-control mb-2"
                               required
                               name="contractNumber"
                               type="text"
                               onChange={props.handleFunc.input}
                               value={props.value.contractNumber}
                               placeholder="Номер договора"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="address">Адрес</label>
                        <AddressSuggestions
                            token={DADATA_TOKEN}
                            value={props.value.address | ''}
                            onChange={props.handleFunc.inputAddress}
                            count={5}
                            inputProps={{placeholder: "Адрес"}}

                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="img">Фотография</label>
                        <input className="form-control mb-2"
                               required
                               name="img"
                               type="file"
                               onChange={props.handleFunc.inputImg}
                        />
                    </div>
                    <br/>
                    <div className="form-group d-flex justify-content-between">
                        <button className="btn btn-primary btn-block"
                                onClick={props.handleFunc.registering}>
                            <span>Зарегистрировать</span>
                        </button>
                    </div>
                </form>
            </div>
            <br/>
        </div>
    );
};

export default Register;