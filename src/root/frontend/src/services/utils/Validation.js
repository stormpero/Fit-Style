import isEmpty from "validator/es/lib/isEmpty";
import {logDOM} from "@testing-library/react";

class Validation {
    validateNull(obj) {
        let array = Object.values(obj);
        for (let i of array) {
            if (i !== undefined && typeof i === "string" && isEmpty(i)) {
                return true;
            }
        }
        return false;
    }

    validateEmail(obj) {
        let reg = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
        return reg.test(String(obj).toLowerCase());
    }

    validatePhone(obj) {
        //Should work for different countries
        let regex = /^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$/;
        return regex.test(obj);
    }

    validateNumbers(obj) {
        let reg = /[0-9]/;
        return reg.test(String(obj).toLowerCase());
    }

    validateLetters(obj) {
        //Works for English and Russian lang
        let reg = /[A-Za-zA-Яа-яЁё]/;
        return reg.test(String(obj).toLowerCase());
    }
    //
    validateSumCount(str, count) {
        if (str.length > count)
            return false;
        return true;
    }

    validateSingUp(obj) {
        //true - ошибка
        // false - Хорошо
    // Проверка на пустоту
    // Проверка на корректность
        /// name surname patronymic
        if (this.validateNull(obj)) {
            return { msg: "Заполните поля", result: false }
        }

        for(let i in obj) {
            console.log(obj.i)
            switch (i) {
                case "email":
                    if (this.validateEmail(i)) {

                    }
                break;
                case "name": {
                    if (this.validateSumCount(obj.i, 20)) {

                    }
                }
            }
        }
    }

}

export default new Validation()