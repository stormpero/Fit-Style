import LoginContainer from "../login/LoginContainer";
import RegisterContainer from "../register/RegisterContainer";
import ProfileContainer from "../profile/ProfileContainer";
import UserContent from "../../components/UserContent";
import NewsBoard from "../news/NewsBoard";

export const routes = [
    {
        path: '/register',
        Component: RegisterContainer,
        role: ['ROLE_MODERATOR']
    },
    {
        path: '/profile',
        Component: ProfileContainer,
        role: ['ROLE_USER']
    },
    {
        path: '/user',
        Component: UserContent,
        role: ['ROLE_USER']
    },
    {
        path: '/news',
        Component: NewsBoard,
        role: ['ROLE_USER']
    },
]

export const loginRoute = {
    path: '/login',
    Component: LoginContainer,
    role: ['ROLE_NONE']
}