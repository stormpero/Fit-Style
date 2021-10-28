package ru.project.fitstyle.payload.request.profile;

import javax.validation.constraints.Min;

public class UserRequest {
    @Min(value=0, message = "Id should be greater than 0")
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRequest(Long id) {
        this.id = id;
    }

    public UserRequest()
    {

    }
}
