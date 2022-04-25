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
            } //

            try {
                let response = await UserApi.getUserImg(value?.fitUserInfo.id);
                let imageData = response.data;
                value.img = imageData ? URL.createObjectURL(imageData) : null
            } catch (error) {}
        }
        return fitUsersTemp;
    }

    const disableUser = (event) => {
        const {id} = event.target;
        UserApi.disableUser(+id).then(
            response => {
                ToastMessages.success("Пользователь удалён!");
                const tempUsers = [...userList];
                tempUsers[id - 1].fitUserInfo.enabled = false;
                setUserList(tempUsers);
            },
            error => {
                console.log(error.response);
                ToastMessages.defaultError();
            }
        )
    }

    const enableUser = (event) => {
        const {id} = event.target;
        UserApi.enableUser(+id).then(
            response => {
                ToastMessages.success("Пользователь восстановлен!");
                const tempUsers = [...userList];
                tempUsers[id - 1].fitUserInfo.enabled = true;
                setUserList(tempUsers);
            },
            error => {
                console.log(error.response);
                ToastMessages.defaultError();
            }
        )
    }

    return (
        <Users userList={userList} disableUser={disableUser} enableUser={enableUser} />
    );
}
