
class LStorageUser {
    constructor() {
        this.user = JSON.parse(localStorage.getItem('user'));
        this.exist =  !!(this.user);
    }

    getUser() {
        return this.exist ? this.user : null;
    }

    getAccessToken() {
        return this.exist ? this.user.accessToken : null;
    }

    getId() {
        console.log(this.exist ? this.user.id : null);
        return this.exist ? this.user.id : null;
    }

    getRoles() {
        return this.exist? this.user.roles : null;
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