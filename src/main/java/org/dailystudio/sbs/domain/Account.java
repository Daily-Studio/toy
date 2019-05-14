package org.dailystudio.sbs.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "account")
@Getter
public class Account {

    @Id //프라이머리 키
    @GeneratedValue(strategy = GenerationType.AUTO) //전략을 정해주는거
    @Column(name = "account_id") //데이터베이스에 저장되는이름
    private Long id;

    @Column(name = "account_email", unique = true, nullable =  false)
    private String email;

    @Column(name = "account_name", nullable =  false)
    private String name;

    @Column(name = "account_pass", nullable =  false)
    private String pass;

    public Account(String email, String name, String pass) {
        this.email = email;
        this.name = name;
        this.pass = pass;
    }



}
