import {makeRequest} from "../makeRequest";
import {GET} from "../constants/methods";
import {
  URL_ROLES
} from "../constants/urls";

export const getRoles = () => {
  return makeRequest({
    url:URL_ROLES,
    method: GET,
  })
}
