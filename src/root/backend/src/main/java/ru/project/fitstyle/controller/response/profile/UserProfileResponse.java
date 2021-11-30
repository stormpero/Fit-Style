package ru.project.fitstyle.controller.response.profile;

import ru.project.fitstyle.controller.data.SubscriptionInfo;

import java.util.Date;

public class UserProfileResponse {

    private final Long id;

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

    private final Long balance;

    private final SubscriptionInfo subscriptionInfo;

    public UserProfileResponse(Long id, String name, String surname, String patronymic,
                               String email, String age, String gender, Date birthdate,
                               String telephone, String passport, String address,
                               Long balance, SubscriptionInfo subscriptionInfo) {
        this.id = id;
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
        this.balance = balance;
        this.subscriptionInfo = subscriptionInfo;
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

    public Long getBalance() {
        return balance;
    }

    public SubscriptionInfo getSubscriptionInfo() {
        return subscriptionInfo;
    }
}
