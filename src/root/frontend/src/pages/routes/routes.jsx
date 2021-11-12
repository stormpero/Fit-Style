import React from "react";
import {Route, Switch} from "react-router-dom";

import Login from "../login/Login";
import Register from "../register/Register";
import ProfileContainer from "../profile/ProfileContainer";
import UserContent from "../../components/UserContent";

import PrivateRoute from "./PrivateRoute";
import NewsBord from "../news/NewsBord";

const Routes = () => {
    return (
        <Switch>
            <Route exact path={["/", "/login"]} component={Login} />
            <PrivateRoute role={"ROLE_MODERATOR"} path="/register" component={Register} />
            <PrivateRoute role={"ROLE_USER"} path="/profile" component={ProfileContainer} />
            <PrivateRoute role={"ROLE_USER"} path="/user" component={UserContent} />
            <PrivateRoute role={"ROLE_USER"} path="/news" component={NewsBord} />
        </Switch>
    )
}

export { Routes };