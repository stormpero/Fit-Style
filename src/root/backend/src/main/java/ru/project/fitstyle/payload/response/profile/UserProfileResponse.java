package ru.project.fitstyle.payload.response.profile;

import ru.project.fitstyle.model.dto.subscription.ESubsPlacementTime;
import ru.project.fitstyle.model.dto.user.FitUser;

import java.util.Date;

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

    private final int subscriptionValidityMonths;

    private final ESubsPlacementTime subscriptionPlacementTime;

    private final String subscriptionCost;

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
            this.subscriptionPlacementTime = fitUser.getSubscription().getSubscriptionType().getPlacementTime();
            this.subscriptionValidityMonths = fitUser.getSubscription().getSubscriptionType().getValidityMonths();
            this.subscriptionCost = fitUser.getSubscription().getSubscriptionType().getCost();
        }
        else {
            this.subscriptionPlacementTime = null;
            this.subscriptionCost = null;
            subscriptionValidityMonths = 0;
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

    public int getSubscriptionValidityMonths() {
        return subscriptionValidityMonths;
    }

    public ESubsPlacementTime getSubscriptionPlacementTime() {
        return subscriptionPlacementTime;
    }

    public String getSubscriptionCost() {
        return subscriptionCost;
    }
}
