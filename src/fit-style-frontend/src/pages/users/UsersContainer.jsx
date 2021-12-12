import React, {useEffect, useState} from 'react';
import {Users} from "./Users";
import UserApi from "../../services/api/UserApi";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import "./Users.css"

export const UsersContainer = (props) => {
    const [userList, setUserList] = useState([]);
    const [reload, setReload] = useState(false);

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
    }, [reload])

    const getUserImages = async (fitUsersTemp) => {
        for (const value of fitUsersTemp) {
            if (value?.fitUserInfo?.imgURL === null) {
                value.img = null;
                continue;
            }

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
        UserApi.disableUser(Number(id)).then(
            response => {
                ToastMessages.success("Пользователь удалён!");
                setReload(prev => !prev);
            },
            error => {
                console.log(error.response);
                ToastMessages.defaultError();
            }
        )
    }

    const enableUser = (event) => {
        const {id} = event.target;
        UserApi.enableUser(Number(id)).then(
            response => {
                ToastMessages.success("Пользователь восстановлен!");
                setReload(prev => !prev);
            },
            error => {
                console.log(error.response);
                ToastMessages.defaultError();
            }
        )
    }

    return (
        <Users userList={userList} disableUser={disableUser} enableUser={enableUser} setReload={setReload}/>
    );
}
