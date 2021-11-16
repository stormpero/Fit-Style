import {useEffect, useState} from "react";
import {Redirect, Route} from "react-router-dom";

import LStorageUser from "../../services/LStorageUser";
import UserService from "../../services/UserService";

export default function PrivateRoute ({component: Component, role, ...rest}) {

    const [isLoad, setLoad] = useState(false);
    const [isPerm, setPerm] = useState(false);

    useEffect( () => {
        UserService.getRoles(LStorageUser.getId()).then(
            response => {
                let cookieRoles = response.data?.roles.map(res => res.name);
                if (!cookieRoles.length) alert('Ошибка Backend, У пользователя нет ролей');
                setPerm(!!(cookieRoles.indexOf(role) + 1));
            },
            error => {
                console.log('error PrivateRoute', error)
                //LStorageUser.remove();
            }).finally(() => setLoad(true))
    }, [role])

    return (
        <Route
            {...rest}
            render=
            {
                (props) =>
                 isLoad
                        ? isPerm
                            ? <Component {...props} />
                            : <Redirect to={{
                                pathname: '/profile',
                                state: {from: props.location}
                            }}/>
                        : null
            }
        />
    )
}
