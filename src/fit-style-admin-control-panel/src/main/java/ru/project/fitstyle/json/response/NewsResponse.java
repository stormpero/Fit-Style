package ru.project.fitstyle.json.response;

import java.util.List;

public class NewsResponse {

    private List<News> news;

    public NewsResponse(List<News> news) {
        this.news = news;
    }

    public NewsResponse() {
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public static class News {
        private Long id;
        private String header;
        private String content;
        private String dateTime;
        private String imgURL;

        public News(Long id, String header, String content, String dateTime, String imgURL) {
            this.id = id;
            this.header = header;
            this.content = content;
            this.dateTime = dateTime;
            this.imgURL = imgURL;
        }

        public News() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }
    }
}
