import React, { Component } from "react";
import NewsRow from "./NewsRow";
import UserService from "../../services/UserService";
import DateFormat from "../../services/utils/DateFormat";

export default class NewsBoard extends Component {

    state = {
        rowNews: [],
    }
    // news: [{Новость},{Новость},{Новость},{Новость},{Новость},{Новость}]
    // rowNews: [[{Новость},{Новость},{Новость}],[{Новость},{Новость},{Новость}]]
    componentDidMount() {
        UserService.getNews(1).then(
            response => {
                let rowNews = response.data.news;
                rowNews.map(value => value.dateTime = DateFormat.convertDataTimeToData(value.dateTime))
                rowNews = sliceArray(rowNews, 3);
                this.setState({
                    rowNews: rowNews
                })
            }
        );
    }

    render() {
        const rowNews = this.state.rowNews;
        return(
            <div className="d-flex justify-content-center">
                <div className="news-board">
                    {
                        rowNews.map((param, index) => <NewsRow key={index} news={param}/>)
                    }
                </div>
            </div>
        );
    }
}

function sliceArray(array, size){ // return arrays of roles, arrays length <= size
    return array.reduce((p,c)=>{
        if(p[p.length-1].length === size){
            p.push([]);
        }

        p[p.length-1].push(c);
        return p;
    }, [[]]);
}