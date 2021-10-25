import React from "react";
import {Route, Switch} from "react-router-dom";

import Login from "../login/Login";
import Register from "../register/Register";
import Profile from "../profile/Profile";
import UserContent from "../../components/UserContent";

import requireAuth from "../../services/security/reqAuth";

const Routes = () => {
    return (
        <Switch>
            <Route exact path={["/", "/login"]} component={Login} />
            <Route exact path="/register" component={requireAuth(Register)} />
            <Route exact path="/profile" component={requireAuth(Profile)} />
            <Route path="/user" component={requireAuth(UserContent)} />
        </Switch>
    )
}

export { Routes };
