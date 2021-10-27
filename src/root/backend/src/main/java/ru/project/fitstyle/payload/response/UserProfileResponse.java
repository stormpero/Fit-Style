package ru.project.fitstyle.payload.response;

import ru.project.fitstyle.models.User;

public class UserProfileResponse {
    private String username;

    private String surname;

    private String patronymic;

    private String email;

    private String age;

    private String gender;

    private java.sql.Date birthdate;

    private String telephone;

    private String passport;

    private String address;

    public UserProfileResponse(User user)
    {
        username=user.getUsername();
        surname=user.getSurname();
        patronymic=user.getPatronymic();
        email=user.getEmail();
        age=user.getAge();
        gender=user.getGender();
        birthdate=user.getBirthdate();
        telephone=user.getTelephone();
        passport=user.getPassport();
        address=user.getAddress();
    }
}
