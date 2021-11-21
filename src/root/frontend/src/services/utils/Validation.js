class Validation {
    validateNull(obj) {
        for (let i in obj) {
            if (obj.hasOwnProperty(i)) {
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



}

export default new Validation()