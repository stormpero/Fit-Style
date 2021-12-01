import React, {useEffect, useState} from "react";
import Profile from "./Profile";
import Modal from "../../components/modal/Modal";
import {PaymentContainer} from "../../components/paymentform/PaymentContainer";
import UserService from "../../services/api/user/UserService";
import DateFormat from "../../utils/DateConvert";

export const ProfileContainer = () => {
    const [modalActive, setModalActive] = useState(false);
    const [userInfo, setUserInfo] = useState(null);
    const [userImg, setUserImg] = useState(null);
    const [isReady, setIsReady] = useState(false);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        Promise.allSettled([UserService.getProfileInfo(), UserService.getProfileImg()]).then(
            response => {
                const [userInfo, img] = response.map(element => element?.status === "fulfilled" ? element?.value.data : null);
                userInfo.id = ('000000' + userInfo.id).slice(Math.log(Number(userInfo.id)) * Math.LOG10E + 1 | 0);
                userInfo.birthdate = DateFormat.convertDataToNormalData(userInfo.birthdate);

                setUserInfo(userInfo);
                setUserImg(img ? URL.createObjectURL(img) : null);
            }
        ).finally(() => setIsReady(true))
    }, [reload])

    return (
        isReady &&
        <div>
            <Profile userInfo={userInfo} img={userImg} setModalActive={setModalActive}/>
            <Modal active={modalActive} setActive={setModalActive} options={{closeBackground: false}}>
                <PaymentContainer setReload={setReload} setActive={setModalActive}/>
            </Modal>
        </div>
    );
}