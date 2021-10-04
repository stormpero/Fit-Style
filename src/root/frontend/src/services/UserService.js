import axios from "axios";

const USER_API_URL = "http://localhost:8080/users";

class UserService {

    getUsers() {
        return axios.get(USER_API_URL);
    }
}


export default new UserService()