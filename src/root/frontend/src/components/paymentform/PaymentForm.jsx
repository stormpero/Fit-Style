import React, {Component} from 'react';
import Card from 'react-credit-cards';
import 'react-credit-cards/es/styles-compiled.css';
import {
    formatCreditCardNumber,
    formatCVC,
    formatExpirationDate
} from "./Utils";
import "./PaymentForm.css"
import ProfileApi from "../../services/api/profile/ProfileApi";
import ToastMessages from "../toastmessages/ToastMessages";
import {TOP_RIGHT} from "../../config/consts/ToastPosition";
import ProfileService from "../../services/profile/ProfileService";

export default class PaymentForm extends Component {
    state = {
        number: "",
        name: "",
        expiry: "",
        cvc: "",
        issuer: "",
        focused: "",
        balance: "",
    };

    handleCallback = ({ issuer }, isValid) => {
        if (isValid) {
            this.setState({ issuer });
        }
    };

    handleInputFocus = ({ target }) => {
        this.setState({
            focused: target.name
        });
    };

    handleInputChange = ({ target }) => {
        if (target.name === "number") {
            target.value = formatCreditCardNumber(target.value);
        } else if (target.name === "expiry") {
            target.value = formatExpirationDate(target.value);
        } else if (target.name === "cvc") {
            target.value = formatCVC(target.value);
        }

        this.setState({ [target.name]: target.value });
    };

    handleSubmit = e => {
        e.preventDefault();
        ProfileApi.addBalance(this.state.balance).then(
            response => {
                this.props.setReload(prev => !prev);
                ToastMessages.success("Баланс пополнен на " + ProfileService.declinationRuble(this.state.balance), TOP_RIGHT);
            },
            error => {
                ToastMessages.defaultError();
            }).finally(() => {
            this.form.reset();
            this.props.setActive(false);
        })
    };

    render() {
        const { name, number, expiry, cvc, focused, issuer } = this.state;

        return (
            <div key="Payment">
                <div className="App-payment">
                    <Card
                        number={number}
                        name={name}
                        expiry={expiry}
                        cvc={cvc}
                        focused={focused}
                        callback={this.handleCallback}
                    />
                    <form ref={c => (this.form = c)} onSubmit={this.handleSubmit}>
                        <div className="form-group">
                            <input
                                type="tel"
                                name="number"
                                className="form-control"
                                placeholder="Номер карты"
                                pattern="[\d| ]{16,22}"
                                required
                                onChange={this.handleInputChange}
                                onFocus={this.handleInputFocus}
                            />
                            <small><p className="font-weight-light">Например: 49..., 51..., 36..., 37...</p></small>
                        </div>
                        <div className="form-group">
                            <input
                                type="text"
                                name="name"
                                className="form-control"
                                placeholder="Имя"
                                required
                                onChange={this.handleInputChange}
                                onFocus={this.handleInputFocus}
                            />
                        </div>
                        <div className="row">
                            <div className="col-6">
                                <input
                                    type="tel"
                                    name="expiry"
                                    className="form-control"
                                    placeholder="Месяц/Год"
                                    pattern="\d\d/\d\d"
                                    required
                                    onChange={this.handleInputChange}
                                    onFocus={this.handleInputFocus}
                                />
                            </div>
                            <div className="col-6">
                                <input
                                    type="tel"
                                    name="cvc"
                                    className="form-control"
                                    placeholder="CVC"
                                    pattern="\d{3,4}"
                                    required
                                    onChange={this.handleInputChange}
                                    onFocus={this.handleInputFocus}
                                />
                            </div>
                        </div>
                        <input type="hidden" name="issuer" value={issuer} />
                        <div className="form-group mt-4">
                            <input className="form-control mb-2"
                                   required
                                   name="balance"
                                   type="text"
                                   placeholder="Сумма"
                                   onChange={this.handleInputChange}
                                   value={this.state.balance}
                            />
                        </div>
                        <div className="form-actions">
                            <button className="btn btn-primary btn-lg btn-block w-100">Пополнить</button>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}