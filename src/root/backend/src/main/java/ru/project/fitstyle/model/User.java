package ru.project.fitstyle.model;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_cardid")
    private long cardId;

    @NotBlank
    @Size(max = 20)
    @Column(name="user_name")
    private String name;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name="user_email")
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(name="user_password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_cardId"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String name, String email, String password, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
    //    @Column(name="user_passport_number")
//    private int passportNumber;
//    @Column(name="user_paycard_number")
//    private int payCardNumber;
//    @Column(name="user_firstname")
//    private String firstname;
//    @Column(name="user_lastname")
//    private String lastname;
//    @Column(name="user_gender")
//    private String gender;
//    @Column(name="user_birthdaydate")
//    private java.sql.Date birthdayDate;
//    @Column(name="user_specialrequests")
//    private String specialRequests;
