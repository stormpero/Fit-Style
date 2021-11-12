import api from "./Api";
import LStorageUser from "./LStorageUser";

class AuthService {
    login(username, password) {
        return api
            .post('auth/signin',{
               username,
               password
            })
            .then(response => {
                if (response.data.accessToken) {
                    LStorageUser.setUser(response.data);
                }
                return response.data;
            });
    }

    logout() {
        LStorageUser.remove();
    }

    register(username, email, password, surname, patronymic, age, gender, birthdate, telephone, passport, address) {
        const name = 'denis';
        return api
            .post('auth/signup', {
                name,
                username,
                email,
                password,
                surname,
                patronymic,
                age,
                gender,
                birthdate,
                telephone,
                passport,
                address
            });
    }
}

export default new AuthService();
