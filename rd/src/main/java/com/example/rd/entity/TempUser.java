package com.example.rd.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempUser implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Long id;
    @Column private String username;
    @Column private String password;

    public TempUser() {

    }
}
