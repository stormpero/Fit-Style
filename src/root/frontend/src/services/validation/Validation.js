import isEmpty from "validator/es/lib/isEmpty";

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
        let reg = /\S+@\S+\.\S+/;
        return reg.test(String(obj).toLowerCase());
    }

    validatePhone(obj) {
        let reg = /^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$/;
        return reg.test(obj);
    }

    validateNumbers(obj) {
        let reg = /[0-9]/;
        return reg.test(String(obj).toLowerCase());
    }

    validateLetters(obj) {
        let reg = /[A-Za-zA-Яа-яЁё]/;
        return reg.test(String(obj).toLowerCase());
    }

    validateSumCount(str, count) {
        return str.length <= count;
    }

    validateSingUp(obj) {
        if (this.validateNull(obj)) {
            return { msg: "Заполните поля", result: true }
        }

        for(let i in obj) {
            switch (i) {
                case "email":{
                    if (!this.validateEmail(obj[i]))
                        return {msg: "Некорректный email", result: true}
                    if (!this.validateSumCount(obj[i], 50))
                        return {msg: "Превышено колличество символов email", result: true}
                    break;
                }

                case "password":{
                    if (!this.validateSumCount(obj[i], 120))
                        return {msg: "Превышено колличество символов email", result: true}
                    break;
                }

                case "name": {
                    if (!this.validateLetters(obj[i]))
                        return {msg: "Имя может состоять только из букв латинского и русского алфавитов", result: true}
                    if (!this.validateSumCount(obj[i], 20))
                        return{msg: "Превышено колличество символов name", result: true}
                    break;
                }

                case "surname":{
                    if (!this.validateLetters(obj[i]))
                        return {msg: "Фамилия может состоять только из букв латинского и русского алфавитов", result: true}
                    if (!this.validateSumCount(obj[i], 20))
                        return{msg: "Превышено колличество символов surname", result: true}
                    break;
                }

                case "patronymic":{
                    if (!this.validateLetters(obj[i]))
                        return {msg: "Отчество может состоять только из букв латинского и русского алфавитов", result: true}
                    if (!this.validateSumCount(obj[i], 20))
                        return{msg: "Превышено колличество символов surname", result: true}
                    break;
                }

                case "telephone":{
                    if (!this.validatePhone(obj[i]))
                        return {msg: "Телефон некоректен", result: true}
                    if (!this.validateSumCount(obj[i], 20))
                        return{msg: "Превышено колличество символов surname", result: true}
                    break;
                }

                case "passport":{
                    if (!this.validateNumbers(obj[i]))
                        return {msg: "Паспорт должен состоять только из цифр", result: true}
                    if (!this.validateSumCount(obj[i], 16))
                        return{msg: "Превышено колличество символов в паспорте", result: true}
                    break;
                }

                case "address":{
                    if (!this.validateSumCount(obj[i], 150))
                        return{msg: "Превышено колличество символов в адресе", result: true}
                    break;
                }

                case "subscriptionTypeId": {
                     if (obj[i] === "DEFAULT") {
                        return{msg: "Выберите абонемент", result: true}
                    }
                    break;
                }

                default: {

                }

            }
        }

        return {msg: "", result: false}
    }


}

export default new Validation()