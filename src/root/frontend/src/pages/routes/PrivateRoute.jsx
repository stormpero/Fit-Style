import {Redirect, Route} from "react-router-dom";
import hasPermission from "../../services/security/permisson";
import LStorageUser from "../../services/LStorageUser";

export default function PrivateRoute ({component: Component, role, ...rest}) {
    return (
        <Route
            {...rest}
            render={
                (props) => hasPermission(role) === true
                    ? <Component {...props} />
                    : <Redirect to={{pathname: LStorageUser.isExist() ? '/profile' : '/login', state: {from: props.location}}} />
            }
        />
    )
}
