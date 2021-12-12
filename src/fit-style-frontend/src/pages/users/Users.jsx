import React from 'react';
import ProfileService from "../../services/profile/ProfileService";
import LStorageUser from "../../services/localstorage/LStorageUser";
import defaultProfile from "./../../assets/default-profile-picture.jpg"
export const Users = ({userList, disableUser , enableUser , setReload}) => {
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
                                        <th><span>Email</span></th>
                                        <th className="text-center"><span>Статус</span></th>
                                        <th>&nbsp;</th>
                                    </tr>
                                </thead>
                                <tbody >
                                {userList.map(({fitUserInfo, img, roles, subscriptionInfo}) =>
                                    <tr key={fitUserInfo.id}>
                                        <td className="user-info">
                                            <img src={img ? img : defaultProfile} alt=""/>
                                            <span className="user-subhead">{fitUserInfo.id}</span>
                                            <span className="user-link">{fitUserInfo.surname} {fitUserInfo.name}</span>
                                            <span className="user-subhead">{ProfileService.getRoleView(roles)}</span>
                                        </td>
                                        <td className="user-id">
                                            {fitUserInfo.telephone}
                                        </td>
                                        <td className="text-center user-email">
                                            <span>{fitUserInfo.email}</span>
                                        </td>
                                        <td className="text-center user-status">
                                            <span className={ fitUserInfo.enabled ? "badge rounded-pill bg-success" : "badge rounded-pill bg-danger"}>{ fitUserInfo.enabled ? "Активен" : "Заблокирован"}</span>
                                        </td>
                                        <td className="buttons">
                                            {LStorageUser.getId() !== Number(fitUserInfo.id) ? fitUserInfo.enabled ?
                                                    <button type="button" className="btn btn-danger button-block" onClick={disableUser} id={fitUserInfo.id}>Заблокировать</button>
                                                :
                                                <button type="button" className="btn btn-success button-block" onClick={enableUser} id={fitUserInfo.id}>Разблокировать</button>
                                                :
                                                <button type="button" className="btn btn-primary" disabled={true}>Текущий пользователь</button>
                                            }
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