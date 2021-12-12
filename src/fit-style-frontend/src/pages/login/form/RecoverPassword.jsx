import React, {useState} from 'react';
import UserApi from "../../../services/api/UserApi";
import ToastMessages from "../../../components/toastmessages/ToastMessages";
import isEmpty from "validator/es/lib/isEmpty";
import {TOP_CENTER} from "../../../config/consts/ToastPosition";
import {NOT_FOUND, WRONG_CODE} from "../../../config/consts/ErrorCode";

export const RecoverPassword = ({setActive}) => {
    const [isStepOne, setIsStepOne] = useState(true);

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [passwordCopy, setPasswordCopy] = useState("");
    const [code, setCode] = useState("");

    const [isError, setIsError] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        if (isStepOne) {
            if (isEmpty(email)) {
                ToastMessages.error("Введите Email", TOP_CENTER);
                return
            }
            const {hide} = ToastMessages.loading("Отправка письма", { hideAfter: 0 });
            UserApi.askForRecoverWithEmail({email}).then(
                response => {
                    hide();
                    ToastMessages.success("Письмо с восстановлением пароля отправлено Вам на почту!");
                    setIsStepOne(false);
                },
                error => {
                    hide();
                    if (error.response.data.errorCode === NOT_FOUND) {
                        ToastMessages.error("Неверный Email");
                    } else {
                        console.log(error.response)
                        ToastMessages.defaultError();
                    }
                }
            )
        } else {
            if (isEmpty(code)) {
                ToastMessages.error("Введите код восстановления пароля", TOP_CENTER);
                return;
            }
            if (password !== passwordCopy) {
                ToastMessages.error("Пароли не совпадают", TOP_CENTER);
                return;
            }
            if (password.length < 6) {
                ToastMessages.error("Пароли должен быть больше 6 символов", TOP_CENTER);
                return;
            }

            UserApi.confirmRecovery({code, password}).then(
                response => {
                    ToastMessages.success("Ваш пароль успешно изменён!");
                },
                error => {
                    if (error.response.data.errorCode === WRONG_CODE) {
                        ToastMessages.error("Неверный код восстановления");
                    } else {
                        console.log(error.response);
                        ToastMessages.defaultError();
                    }

                }
            ).finally(() => {
                setEmail("");
                setPassword("");
                setPasswordCopy("");
                setCode("");
                setActive(false);
                setIsStepOne(true);
            });
        }
    }

    const isPasswordsEquel = (event) => {
        setPasswordCopy(event.target.value);
        let temp = event.target.value;
        if (password.substring(0, temp.length) !== temp) {
            setIsError(true);
        } else {
            setIsError(false)
        }
    }

    return (
        <div>
            <h4>Восстановление пароля</h4>
            <form className="form-div">
                <div className="form-group">
                    <label htmlFor="email"/>
                    <input className="form-control"
                           required
                           name="email"
                           type="text"
                           onChange={(e) => setEmail(e.target.value)}
                           value={email}
                           placeholder={"Введите Email"}
                           readOnly={!isStepOne}
                    />
                </div>
                {!isStepOne &&
                    <>
                        <div className="form-group">
                            <label htmlFor="email"/>
                            <input className="form-control"
                                   required
                                   name="email"
                                   type="text"
                                   onChange={(e) => setCode(e.target.value)}
                                   value={code}
                                   placeholder={"Введите код"}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="email"/>
                            <input className={"form-control " + (isError ? "is-invalid" : "")}
                                   required
                                   name="new-password"
                                   type="password"
                                   onChange={(e) => setPassword(e.target.value)}
                                   value={password}
                                   placeholder={"Введите новый пароль"}
                                   autoComplete="off"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="email"/>
                            <input className={"form-control " + (isError ? "is-invalid" : "")}
                                   required
                                   name="new-password"
                                   type="password"
                                   onChange={isPasswordsEquel}
                                   value={passwordCopy}
                                   placeholder={"Повторите пароль"}
                                   autoComplete="off"
                            />
                        </div>
                        {isError &&
                            <div className="text-danger">
                                Пароли не совпадают
                            </div>
                        }
                    </>
                }
                <div className="d-flex justify-content-between mt-3">
                    <button className="btn btn-primary"
                            onClick={handleSubmit}>
                        {isStepOne ? "Восстановить пароль" : "Изменить пароль"}
                    </button>
                </div>
            </form>
        </div>
    );
};

