package ru.project.fitstyle.controller.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotBlank(message = "email should not be blank")
    @Size(max=50, message = "email should be more or equal than 5 and less or equal than 50 characters")
    @Email(message = "email should have syntax like: email@email.com")
    private String email;

    @NotBlank(message = "password should not be blank")
    @Size(max=120, message = "password should be more or equal than 6 and less or equal than 120 characters")
    private String password;

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
}
