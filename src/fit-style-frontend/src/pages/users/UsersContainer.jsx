import React, {useEffect, useState} from 'react';
import {Users} from "./Users";
import UserApi from "../../services/api/UserApi";
import ToastMessages from "../../components/toastmessages/ToastMessages";

export const UsersContainer = (props) => {
    const [userList, setUserList] = useState([]);
    useEffect(() => {
        UserApi.getAllUsers().then(
            response => {
                console.log(response.data.fitUsers)
                setUserList(response.data.fitUsers)
            },
            error => {
                ToastMessages.defaultError();
            }
        )
    }, [])
    return (
        <Users userList={userList}/>
    );
}
