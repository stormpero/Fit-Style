import React from 'react';
import ProfileService from "../../services/profile/ProfileService";

export const Users = ({userList}) => {

    return (
        <div className="container">
            <ul >
            {userList.map(({fitUserInfo, roles, subscriptionInfo}) =>
                <li key={fitUserInfo.id} >
                    {fitUserInfo.id} {fitUserInfo.surname} {fitUserInfo.name} {fitUserInfo.patronymic} {fitUserInfo.email} {ProfileService.getRoleView(roles)} {subscriptionInfo.name}
                </li>)
            }
            </ul>
            <div className="row">
                <div className="col-lg-12">
                    <div className="main-box clearfix">
                        <div className="table-responsive">
                            <table className="table user-list">
                                <thead>
                                <tr>
                                    <th><span>User</span></th>
                                    <th><span>Created</span></th>
                                    <th className="text-center"><span>Status</span></th>
                                    <th><span>Email</span></th>
                                    <th>&nbsp;</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt=""/>
                                            <span href="#" className="user-link">Mila Kunis</span>
                                            <span className="user-subhead">Admin</span>
                                        </td>
                                        <td>
                                            2013/08/08
                                        </td>
                                        <td className="text-center">
                                            <span className="label label-default">Inactive</span>
                                        </td>
                                        <td>
                                            <a href="#">mila@kunis.com</a>
                                        </td>
                                        <td className="buttons">
                                            <a href="#" className="table-link">
                                                <span className="fa-stack">
                                                    <i className="fa fa-square fa-stack-2x"></i>
                                                    <i className="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                                                </span>
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};