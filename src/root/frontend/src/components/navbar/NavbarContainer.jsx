import React from 'react';

import Navbar from "./Navbar";
import NavbarApi from "../../services/api/navbar/NavbarApi";

import LStorageUser from "../../services/localstorage/LStorageUser";
import PermissionService from "../../services/security/permission/PermissionService";
import ToastMessages from "../toastmessages/ToastMessages";

export const NavbarContainer = ({setIsAuth}) => {
    const isModer = PermissionService.hasRole("MODERATOR");

    const logOut = () => {
        NavbarApi.logout().then(
            response => {
                console.log(response)
            },
            error => {
                console.error(error)
                ToastMessages.defaultError();
        }).finally(() => {
            LStorageUser.remove();
            setIsAuth(false);
        });
    }
    return ( <Navbar isModer={isModer} logOut={logOut}/> );
}
