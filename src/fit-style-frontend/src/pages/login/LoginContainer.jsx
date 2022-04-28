import React, {useState} from "react";

import Login from "./Login";

import isEmpty from "validator/es/lib/isEmpty";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import {TOP_CENTER} from "../../config/consts/ToastPosition";
import Modal from "../../components/modal/Modal";
import {RecoverPassword} from "./form/RecoverPassword";
import {useHistory} from "react-router-dom";
import {useInput} from "../../customHooks/useInput";
import {useAuth} from "../../packages/auth/useAuth";


export const LoginContainer = () => {
    const history = useHistory();
    const [modalActive, setModalActive] = useState(false);
    const {login} = useAuth();
    const email = useInput("","Email","text")
    const password = useInput("","Password", "password")

    const handleLogin = (event) => {
        event.preventDefault();

        //TODO: DELETE  - - - <<< TESTING >>>
        // const email = "AdminProfile@gmail.com";
        // const password = "AdminProfile"
        // login({email, password});

        if (isEmpty(email.value) || isEmpty(password.value)) {
            const errorMsg = "Заполните поля";
            ToastMessages.error(errorMsg, TOP_CENTER);
            return;
        }
        login({email: email.value, password: password.value});
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