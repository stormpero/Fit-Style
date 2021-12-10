import React, {useEffect, useState} from 'react';
import {Users} from "./Users";
import UserApi from "../../services/api/UserApi";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import NewsApi from "../../services/api/NewsApi";
import "./Users.css"

export const UsersContainer = (props) => {
    const [userList, setUserList] = useState([]);
    useEffect(() => {
        UserApi.getAllUsers().then(
            response => {
                let fitUsersTemp = response.data?.fitUsers;
                fitUsersTemp = fitUsersTemp.map(value => {
                    value.fitUserInfo.id = ('000000' + value?.fitUserInfo.id).slice(Math.log(Number(value?.fitUserInfo.id)) * Math.LOG10E + 1 | 0)
                    value.roles = value?.roles.map(value => value.name);
                    return value;
                });
                setUserList(fitUsersTemp)
            },
            error => {
                ToastMessages.defaultError();
            }
        )
    }, [])

    const getUserImages = async (rowNewsData) => {
        try {
            for (const value of rowNewsData) {
                let response = await NewsApi.getUserImg(value.id);
                let imageData = response.data;
                value.img = imageData ? URL.createObjectURL(imageData) : null
            }
        } catch (error) {}
        return rowNewsData
    }

    return (
        <Users userList={userList}/>
    );
}
