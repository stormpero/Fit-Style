import {instance} from "./axios/createInstance";
import {GET} from "./constants/methods";

export const makeRequest = ({
  url = '/',
  method = GET,
  data = {},
  ...configs
}) => {
  return instance.request({url, method, data, ...configs});
};