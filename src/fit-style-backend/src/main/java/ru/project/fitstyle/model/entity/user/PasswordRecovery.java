package ru.project.fitstyle.model.entity.user;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "password_recovery",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class PasswordRecovery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "deadline", nullable = false)
    private Date deadline;

    public PasswordRecovery() {
    }

    public PasswordRecovery(String email, String code, Date deadline) {
        this.email = email;
        this.code = code;
        this.deadline = deadline;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
