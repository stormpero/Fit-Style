import React, {useState} from 'react';

import {
    formatCreditCardNumber,
    formatCVC,
    formatExpirationDate
} from "./Utils";

import ProfileApi from "../../services/api/profile/ProfileApi";
import ProfileService from "../../services/profile/ProfileService";
import ToastMessages from "../toastmessages/ToastMessages";
import {TOP_RIGHT} from "../../config/consts/ToastPosition";
import {Payment} from "./Payment";
import Validation from "../../services/validation/Validation";

export const PaymentContainer = ({setReload, setActive}) => {
    const [cardNumber, setCardNumber] = useState("");
    const [cardName, setCardName] = useState("");
    const [cardExpiry, setCardExpiry] = useState("");
    const [cardCvc, setCardCvc] = useState("");
    const [issuer, setIssuer] = useState("");
    const [focused, setFocused] = useState("");
    const [balance, setBalance] = useState("");

    const handleCallback = ({ issuer }, isValid) => isValid && setIssuer(issuer)

    const handleInputFocus = ({ target }) => setFocused(target.name)

    const handleInputChange = ({ target }, callback) => {
        if (target.name === "number") {
            target.value = formatCreditCardNumber(target.value);
        } else if (target.name === "expiry") {
            target.value = formatExpirationDate(target.value);
        } else if (target.name === "cvc") {
            target.value = formatCVC(target.value);
        }
        callback(target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (!Validation.validateNumbers(balance)) {
            ToastMessages.error("Введите корректную сумму", TOP_RIGHT);
            return;
        } else if (balance === "0") {
            ToastMessages.error("Сумма должна быть больше 0", TOP_RIGHT);
            return;
        } else if (Number(balance) < 0) {
            ToastMessages.error("Сумма не может быть отрицательной", TOP_RIGHT);
            return;
        }

        ProfileApi.addBalance(balance).then(
            response => {
                setReload(prev => !prev);
                ToastMessages.success("Баланс пополнен на " + ProfileService.declinationRuble(balance), TOP_RIGHT);
            },
            error => {
                ToastMessages.defaultError();
            }).finally(() => {
                event.target.reset();
                setActive(false);
        })
    };

    return (
        <Payment
            func={{
                handleCallback: handleCallback,
                handleInputFocus: handleInputFocus,
                handleInputChange: handleInputChange,
                handleSubmit: handleSubmit,
            }}
            value={{
                cardNumber: cardNumber,
                cardName: cardName,
                cardExpiry: cardExpiry,
                cardCvc: cardCvc,
                issuer: issuer,
                focused: focused,
            }}
            setValue={{
                setCardNumber: setCardNumber,
                setCardName: setCardName,
                setCardExpiry: setCardExpiry,
                setCardCvc: setCardCvc,
                setBalance: setBalance,
            }}
        /> );
}