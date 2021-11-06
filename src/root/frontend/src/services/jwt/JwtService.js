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
        const user = LStorageUser.getUser();
        return user ? user?.accessToken : null;
    }

    getRefreshToken() {
        const user = LStorageUser.getUser();
        return user ? user?.refreshToken : null;
    }

    updateAccessToken(accessToken) {
        const user = LStorageUser.getUser();
        user.accessToken = accessToken;
        LStorageUser.setUser(user);
    }
}

export default new JwtService()