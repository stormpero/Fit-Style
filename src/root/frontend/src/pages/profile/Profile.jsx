import React, {useState} from 'react';

import "./Profile.css";
import ProfilePicture from "../../assets/default-profile-picture.jpg";
import Modal from "../../components/modal/Modal";
import PaymentForm from "../../components/paymentform/PaymentForm"
import DateConvert from "../../utils/DateConvert";
import ProfileService from "../../services/profile/ProfileService";

const Profile = ({userInfo, img, setReload}) => {
    const [modalActive, setModalActive] = useState(false);
    return (
            <div className="container profile-info">
                <div className="d-flex justify-content-between">
                    <div className="left-image d-flex flex-column">
                        <img className="picture-profile" src={img ? img : ProfilePicture} alt={userInfo.name + " photo"}/>
                    </div>
                    <div className="right-info d-flex justify-content-between">
                        <div className="first-column">
                            <p>
                                <label> Номер клубной карты </label>
                                <strong>{userInfo.id}</strong>
                            </p>
                            <p>
                                <label> ФИО </label>
                                <strong>{userInfo.surname} </strong>
                                <strong>{userInfo.name} </strong>
                                <strong>{userInfo.patronymic} </strong>
                            </p>
                            <p>
                                <label> Возраст </label>
                                <strong>{userInfo.age}</strong>
                            </p>
                            <p>
                                <label> Пол </label>
                                <strong>{userInfo.gender}</strong>
                            </p>
                            <p>
                                <label> Дата рождения </label>
                                <strong>{userInfo.birthdate}</strong>
                            </p>
                            <p>
                                <label> Роль </label>
                                <strong>{ProfileService.getRoleView(userInfo?.roles.map(value => value.name))}</strong>
                            </p>
                        </div>
                        <div className="second-column">
                            <p>
                                <label> Телефон </label>
                                <strong>{userInfo.telephone}</strong>
                            </p>
                            <p>
                                <label> Паспорт </label>
                                <strong>{userInfo.passport}</strong>
                            </p>
                            <p>
                                <label> Адрес </label>
                                <strong>{userInfo.address}</strong>
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
                                <strong>{userInfo.balance} рублей</strong>
                                <button className="button-53" onClick={() => setModalActive(true)}>Пополнить</button>
                            </p>

                        </div>
                    </div>
                </div>
                {
                    <Modal active={modalActive} setActive={setModalActive} options={{closeBackground: false}}>
                        <PaymentForm setReload={setReload} setActive={setModalActive}/>
                    </Modal>
                }
            </div>
    );
}

export default Profile;