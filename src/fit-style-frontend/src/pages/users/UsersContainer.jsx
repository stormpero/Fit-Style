import React, {useEffect, useState} from 'react';
import {Users} from "./Users";
import UserApi from "../../services/api/UserApi";
import ToastMessages from "../../components/toastmessages/ToastMessages";
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

                getUserImages(fitUsersTemp).then(
                    response => {
                        setUserList(response);
                    }
                );
            },
            error => {
                ToastMessages.defaultError();
            }
        )
    }, [])

    const getUserImages = async (fitUsersTemp) => {
        for (const value of fitUsersTemp) {
            if (value?.fitUserInfo?.imgURL === null) {
                value.img = null;
                continue;
            }

            try {
                let response = await UserApi.getUserImg(value?.fitUserInfo.id);
                let imageData = response.data;
                value.img = imageData ? URL.createObjectURL(imageData) : null;
            } catch (error) {}
        }
        return fitUsersTemp;
    }

    const setUserStatus = (event , status) => {
        const {id} = event.target;
        let promise = status ? UserApi.enableUser(+id) : UserApi.disableUser(+id);
        promise.then(
            response => {
                const enabled = response.data.message === "User enabled successfully!";
                ToastMessages.success(enabled ? "Пользователь удалён!" : "Пользователь восстановлен!");
                const tempUsers = [...userList];
                tempUsers[id - 1].fitUserInfo.enabled = enabled;
                setUserList(tempUsers);
            },
            error => {
                console.log(error.response);
                ToastMessages.defaultError();
            });
    }

    return (
        <Users userList={userList} setUserStatus = {setUserStatus} />
    );
}
