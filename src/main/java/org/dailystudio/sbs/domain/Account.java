package org.dailystudio.sbs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// entity class 라고 함 이걸
@NoArgsConstructor
@Table(name = "ACCOUNT")
@Entity
@Getter
public class Account {

    @Id //primary key 를 의미함
    @GeneratedValue(strategy = GenerationType.AUTO) //id 값은 자동으로 생성해 되야하니 그걸 해주는거 GenerationType.AUTO 를 하면 디비에 알맞게 지정해준대
    @Column(name = "ACCOUNT_ID")
    private long id;

    @Column(name = "ACCOUNT_EMAIL", nullable = false, unique = true)
    //unique = true 라는 조건을 주면 동일한 이메일을 가진 애들이 디비에 들어가는걸 방지하겠다는거임.
    //프라이머리 키는 아니지만 그래도 중복값은 존재하면 안되므로.
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
}

//내장디비 들어가는 주소
//

