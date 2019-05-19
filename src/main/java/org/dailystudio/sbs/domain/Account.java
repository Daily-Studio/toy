package org.dailystudio.sbs.domain;

import lombok.Getter;
import org.dailystudio.sbs.dto.AccountInfo;

import javax.persistence.*;

@Entity
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID", nullable = false)
    private long id;

    @Column(name = "ACCOUNT_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "ACCOUNT_PASSWORD", nullable = false)
    private String password;

    @Column(name = "ACCOUNT_NAME", nullable = false)
    private String name;

    public Account(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Account() { }

    public AccountInfo toAccountInfo() {
        return new AccountInfo(this.email, this.name);
    }

    public void changeUserName(String name) {
        this.name = name;
    }
}
