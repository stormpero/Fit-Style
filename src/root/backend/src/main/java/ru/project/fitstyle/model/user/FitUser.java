package ru.project.fitstyle.model.user;

import ru.project.fitstyle.model.subscription.Subscription;
import ru.project.fitstyle.model.training.GroupTraining;
import ru.project.fitstyle.model.training.PersonalTraining;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="fit_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class FitUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false, updatable = false, unique = true)
    private Long id;

    @Email(message = "email should have syntax like: email@email.com ")
    @Column(name = "email", length = 50,
            nullable = false, unique = true)
    private String email;



    @Column(name = "password", length = 120,
            nullable = false)
    private String password;



    @Column(name = "name", length = 20,
            nullable = false)
    private String name;

    @Column(name = "surname", length = 20,
            nullable = false)
    private String surname;

    @Column(name = "patronymic", length = 20,
            nullable = false)
    private String patronymic;



    @Column(name = "age", length = 3,
            nullable = false)
    private String age;

    @Column(name = "gender", length = 6,
            nullable = false)
    private String gender;

    @Column(name = "birthdate",
            nullable = false)
    private Date birthdate;

    @Size(max = 20, message = "telephone should be less or equal than 20 chars")
    @Column(name = "telephone", length = 20,
            nullable = false)
    private String telephone;

    @Size(max = 16, message = "passport should be less or equal than 16 chars")
    @Column(name = "passport", length = 16,
            nullable = false)
    private String passport;

    @Size(max = 150, message = "address should be less or equal than 150 chars")
    @Column(name = "address", length = 150,
            nullable = false)
    private String address;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fit_user_roles",
            joinColumns = @JoinColumn(name = "fit_user_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fit_user_group_training",
            joinColumns = @JoinColumn(name = "fit_user_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "group_training_id", referencedColumnName = "id", nullable = false))
    private Set<GroupTraining> groupTrainings = new HashSet<>();

    @OneToMany(mappedBy = "fitUser", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<PersonalTraining> personalTrainings = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private Subscription subscription;

    public FitUser() {
    }

    public FitUser(String name, String surname, String patronymic,
                   String email, String password, String age,
                   String gender, Date birthdate, String telephone,
                   String passport, String address) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<GroupTraining> getGroupTrainings() {
        return groupTrainings;
    }

    public void setGroupTrainings(Set<GroupTraining> groupTrainings) {
        this.groupTrainings = groupTrainings;
    }

    public Set<PersonalTraining> getPersonalTrainings() {
        return personalTrainings;
    }

    public void setPersonalTrainings(Set<PersonalTraining> personalTrainings) {
        this.personalTrainings = personalTrainings;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
