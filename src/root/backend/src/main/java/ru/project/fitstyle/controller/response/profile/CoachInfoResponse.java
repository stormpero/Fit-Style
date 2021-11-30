package ru.project.fitstyle.controller.response.profile;

public class CoachInfoResponse {
    private final String name;

    private final String surname;

    private final String patronymic;

    public CoachInfoResponse(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
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
