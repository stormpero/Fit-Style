import React from 'react';
import ProfileService from "../../services/profile/ProfileService";

export const Users = ({userList}) => {

    return (
        <div className="container">
            <ul className="list-group">
            {userList.map(({fitUserInfo, roles, subscriptionInfo}) =>
                <li key={fitUserInfo.id} className="list-group-item">
                    {fitUserInfo.id} {fitUserInfo.surname} {fitUserInfo.name} {fitUserInfo.patronymic} {fitUserInfo.email} {ProfileService.getRoleView(roles)} {subscriptionInfo.name}
                </li>)
            }
            </ul>
        </div>
    );
};