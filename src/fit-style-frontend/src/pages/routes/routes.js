import {
    URL_NEWS,
    URL_PROFILE,
    URL_REGISTER,
    URL_USER,
    URL_MAP,
    URL_INFO, URL_CALENDAR, URL_SCHEDULE
} from "../../config/consts/urlsPages";
import RegisterContainer from "../register/RegisterContainer";
import {ProfileContainer} from "../profile/ProfileContainer";
import {Map} from "../map/Map";
import {NewsBoard} from "../news/NewsBoard";
import {CalendarContainer} from "../calendar/CalendarContainer";
import {ScheduleContainer} from "../schedule/ScheduleContainer";
import {Info} from "../info/Info";
import {UsersContainer} from "../users/UsersContainer";

export const routes = [
    {
        path: URL_REGISTER,
        Component: RegisterContainer,
        reqRole: "ROLE_MODERATOR"
    },
    {
        path: URL_USER,
        Component: UsersContainer,
        reqRole: "ROLE_MODERATOR"
    },
    {
        path: URL_PROFILE,
        Component: ProfileContainer,
        reqRole: "ROLE_USER"
    },
    {
        path: URL_MAP,
        Component: Map,
        reqRole: "ROLE_USER"
    },
    {
        path: URL_INFO,
        Component: Info,
        reqRole: "ROLE_USER"
    },
    {
        path: URL_CALENDAR,
        Component: CalendarContainer,
        reqRole: "ROLE_USER"
    },
    {
        path: URL_SCHEDULE,
        Component: ScheduleContainer,
        reqRole: "ROLE_USER"
    },
    {
        path: [URL_NEWS, '/'],
        Component: NewsBoard,
        reqRole: "ROLE_USER"
    },
]
