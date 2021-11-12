package ru.project.fitstyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.fitstyle.models.News;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAll();

    Optional<News> findById(Long id);
}
