package ru.project.fitstyle.models.user;

import ru.project.fitstyle.models.training.Training;
import ru.project.fitstyle.models.subscription.Subscription;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username should not be blank")
    @Size(max = 20, message = "username size should be less or equal than 20 chars")
    private String username;

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
    @Size(max = 150, message = "address should be less or equal than 150 chars")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_training",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id"))
    private Set<Training> trainings = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn//(name = "subscription_id")
    private Subscription subscription;

    public User() {
    }

    public User(String username, String name, String surname, String patronymic,
                String email, String password, String age,
                String gender, Date birthdate, String telephone,
                String passport, String address) {
        this.username = username;
        this.name = name;
        this.surname=surname;
        this.patronymic=patronymic;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.passport = passport;
        this.address = address;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
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
        this.password = gender;
    }

    public java.sql.Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(java.sql.Date birthdate) {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

}
