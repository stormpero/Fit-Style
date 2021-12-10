import React from 'react';
import ProfileService from "../../services/profile/ProfileService";

export const Users = ({userList}) => {

    return (
        <div >
            <ul >
            {userList.map(({fitUserInfo, roles, subscriptionInfo}) =>
                <li key={fitUserInfo.id} >
                    {fitUserInfo.id} {fitUserInfo.surname} {fitUserInfo.name} {fitUserInfo.patronymic} {fitUserInfo.email} {ProfileService.getRoleView(roles)} {subscriptionInfo.name}
                </li>)
            }
            </ul>
        </div>
    );
};