package com.example.intechchat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGSERIAL")
    private Long id;

    @NotNull
    @NotBlank(message = "Username can't be empty")
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @NotBlank(message = "Password is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
