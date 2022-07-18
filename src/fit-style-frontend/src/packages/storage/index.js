import {setStorage} from "./storage";
import * as token from "./adapters/token";

setStorage(localStorage);

export {
  token
}

