package ru.project.fitstyle.payload.response.profile;

import ru.project.fitstyle.model.subscription.ESubsPlacementTime;
import ru.project.fitstyle.model.subscription.Subscription;
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

    private final Date validity;

    private final ESubsPlacementTime subsPlacementTime;

    private final String cost;

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
                               String address,
                               ESubsPlacementTime subsPlacementTime,
                               Date validity,
                               String cost) {
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
        this.subsPlacementTime = subsPlacementTime;
        this.validity = validity;
        this.cost = cost;
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
        if(fitUser.getSubscription() != null) {
            this.subsPlacementTime = fitUser.getSubscription().getSubscriptionType().getPlacementTime();
            this.validity = fitUser.getSubscription().getSubscriptionType().getValidity();
            this.cost = fitUser.getSubscription().getSubscriptionType().getCost();
        }
        else {
            this.subsPlacementTime = null;
            this.validity = null;
            this.cost = null;
        }
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

    public Date getValidity() {
        return validity;
    }

    public ESubsPlacementTime getSubsPlacementTime() {
        return subsPlacementTime;
    }

    public String getCost() {
        return cost;
    }
}
