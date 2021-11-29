package ru.project.fitstyle.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.news.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
