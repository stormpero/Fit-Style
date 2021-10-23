import React from "react";
import {Route, Switch} from "react-router-dom";

import Login from "../login/Login";
import Register from "../register/Register";
import Profile from "../profile/Profile";
import UserContent from "../../components/UserContent";

const Routes = () => {
    return (
        <Switch>
            <Route exact path={["/", "/login"]} component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/profile" component={Profile} />
            <Route path="/user" component={UserContent} />
        </Switch>
    )
}

export { Routes };
