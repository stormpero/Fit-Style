import React, {useState} from "react";

import Login from "./Login";

import isEmpty from "validator/es/lib/isEmpty";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import {TOP_CENTER, TOP_RIGHT} from "../../config/consts/ToastPosition";
import LoginApi from "../../services/api/LoginApi";
import Modal from "../../components/modal/Modal";
import {RecoverPassword} from "./form/RecoverPassword";
import {useHistory} from "react-router-dom";
import {useInput} from "../../customHooks/useInput";


export const LoginContainer = ({setIsAuth}) => {
    const history = useHistory();
    const [modalActive, setModalActive] = useState(false);

    const email = useInput("","Email","text")
    const password = useInput("","Password", "password")

    const handleLogin = (event) => {
        event.preventDefault();
        if (isEmpty(email.value) || isEmpty(password.value)) {
            const errorMsg = "Заполните поля";
            ToastMessages.error(errorMsg, TOP_CENTER);
            return;
        }

        LoginApi.login({email: email.value, password: password.value}).then(
            response => {
                ToastMessages.success("Добро пожаловать!", TOP_RIGHT);
                setIsAuth(true);
                history.push("/news");
            },
            error => {
                console.log(error.response)
                if (error?.response?.data?.message === "Bad credentials") {
                    ToastMessages.error("Неверные данные");
                } else {
                    ToastMessages.defaultError();
                }
            }
        );
    }

    return (
        <div>
            <Login
                handleLogin={handleLogin}
                setModalActive={setModalActive}
                emailState={email}
                passwordState={password}
            />
            <Modal active={modalActive} setActive={setModalActive} options={{closeBackground: false}}>
                <RecoverPassword setActive={setModalActive}/>
            </Modal>
        </div>
    );
}