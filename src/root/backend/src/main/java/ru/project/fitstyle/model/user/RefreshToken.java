package ru.project.fitstyle.model.user;

import java.time.Instant;

import javax.persistence.*;


@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",
            nullable = false, updatable = false, unique = true)
    private long id;

    @Column(name = "token", length = 200,
            nullable = false, updatable = false, unique = true)
    private String token;

    @Column(name = "expiry_date",
            nullable = false, updatable = false)
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "fit_user_id", referencedColumnName = "id",
            nullable = false, updatable = false, unique = true)
    private FitUser fitUser;

    public RefreshToken() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FitUser getUser() {
        return fitUser;
    }

    public void setUser(FitUser fitUser) {
        this.fitUser = fitUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}