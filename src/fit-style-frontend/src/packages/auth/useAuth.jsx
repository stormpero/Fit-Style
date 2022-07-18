import {useContext} from "react";
import authContext from "./authContext";

export const useAuth = () => {
  return useContext(authContext);
}