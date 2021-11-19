import RegisterContainer from "../register/RegisterContainer";
import ProfileContainer from "../profile/ProfileContainer";
import UserContent from "../../components/UserContent";
import {NewsBoard} from "../news/NewsBoard";
import {
    URL_NEWS,
    URL_PROFILE,
    URL_REGISTER,
    URL_USER
} from "../../services/utils/consts/urlsPages";

export const routes = [
    {
        path: URL_REGISTER,
        Component: RegisterContainer,
        reqRole: "ROLE_MODERATOR"
    },
    {
        path: URL_PROFILE,
        Component: ProfileContainer,
        reqRole: "ROLE_USER"
    },
    {
        path: URL_USER,
        Component: UserContent,
        reqRole: "ROLE_USER"
    },
    {
        path: [URL_NEWS, '/'],
        Component: NewsBoard,
        reqRole: "ROLE_USER"
    },
]
