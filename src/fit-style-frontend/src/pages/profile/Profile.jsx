import React from 'react';
import "./Profile.css";
import ProfilePicture from "../../assets/default-profile-picture.jpg";
import DateConvert from "../../utils/DateConvert";
import ProfileService from "../../services/profile/ProfileService";

const Profile = ({userInfo, img, setModalActive}) => {
    return (
        <div>
            <ul className="circles">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        <div className="container profile-info">
            <div className="d-flex justify-content-between">
                <div className="left-image d-flex flex-column">
                    <img className="picture-profile" src={img ? img : ProfilePicture} alt={userInfo.name + " photo"}/>
                </div>
                <div className="right-info d-flex justify-content-between">
                    <div className="first-column">
                        <p>
                            <label> Номер клубной карты </label>
                            <strong>{userInfo.fitUserInfo.id}</strong>
                        </p>
                        <p>
                            <label> ФИО </label>
                            <strong>{userInfo.fitUserInfo.surname} </strong>
                            <strong>{userInfo.fitUserInfo.name} </strong>
                            <strong>{userInfo.fitUserInfo.patronymic} </strong>
                        </p>
                        <p>
                            <label> Возраст </label>
                            <strong>{userInfo.fitUserInfo.age}</strong>
                        </p>
                        <p>
                            <label> Пол </label>
                            <strong>{userInfo.fitUserInfo.gender}</strong>
                        </p>
                        <p>
                            <label> Дата рождения </label>
                            <strong>{userInfo.fitUserInfo.birthdate}</strong>
                        </p>
                        <p>
                            <label> Роль </label>
                            <strong>{ProfileService.getRoleView(userInfo?.roles.map(value => value.name))}</strong>
                        </p>
                    </div>
                    <div className="second-column">
                        <p>
                            <label> Телефон </label>
                            <strong>{userInfo.fitUserInfo.telephone}</strong>
                        </p>
                        <p>
                            <label> Паспорт </label>
                            <strong>{userInfo.fitUserInfo.passport}</strong>
                        </p>
                        <p>
                            <label> Адрес </label>
                            <strong>{userInfo.fitUserInfo.address}</strong>
                        </p>
                        <p>
                            <label> Вид абонемента </label>
                            <strong>{userInfo?.subscriptionInfo.name}</strong>
                        </p>
                        <p>
                            <label> Дата окончания </label>
                            <strong>{DateConvert.convertDataToNormalData(userInfo?.subscriptionInfo.endDate)}</strong>
                        </p>
                        <p className="p-button">
                            <label> Баланс </label>
                            <strong>{ProfileService.declinationRuble(userInfo.fitUserInfo.balance)}</strong>
                            <button className="button-53" onClick={() => setModalActive(true)}>Пополнить</button>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        </div>
    );
}

export default Profile;