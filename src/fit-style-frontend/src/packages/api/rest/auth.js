import {makeRequest} from "../makeRequest";
import {GET, POST} from "../constants/methods";
import {
  URL_AUTH,
  URL_ASK_RECOVER_WITH_EMAIL,
  URL_CONFIRM_RECOVERY,
  URL_LOGOUT,
  URL_USERDATA
} from "../constants/urls";


export const login = (data) => {
  return makeRequest({
    url: URL_AUTH,
    method: POST,
    data,
    withCredentials: true,
  });
}

export const askForRecoverWithEmail = (data) => {
  return makeRequest({
    url: URL_ASK_RECOVER_WITH_EMAIL,
    method: POST,
    data,
  });
}

export const confirmRecovery = (data) => {
  return makeRequest({
    url: URL_CONFIRM_RECOVERY,
    method: POST,
    data,
  });
}

export const logout = () => {
  return makeRequest({
    url: URL_LOGOUT,
    method: GET,
  });
}

export const getUserData = () =>{
  return makeRequest({
    url: URL_USERDATA,
    method: GET,
  })
}
//TODO: URL_REFRESHTOKEN