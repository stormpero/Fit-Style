package ru.project.fitstyle.controller.request.auth;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;


public class SignupRequest {
    @NotBlank(message = "name should not be blank")
    @Size(min = 2, max = 20, message = "name should be more or equal than 2 and less or equal than 20 characters")
    private String name;

    @NotBlank(message = "surname should not be blank")
    @Size(min = 2, max = 20, message = "surname should be more or equal than 2 and less or equal than 20 characters")
    private String surname;

    @NotBlank(message = "patronymic should not be blank")
    @Size(min = 2, max = 20, message = "patronymic should be more or equal than 2 and less or equal than 20 characters")
    private String patronymic;

    @NotBlank(message = "email should not be blank")
    @Size(min = 5, max = 50, message = "email should be more or equal than 5 and less or equal than 50 characters")
    @Email(message = "email should have syntax like: email@email.com")
    private String email;

    @NotBlank(message = "password should not be blank")
    @Size(min = 6, max = 120, message = "password should be more or equal than 6 and less or equal than 120 characters")
    private String password;

    @NotBlank(message = "age should not be blank")
    @Size(min = 1, max = 3, message = "age should be more or equal than 1 and less or equal than 3 characters")
    private String age;

    @NotBlank(message = "gender should not be blank")
    @Size(min = 1, max = 6, message = "gender should be more or equal than 1 and less or equal than 6 characters")
    private String gender;

    private Date birthdate;

    @NotBlank(message = "telephone should not be blank")
    @Size(min = 5, max = 20, message = "telephone should be more or equal than 5 and less or equal than 20 characters")
    private String telephone;

    @NotBlank(message = "passport should not be blank")
    @Size(min = 5, max = 16, message = "passport should be more or equal than 5 and less or equal than 16 characters")
    private String passport;

    @NotBlank(message = "address should not be blank")
    @Size(min = 5, max = 150, message = "address should be more or equal than 5 and less or equal than 150 characters")
    private String address;

    private Set<String> roles;

    @NotNull(message = "subscriptionTypeId should not be blank")
    private Long subscriptionTypeId;

    @NotBlank(message = "contractNumber should not be blank")
    @Size(min = 1, max = 15, message = "contractNumber should be more or equal than 1 and less or equal than 15 characters")
    private String contractNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<String> getRoles() {
      return this.roles;
    }
    
    public void setRoles(Set<String> roles) {
      this.roles = roles;
    }

    public Long getSubscriptionTypeId() {
        return subscriptionTypeId;
    }

    public void setSubscriptionTypeId(Long subscriptionTypeId) {
        this.subscriptionTypeId = subscriptionTypeId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
}
