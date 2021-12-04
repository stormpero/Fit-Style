package ru.project.fitstyle.model.dto.user;

import java.util.Date;

public class FitUserDto {
    private final Long id;

    private final String email;

    private final String password;


    private final String name;

    private final String surname;

    private final String patronymic;


    private final String age;

    private final String gender;

    private final Date birthdate;

    private final String telephone;

    private final String passport;

    private final String address;

    private final String imgURL;

    private final Long balance;

    public FitUserDto(final Long id, final String email, final String password, final String name, final String surname, final String patronymic,
                      final String age, final String gender, final Date birthdate, final String telephone, final String passport, final String address,
                      final String imgURL, final Long balance) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.gender = gender;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.passport = passport;
        this.address = address;
        this.imgURL = imgURL;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public String getImgURL() {
        return imgURL;
    }

    public Long getBalance() {
        return balance;
    }
}
