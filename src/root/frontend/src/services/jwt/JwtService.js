import LStorageUser from "../LStorageUser";

class JwtService {
    getAuthHeader() {
        const user = LStorageUser.getUser();

        if (user && user.accessToken) {
            return { Authorization: 'Bearer ' + user.accessToken };
        } else {
            return {};
        }
    }

    parseJwt(token) {
        try {
            return JSON.parse(atob(token.split('.')[1]));
        } catch (e) {
            return null;
        }
    };
}

export default new JwtService()