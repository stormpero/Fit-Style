import React, {useEffect, useState} from "react";
import NewsRow from "./NewsRow";
import DateFormat from "../../services/utils/DateFormat";
import ArrayHelper from "../../services/utils/ArrayHelper";
import Modal from "../../components/modal/Modal";
import NewsFormContainer from "./form/NewsFormContainer";
import NewsService from "../../services/api/NewsService";
import LStorageUser from "../../services/LStorageUser";
import "./NewsBoard.css";
import ToastMessages from "../../services/utils/ToastMessages";
import {TOP_RIGHT} from "../../services/utils/consts/ToastPosition";
import arrow from "../../assets/arrow.png";

export const NewsBoard = () => {

    const isAdmin = LStorageUser.getUser().roles.includes("ROLE_MODERATOR");

    const [modalActive, setModalActive] = useState(false);
    const [deleteActive, setDeleteActive] = useState(false);

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
                    setHasNews(false);
                    if (error?.response?.data?.statusCode === 400 &&
                        error.response.data?.message === "Failed. Page not found!") {
                    }
                }
            )}
        loadNews();
    },[rowNum])

    const deleteNews = (id) => {
        NewsService.deleteNews(id).then(
            response => {
                window.location.reload();
                ToastMessages.success("Новость удалена!", TOP_RIGHT);
            },
            error => {
                console.log(error.response)
            }
        )
    }

    return(
        <div className="d-flex justify-content-center">
            <div className="news-board">
                <div className="d-flex justify-content-around">
                    {isAdmin &&
                        <button className={deleteActive ? 'select' : 'noselect'}
                                onClick={() => setDeleteActive((prev) => !prev)}>
                            <span className='text'>Удалить</span>
                            <span className="icon">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                    <path d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"/>
                                </svg>
                            </span>
                        </button>
                    }
                    <h1 className="map-title">Новостная лента</h1>
                    {isAdmin &&
                        <button className="add-news" onClick={() => setModalActive(true)}>
                            <span className='text'>Добавить</span>
                            <span className="icon">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" transform="rotate(45)" viewBox="0 0 24 24">
                                    <path d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"/>
                                </svg>
                            </span>
                        </button>
                    }
                </div>
                {rowNews && rowNews.map((param, index) => <NewsRow key={index} news={param} delete={deleteNews} deleteMode={deleteActive}/>)}
                {hasNews &&
                    <div className="d-flex justify-content-center">
                        <div>
                            <button className="news-more" onClick={() => setRowNum(rowNum + 1)}><img className="more-news-icon" src={arrow} alt="Fit-Style"/></button>
                        </div>
                    </div>
                }
                <br/>
                <br/>
                <br/>
                <br/>
            </div>
            {isAdmin &&
                <Modal active={modalActive} setActive={setModalActive}>
                    <NewsFormContainer setActive={setModalActive}/>
                </Modal>
            }
        </div>
    );
}

