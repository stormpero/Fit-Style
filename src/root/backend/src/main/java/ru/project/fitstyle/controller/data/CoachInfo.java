package ru.project.fitstyle.controller.data;

public class CoachInfo {
    private final Long id;

    private final String name;

    private final String surname;

    private final String patronymic;

    public CoachInfo(Long id, String name, String surname, String patronymic) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }
}
