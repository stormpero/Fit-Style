import React, {useEffect, useState} from "react";
import NewsRow from "./NewsRow";
import DateFormat from "../../services/utils/DateFormat";
import ArrayHelper from "../../services/utils/ArrayHelper";
import Modal from "../../components/modal/Modal";
import NewsFormContainer from "./form/NewsFormContainer";
import NewsService from "../../services/api/NewsService";
import LStorageUser from "../../services/LStorageUser";

export const NewsBoard = () => {

    const isAdmin = LStorageUser.getUser().roles.includes("ROLE_MODERATOR");

    const [modalActive, setModalActive] = useState(false);

    const [rowNews, setRowNews] = useState([]);
    const [rowNum, setRowNum] = useState(1);
    const [hasNews, setHasNews] = useState(true);

    useEffect(() => {
        const loadNews = () => {
            NewsService.getNews(rowNum).then(
                response => {
                    let rowNewsData = response.data.news;
                    rowNewsData.map(value => value.dateTime = DateFormat.convertDataTimeToData(value.dateTime))
                    rowNewsData = ArrayHelper.sliceArray(rowNewsData, 3);
                    setRowNews(prevNews => prevNews.concat(rowNewsData))
                },
                error => {
                    if (error.response.data.statusCode === 400 &&
                        error.response.data.message === "Failed. Page not found!") {
                        setHasNews(false);
                    }
                }
            )}
        loadNews();
    },[rowNum])

    return(
        <div className="d-flex justify-content-center">
            <div className="news-board">
                {isAdmin && <button className="btn-primary" onClick={() => setModalActive(true)}>Добавить Новость</button>}
                {rowNews && rowNews.map((param, index) => <NewsRow key={index} news={param}/>)}
                {hasNews && <button className="btn-primary" onClick={() => setRowNum(rowNum + 1)}>Ещё</button> }
            </div>
            { isAdmin &&
                <Modal active={modalActive} setActive={setModalActive}>
                    <NewsFormContainer setActive={setModalActive}/>
                </Modal>
            }
        </div>
    );
}

