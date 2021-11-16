import api from "./Api";

class UserService {

  getUserBoard() {
    return api.get('test/user');
  }

  getProfileInfo(id) {
    return api.get('profile');
  }

  getRoles(id) {
    return api.get('permission/roles', { params: { id: id} });
  }

  getNews(pageNumber) {
    return api.get(`news/${pageNumber}`);
  }
}

export default new UserService();
