import React, {Component} from 'react';
import NewsForm from "./NewsForm";
import isEmpty from "validator/es/lib/isEmpty";
import NewsService from "../../../services/api/NewsApi";
import ToastMessages from "../../../components/toastmessages/ToastMessages";
import {TOP_RIGHT} from "../../../config/consts/ToastPosition";

class NewsFormContainer extends Component {

    state = {
        newsData: {
            header: "",
            content: "",
            dateTime: ""
        },
        message: ""
    }

    handleInputChange = (event) => {
        const {name, value} = event.target;

        this.setState({
            newsData: {
                ...this.state.newsData,
                [name]: value
            }
        });
    }

    handleAdd = async (event) => {
        event.preventDefault();

        if (isEmpty(this.state.newsData.header) ||
            isEmpty(this.state.newsData.content)) {
            const errorMsg = "Заполните поля";
            this.setState({
                message: errorMsg
            });
            return;
        }

        if (this.state.newsData.content.length > 1500) {
            this.setState({
                message: "В описании должно быть меньше 1500 символов"
            });
            return;
        }

        if (this.state.newsData.header.length > 50) {
            this.setState({
                message: "В заголовке должно быть меньше 50 символов"
            });
            return;
        }


        const date = new Date();

        await this.setState({
            newsData: {
                ...this.state.newsData,
                dateTime: date
            }
        });

        NewsService.addNews(this.state.newsData).then(
            response => {
                this.setState({
                    newsData: {
                        header: "",
                        content: "",
                        dateTime: "",
                    },
                })
                ToastMessages.success("Новость успешно добавлена!", TOP_RIGHT);
                this.props.setActive(false);
                this.props.updateNews();

            }
        ).catch(
            error => {
                let errorMsg = error.response?.data?.message || error.message;

                this.setState({
                    message: errorMsg
                });
            }
        )
    }

    render() {
        return (
            <NewsForm
            handleFunc={{
                add: this.handleAdd,
                input: this.handleInputChange,
            }}
            value={this.state.newsData}
            message={this.state.message}
            />
        );
    }
}

export default NewsFormContainer;