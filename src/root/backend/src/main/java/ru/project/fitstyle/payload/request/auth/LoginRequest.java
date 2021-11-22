package ru.project.fitstyle.payload.request.auth;

import ru.project.fitstyle.model.subscription.Subscription;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotBlank(message = "email should not be blank")
    @Size(min=10, max=50, message = "email should be more than 10 and less than 50 characters")
    @Email(message = "It should be email!")
    private String email;

    @NotBlank(message = "password should not be blank")
    @Size(min=6, max=120, message = "password should be more than 6 and less than 12- characters")
    private String password;

    private Subscription subscription;

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

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
