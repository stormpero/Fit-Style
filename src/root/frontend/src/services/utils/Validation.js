class Validation {
    validIsNull(obj) {
        for (let i in obj) {
            if (obj.hasOwnProperty(i)) {
                return true;
            }
        }
        return false;
    }

}

export default new Validation()