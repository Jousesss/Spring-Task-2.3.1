package ru.alkey.webapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_list", schema = "public", catalog = "postgres")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name shouldn't be empty!")
    @Size(min = 2, max = 30, message = "Size of name must be (min = 2, max = 30) symbols!")
    @Column(name = "user_name", nullable = false)
    private String name;

    @Min(value = 0, message = "Age cannot be less than 0")
    @Max(value = 125, message = "Age cannot be more than 125")
    @NotNull(message = "Age cannot be empty")
    @Column(name = "user_age", nullable = false)
    private Integer age;

    @NotEmpty(message = "Email cannot be empty!")
    @Email(message = "Your email doesn't seem like real email!")
    @Size(max = 100, message = "Email cannot be more than 100 characters!")
    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    public User() {
    }

    public User(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
