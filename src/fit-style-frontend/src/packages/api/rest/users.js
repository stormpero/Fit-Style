import {makeRequest} from "../makeRequest";
import {GET} from "../constants/methods";
import {
  URL_ALL_USERS,
  URL_IMG_USER,
  URL_DISABLE_USER,
  URL_ENABLE_USER
} from "../constants/urls"


export const getAllUsers = () => {
  return makeRequest({
    url: URL_ALL_USERS,
    method: GET,
  })
}

export const getUserImg = (id) => {
  return makeRequest({
    url: URL_IMG_USER + id,
    method: GET,
    responseType: 'blob',
  })
}

export const enableUser = (id) => {
  return makeRequest({
    url: URL_ENABLE_USER + id,
    method: GET,
  })
}

export const disableUser = (id) => {
  return makeRequest({
    url: URL_DISABLE_USER + id,
    method: GET,
  })
}