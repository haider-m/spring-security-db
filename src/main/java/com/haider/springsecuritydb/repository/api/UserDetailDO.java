package com.haider.springsecuritydb.repository.api;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "user_details", schema = "myapp_db")
public class UserDetailDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 25)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String password;

    @ColumnDefault("1")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}