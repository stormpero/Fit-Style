import React from 'react';
import Card from "react-credit-cards";

import 'react-credit-cards/es/styles-compiled.css';
import "./PaymentForm.css"

export const Payment = ({func, value, setValue}) => {
    return (
        <div key="Payment">
            <div className="App-payment">
                <Card
                    number={value.cardNumber}
                    name={value.cardName}
                    expiry={value.cardExpiry}
                    cvc={value.cardCvc}
                    focused={value.focused}
                    callback={func.handleCallback}
                />
                <form onSubmit={func.handleSubmit}>
                    <div className="form-group">
                        <input
                            type="tel"
                            name="number"
                            className="form-control"
                            placeholder="Номер карты"
                            pattern="[\d| ]{16,22}"
                            required
                            onChange={(e) => func.handleInputChange(e, setValue.setCardNumber)}
                            onFocus={func.handleInputFocus}
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
                            onChange={(e) => func.handleInputChange(e, setValue.setCardName)}
                            onFocus={func.handleInputFocus}
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
                                onChange={(e) => func.handleInputChange(e, setValue.setCardExpiry)}
                                onFocus={func.handleInputFocus}
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
                                onChange={(e) => func.handleInputChange(e, setValue.setCardCvc)}
                                onFocus={func.handleInputFocus}
                            />
                        </div>
                    </div>
                    <input type="hidden" name="issuer" value={value.issuer} />
                    <div className="form-group mt-4">
                        <input className="form-control mb-2"
                               required
                               name="balance"
                               type="text"
                               placeholder="Сумма"
                               onChange={(e) => func.handleInputChange(e, setValue.setBalance)}
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
