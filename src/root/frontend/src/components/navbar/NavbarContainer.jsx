import React from 'react';
import LStorageUser from "../../services/localstorage/LStorageUser";
import api from "../../services/api/config/Api";

import Navbar from "./Navbar";
import PermissionService from "../../services/security/permission/PermissionService";

export const NavbarContainer = ({setIsAuth}) => {
    const isModer = PermissionService.hasRole("MODERATOR");

    const logOut = () => {
        api.get('auth/logout').then(
            response => {
                console.log(response)
            },
            error => {
                console.error(error)
        }).finally(() => {
            LStorageUser.remove();
            setIsAuth(false);
        });
    }
    return ( <Navbar isModer={isModer} logOut={logOut}/> );
}
