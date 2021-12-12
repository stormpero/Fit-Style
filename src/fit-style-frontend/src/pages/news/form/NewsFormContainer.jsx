import React, {Component} from 'react';
import NewsForm from "./NewsForm";
import isEmpty from "validator/es/lib/isEmpty";
import NewsService from "../../../services/api/NewsApi";
import ToastMessages from "../../../components/toastmessages/ToastMessages";
import {TOP_RIGHT} from "../../../config/consts/ToastPosition";
import ImgConvert from "../../../utils/RequestCreate";
import trim from "validator/es/lib/trim";

class NewsFormContainer extends Component {

    state = {
        newsData: {
            header: "",
            content: "",
            dateTime: "",
            imageData: null,
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

    handleImgInputChange = (event) => {
        const {files} = event.target;
        this.setState({
            newsData: {
                ...this.state.newsData,
                imageData: files[0]
            }
        });
    }

    handleAdd = async (event) => {
        event.preventDefault();

        if (isEmpty(trim(this.state.newsData.header)) ||
            isEmpty(trim(this.state.newsData.content))) {
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

        await this.setState({
            newsData: {
                ...this.state.newsData,
                dateTime: new Date()
            }
        });

        const data = ImgConvert.createRequestData(this.state.newsData, this.state.newsData.imageData);
        NewsService.addNews(data).then(
            response => {
                this.setState({
                    newsData: {
                        header: "",
                        content: "",
                        dateTime: "",
                        imageData: null,
                    },
                    message: "",
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
                inputImg: this.handleImgInputChange,
            }}
            value={this.state.newsData}
            message={this.state.message}
            />
        );
    }
}

export default NewsFormContainer;