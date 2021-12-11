import React from 'react';
import ProfileService from "../../services/profile/ProfileService";

export const Users = ({userList}) => {

    return (
        <div className="container">
                    <div className="row">
                        <div className="col-lg-12">
                            <div className="main-box clearfix">
                                <div className="table-responsive">
                                    <table className="table user-list">
                                        <thead>
                                        <tr>
                                            <th><span>User</span></th>
                                            <th className="text-center"><span>ID</span></th>
                                            <th className="text-center"><span>Status</span></th>
                                            <th><span>Email</span></th>
                                            <th>&nbsp;</th>
                                        </tr>
                                        </thead>
                                        {userList.map(({fitUserInfo, img, roles, subscriptionInfo}) =>

                                        <tbody key={fitUserInfo.id}>
                                        <tr>
                                            <td className="user-info">
                                                <img src={img} alt=""/>
                                                <span className="user-link">{fitUserInfo.surname} {fitUserInfo.name}</span>
                                                <span className="user-subhead">{ProfileService.getRoleView(roles)}</span>
                                            </td>
                                            <td className="user-id">
                                                {fitUserInfo.id}
                                            </td>
                                            <td className="text-center user-status">
                                                <span className="badge bg-secondary">Inactive</span>
                                            </td>
                                            <td className="text-center user-email">
                                                <span>{fitUserInfo.email}</span>
                                            </td>
                                            <td className="buttons">
                                                <button type="button" className="btn btn-danger">Сброс</button>
                                            </td>
                                        </tr>
                                        </tbody>)
                                        }
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

        </div>
    );
};