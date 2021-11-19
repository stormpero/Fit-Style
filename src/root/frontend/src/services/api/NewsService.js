import api from "./Api";
import {URL_NEWSADD} from "../utils/consts/urlsApi";

class NewsService {
    getNews(pageNumber) {
        return api.get(`news/${pageNumber}`);
    }

    addNews(newsData) {
        console.log(newsData)
        return api.post(URL_NEWSADD, newsData);
    }
}

export default new NewsService();