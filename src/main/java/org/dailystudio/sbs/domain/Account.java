package org.dailystudio.sbs.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private int id;

    @Column(name="ACCOUNT_EMAIL", unique = true)
    private String email;

    @Column (name ="ACCOUNT_PASSWORD")
    private String password;

    @Column(name="ACCOUNT_NAME")
    private String name;

    @Builder
    public Account(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
