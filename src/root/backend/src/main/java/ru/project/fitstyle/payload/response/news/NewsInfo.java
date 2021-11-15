package ru.project.fitstyle.payload.response.news;

public class NewsInfo {
    private final long numberOfPages;
    private final long numberOfNews;
    private final int numberOfNewsInOnePage;

    public NewsInfo(long numberOfPages, long numberOfNews, int numberOfNewsInOnePage) {
        this.numberOfPages = numberOfPages;
        this.numberOfNews = numberOfNews;
        this.numberOfNewsInOnePage = numberOfNewsInOnePage;
    }

    public long getNumberOfPages() {
        return numberOfPages;
    }

    public long getNumberOfNews() {
        return numberOfNews;
    }

    public int getNumberOfNewsInOnePage() {
        return numberOfNewsInOnePage;
    }
}
