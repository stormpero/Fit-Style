package ru.project.fitstyle.model.dto.user;

public class FitUserFullNameDto {
    private final Long id;

    private final String name;

    private final String surname;

    private final String patronymic;

    public FitUserFullNameDto(final Long id, final String name, final String surname, final String patronymic) {
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
