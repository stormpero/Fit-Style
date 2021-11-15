package ru.project.fitstyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.models.news.News;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAll();

    Optional<News> findById(Long id);
}
