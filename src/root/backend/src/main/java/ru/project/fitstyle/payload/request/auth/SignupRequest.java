package ru.project.fitstyle.payload.request.auth;

import java.sql.Date;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;


public class SignupRequest {

    @NotBlank(message = "name should not be blank")
    @Size(max = 20, message = "name size should be less or equal than 20 chars")
    private String name;

    @NotBlank(message = "surname should not be blank")
    @Size(max = 20, message = "surname size should be less or equal than 20 chars")
    private String surname;


    @NotBlank(message = "patronymic should not be blank")
    @Size(max = 20, message = "patronymic should be less or equal than 20 chars")
    private String patronymic;

    @NotBlank(message = "email should not be blank")
    @Size(max = 50, message = "email should be less or equal than 50 chars")
    @Email(message = "email should have syntax like: email@email.com ")
    private String email;

    @NotBlank(message = "password should not be blank")
    @Size(max = 120, message = "password should be less or equal than 120 chars")
    private String password;

    @NotBlank(message = "age should not be blank")
    @Size(max = 3, message = "age should be less or equal than 3 chars")
    private String age;

    @NotBlank(message = "gender should not be blank")
    @Size(max = 6, message = "gender should be less or equal than 6 chars")
    private String gender;

    private Date birthdate;

    @NotBlank(message = "telephone should not be blank")
    @Size(max = 20, message = "telephone should be less or equal than 20 chars")
    private String telephone;

    @NotBlank(message = "passport should not be blank")
    @Size(max = 16, message = "passport should be less or equal than 16 chars")
    private String passport;

    @NotBlank(message = "address should not be blank")
    @Size(max = 40, message = "address should be less or equal than 40 chars")
    private String address;

    private Set<String> role;



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

    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
