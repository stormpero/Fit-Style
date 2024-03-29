import React from "react";
import {Redirect, Route} from "react-router-dom";
import {useAuth} from "../../packages/auth/useAuth";

export const PrivateRoute = ({ path, component, reqRole}) => {
  let {roles} = useAuth();
  return roles.includes(reqRole) ? <Route path={path} component={component}/> : <Redirect to="/"/>;
}