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
        let reg = /^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$/;
        return reg.test(obj);
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

    validateSumCount(str, count) {
        if (str.length > count)
            return false;
        return true;
    }

    validateSingUp(obj) {
        //true - error
        // false - good

        if (this.validateNull(obj)) {
            return { msg: "Заполните поля", result: true }
        }

        for(let i in obj) {

            switch (i) {

                case "email":{
                    if (!this.validateEmail(obj.i))
                        return {msg: "Некорректный email", result: true}
                    if (!this.validateSumCount(obj.i, 50))
                        return {msg: "Превышено колличество символов email", result: true}
                    break;
                }

                case "name": {
                    if (!this.validateLetters(obj.i))
                        return {msg: "Имя может состоять только из букв латинского и русского алфавитов", result: true}
                    if (this.validateSumCount(obj.i, 20))
                        return{msg: "Превышено колличество символов name", result: true}
                    break;
                }

                case "surname":{
                    if (this.validateLetters(obj.i))
                        return {msg: "Фамилия может состоять только из букв латинского и русского алфавитов", result: true}
                    if (this.validateSumCount(obj.i, 20))
                        return{msg: "Превышено колличество символов surname", result: true}
                    break;
                }

                case "patronymic":{
                    if (this.validateLetters(obj.i))
                        return {msg: "Фамилия может состоять только из букв латинского и русского алфавитов", result: true}
                    if (this.validateSumCount(obj.i, 20))
                        return{msg: "Превышено колличество символов surname", result: true}
                    break;
                }

                case "telephone":{
                    if (this.validateLetters(obj.i))
                        return {msg: "Фамилия может состоять только из букв латинского и русского алфавитов", result: true}
                    if (this.validateSumCount(obj.i, 20))
                        return{msg: "Превышено колличество символов surname", result: true}
                    break;
                }

                /*case "passport":{
                    if (this.validateLetters(obj.i))
                        return {msg: "Фамилия может состоять только из букв латинского и русского алфавитов", result: true}
                    if (this.validateSumCount(obj.i, 20))
                        return{msg: "Превышено колличество символов surname", result: true}
                    break;
                }

                case "address":{
                    if (this.validateLetters(obj.i))
                        return {msg: "Фамилия может состоять только из букв латинского и русского алфавитов", result: true}
                    if (this.validateSumCount(obj.i, 20))
                        return{msg: "Превышено колличество символов surname", result: true}
                    break;
                }*/

            }
        }

        return {msg: "", result: false}
    }


}

export default new Validation()