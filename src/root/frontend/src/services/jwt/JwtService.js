import LStorageUser from "../LStorageUser";

class JwtService {
    parseJwt(token) {
        try {
            return JSON.parse(atob(token.split('.')[1]));
        } catch (e) {
            return null;
        }
    };

    getAccessToken() {
        return LStorageUser.getAccessToken();
    }

    getRefreshToken() {

    }
}

export default new JwtService()