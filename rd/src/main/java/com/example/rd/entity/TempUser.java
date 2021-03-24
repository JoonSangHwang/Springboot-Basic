package com.example.rd.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempUser implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Long id;
    @Column private String username;
    @Column private String password;

    @Builder
    public TempUser(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
