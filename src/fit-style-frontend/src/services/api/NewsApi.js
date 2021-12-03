import api from "./Api";
import {URL_ADD_NEWS} from "../../config/consts/urlsApi";

class NewsApi {
    getNews(pageNumber) {
        return api.get(`news/${pageNumber}`);
    }
    addNews(newsData) {
        return api.post(URL_ADD_NEWS, newsData);
    }
    deleteNews(id) {
        return api.delete(`news/${id}`, {data: { id: id} });
    }
}

export default new NewsApi();