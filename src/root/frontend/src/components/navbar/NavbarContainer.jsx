import React, {Component} from 'react';
import LStorageUser from "../../services/LStorageUser";
import AuthService from "../../services/AuthService";


import Navbar from "./Navbar";

export default class NavbarContainer extends Component {
    state = {
        isAdmin: false
    }

    componentDidMount() {
        const user = LStorageUser.getUser();
        if (user) {
            this.setState({
                isAdmin: user?.roles.includes("ROLE_MODERATOR"),
            });
        }
    }

    logOut() {
        AuthService.logout();
    }

    render() {
        const { isAdmin } = this.state;

        return (
            <Navbar isAdmin={isAdmin} logOut={this.logOut}/>
        );
    }
}
