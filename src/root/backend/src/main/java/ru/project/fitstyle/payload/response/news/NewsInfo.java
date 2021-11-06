package ru.project.fitstyle.payload.response.news;

public class NewsInfo {
    long numberOfPages;
    long numberOfNews;
    int numberOfNewsInOnePage;

    public NewsInfo(long numberOfPages, long numberOfNews, int numberOfNewsInOnePage) {
        this.numberOfPages = numberOfPages;
        this.numberOfNews = numberOfNews;
        this.numberOfNewsInOnePage = numberOfNewsInOnePage;
    }

    public NewsInfo() {
    }

    public long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public long getNumberOfNews() {
        return numberOfNews;
    }

    public void setNumberOfNews(long numberOfNews) {
        this.numberOfNews = numberOfNews;
    }

    public int getNumberOfNewsInOnePage() {
        return numberOfNewsInOnePage;
    }

    public void setNumberOfNewsInOnePage(int numberOfNewsInOnePage) {
        this.numberOfNewsInOnePage = numberOfNewsInOnePage;
    }
}
