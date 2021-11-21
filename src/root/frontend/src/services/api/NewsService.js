import api from "./Api";
import {URL_NEWSADD} from "../utils/consts/urlsApi";

class NewsService {
    getNews(pageNumber) {
        return api.get(`news/${pageNumber}`);
    }

    addNews(newsData) {
        return api.post(URL_NEWSADD, newsData);
    }
    deleteNews(id) {
        return api.delete(`news/${id}`, {data: { id: id} });
    }
}

export default new NewsService();