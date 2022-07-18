import {makeRequest} from "../makeRequest";
import {DELETE, GET, POST} from "../constants/methods";
import {
  URL_NEWS,
  URL_NEWS_IMG
} from "../constants/urls";


export const addNews = (data) => {
  return makeRequest({
    url: URL_NEWS,
    method: POST,
    data,
  })
}

export const getNews = (pageNumber) => {
  return makeRequest({
    url: URL_NEWS + pageNumber,
    method: GET,
  })
}

export const getNewsImage = (id) => {
  return makeRequest({
    url: URL_NEWS_IMG + id,
    method: GET,
    responseType: 'blob',
  })
}

export const deleteNews = (id) => {
  return makeRequest({
    url: URL_NEWS + id,
    method: DELETE,
    data: { id: id},
  })
}
