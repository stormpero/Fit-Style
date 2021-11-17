import RegisterContainer from "../register/RegisterContainer";
import ProfileContainer from "../profile/ProfileContainer";
import UserContent from "../../components/UserContent";
import NewsBoard from "../news/NewsBoard";
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
        role: ['ROLE_MODERATOR']
    },
    {
        path: URL_PROFILE,
        Component: ProfileContainer,
        role: ['ROLE_USER']
    },
    {
        path: URL_USER,
        Component: UserContent,
        role: ['ROLE_USER']
    },
    {
        path: [URL_NEWS, '/'],
        Component: NewsBoard,
        role: ['ROLE_USER']
    },
]
