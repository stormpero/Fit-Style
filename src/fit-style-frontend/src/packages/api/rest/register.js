import {makeRequest} from "../makeRequest";
import {GET, POST} from "../constants/methods";
import {
  URL_REGISTER,
  URL_SUBSCRIPTIONTYPE
} from "../constants/urls";


export const register = (data) => {
  return makeRequest({
    url: URL_REGISTER,
    method: POST,
    data,
  });
}

export const getSubscriptionType = () => {
  return makeRequest({
    url: URL_SUBSCRIPTIONTYPE,
    method: GET,
  });
}


