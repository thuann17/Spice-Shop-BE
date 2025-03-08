package com.system.spice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "username", nullable = false, length = 30)
    private String username;

    @Size(max = 50)

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 12)
    @Column(name = "number_phone", length = 12)
    private String numberPhone;

    @Size(max = 225)
    @NotNull
    @Column(name = "password", nullable = false, length = 225)
    private String password;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "role_id")
    private Role role;

}