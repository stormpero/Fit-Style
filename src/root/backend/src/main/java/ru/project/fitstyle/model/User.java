package ru.project.fitstyle.model;
import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long userCardLogin; //id

    @Column(name="user_password")
    private String userPassword;
    @Column(name="user_passport_number")
    private int userPassportNumber;
    @Column(name="user_paycard_number")
    private int userPayCardNumber;
    @Column(name="user_firstname")
    private String userFirstname;
    @Column(name="user_lastname")
    private String userLastname;
    @Column(name="user_gender")
    private String userGender;
    @Column(name="user_birthdaydate")
    private java.sql.Date userBirthdayDate;
    @Column(name="user_specialrequests")
    private String userSpecialRequests;

    public User() {
    }

    public User(String userPassword, int userPassportNumber, int userPayCardNumber, String userFirstname, String userLastname, String userGender, java.sql.Date userBirthdayDate, String userSpecialRequests) {
        this.userPassword = userPassword;
        this.userPassportNumber = userPassportNumber;
        this.userPayCardNumber = userPayCardNumber;
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.userGender = userGender;
        this.userBirthdayDate = userBirthdayDate;
        this.userSpecialRequests = userSpecialRequests;
    }



    public long getUserCardLogin() {
        return userCardLogin;
    }

    public void setUserCardLogin(long userCardLogin) {
        this.userCardLogin = userCardLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserPassportNumber() {
        return userPassportNumber;
    }

    public void setUserPassportNumber(int userPassportNumber) {
        this.userPassportNumber = userPassportNumber;
    }

    public int getUserPayCardNumber() {
        return userPayCardNumber;
    }

    public void setUserPayCardNumber(int userPayCardNumber) {
        this.userPayCardNumber = userPayCardNumber;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public java.sql.Date getUserBirthdayDate() {
        return userBirthdayDate;
    }

    public void setUserBirthdayDate(java.sql.Date userBirthdayDate) {
        this.userBirthdayDate = userBirthdayDate;
    }

    public String getUserSpecialRequests() {
        return userSpecialRequests;
    }

    public void setUserSpecialRequests(String userSpecialRequests) {
        this.userSpecialRequests = userSpecialRequests;
    }

}






