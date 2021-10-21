
class LStorageUser {
    constructor() {
        this.user = JSON.parse(localStorage.getItem('user'));
        this.exist =  !!(this.user);
    }

    getUser() {
        return this.exist ? this.user : null;
    }

    getToken() {
        return this.exist ? this.user.accessToken : null;
    }

    isExist() {
        return this.exist;
    }

    add(data) {
        localStorage.setItem("user", JSON.stringify(data));
        this.user = data;
        this.exist = true;
    }

    remove() {
        localStorage.removeItem("user");
        this.user = null;
        this.exist = false;
    }
}

export default new LStorageUser()