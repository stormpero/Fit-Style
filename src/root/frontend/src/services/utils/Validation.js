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

    validatePhone(obj){
        //Should work for different countries
        let regex = /^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$/;
        return regex.test(obj);
    }


}

export default new Validation()