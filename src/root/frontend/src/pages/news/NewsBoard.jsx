import React, {useEffect, useState} from "react";
import NewsRow from "./NewsRow";
import UserService from "../../services/UserService";
import DateFormat from "../../services/utils/DateFormat";
import ArrayHelper from "../../services/utils/ArrayHelper";

export const NewsBoard = () => {

    const [rowNews, setRowNews] = useState([]);
    const [rowNum, setRowNum] = useState(1);
    const [hasNews, setHasNews] = useState(true);

    useEffect(() => {
        const loadNews = () => {
            UserService.getNews(rowNum).then(
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
                {rowNews && rowNews.map((param, index) => <NewsRow key={index} news={param}/>)}
                {hasNews && <button onClick={() => setRowNum(rowNum + 1)}>Ещё</button> }
            </div>
        </div>
    );
}

