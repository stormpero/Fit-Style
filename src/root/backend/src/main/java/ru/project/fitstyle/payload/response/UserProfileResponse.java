package ru.project.fitstyle.payload.response;

import ru.project.fitstyle.models.User;

import java.sql.Date;

public class UserProfileResponse {

    private Long id;

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

    public UserProfileResponse(Long id,
                               String username,
                               String surname,
                               String patronymic,
                               String email,
                               String age,
                               String gender,
                               Date birthdate,
                               String telephone,
                               String passport,
                               String address) {
        this.id = id;
        this.username = username;
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

    public UserProfileResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
