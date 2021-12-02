package ru.project.fitstyle.model.dao;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.controller.response.news.NewsInfo;
import ru.project.fitstyle.model.entity.news.News;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query(value = "select " +
            "new ru.project.fitstyle.controller.response.news.NewsInfo(v.id, v.header, v.content, v.dateTime, v.imgURL) " +
            "from News v")
    Optional<List<NewsInfo>> findNewsPage(Pageable page);
}
