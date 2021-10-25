import React from "react";
import {Route, Switch} from "react-router-dom";

import Login from "../login/Login";
import Register from "../register/Register";
import Profile from "../profile/Profile";
import UserContent from "../../components/UserContent";

import PrivateRoute from "./PrivateRoute";

const Routes = () => {
    return (
        <Switch>
            <Route exact path={["/", "/login"]} component={Login} />
            <PrivateRoute role={"ROLE_MODERATOR"} path="/register" component={Register} />
            <PrivateRoute exact role={"ROLE_USER"} path="/profile" component={Profile} />
            <PrivateRoute role={"ROLE_USER"} path="/user" component={UserContent} />
        </Switch>
    )
}

export { Routes };
