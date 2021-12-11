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
                                        <th><span>Пользователь</span></th>
                                        <th className="text-center"><span>Телефон</span></th>
                                        <th className="text-center"><span>Статус</span></th>
                                        <th><span>Email</span></th>
                                        <th>&nbsp;</th>
                                    </tr>
                                </thead>
                                <tbody >
                                {userList.map(({fitUserInfo, img, roles, subscriptionInfo}) =>
                                    <tr key={fitUserInfo.id}>
                                        <td className="user-info">
                                            <img src={img} alt=""/>
                                            <span className="user-subhead">{fitUserInfo.id}</span>
                                            <span className="user-link">{fitUserInfo.surname} {fitUserInfo.name}</span>
                                            <span className="user-subhead">{ProfileService.getRoleView(roles)}</span>
                                        </td>
                                        <td className="user-id">
                                            {fitUserInfo.telephone}
                                        </td>
                                        <td className="text-center user-status">
                                            <span className="badge bg-secondary">Inactive</span>
                                        </td>
                                        <td className="text-center user-email">
                                            <span>{fitUserInfo.email}</span>
                                        </td>
                                        <td className="buttons">
                                            <button type="button" className="btn btn-danger">Заблокировать</button>
                                        </td>
                                    </tr>
                                )}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};