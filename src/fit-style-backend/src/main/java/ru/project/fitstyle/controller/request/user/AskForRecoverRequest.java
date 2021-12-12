package ru.project.fitstyle.controller.request.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AskForRecoverRequest {
    @Size(max=50, message = "email should be more or equal than 5 and less or equal than 50 characters")
    @Email(message = "email should have syntax like: email@email.com")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
