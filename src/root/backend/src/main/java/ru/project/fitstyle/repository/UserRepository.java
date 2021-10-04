package ru.project.fitstyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.project.fitstyle.model.User;

@Repository //Для БД
public interface UserRepository extends JpaRepository <User, Long> {
}
