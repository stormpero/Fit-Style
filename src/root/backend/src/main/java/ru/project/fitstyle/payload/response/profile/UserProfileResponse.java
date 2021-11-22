package ru.project.fitstyle.payload.response.profile;

import ru.project.fitstyle.model.user.FitUser;

import java.sql.Date;

public class UserProfileResponse {

    private final String name;

    private final String surname;

    private final String patronymic;

    private final String email;

    private final String age;

    private final String gender;

    private final Date birthdate;

    private final String telephone;

    private final String passport;

    private final String address;

    public UserProfileResponse(
                               String name,
                               String surname,
                               String patronymic,
                               String email,
                               String age,
                               String gender,
                               Date birthdate,
                               String telephone,
                               String passport,
                               String address) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.passport = passport;
        this.address = address;
    }

    public UserProfileResponse(FitUser fitUser) {
        this.name = fitUser.getName();
        this.surname = fitUser.getSurname();
        this.patronymic = fitUser.getPatronymic();
        this.email = fitUser.getEmail();
        this.age = fitUser.getAge();
        this.gender = fitUser.getGender();
        this.birthdate = fitUser.getBirthdate();
        this.telephone = fitUser.getTelephone();
        this.passport = fitUser.getPassport();
        this.address = fitUser.getAddress();
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

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassport() {
        return passport;
    }

    public String getAddress() {
        return address;
    }
}
