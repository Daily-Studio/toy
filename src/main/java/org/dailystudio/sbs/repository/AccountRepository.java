package org.dailystudio.sbs.repository;

import org.dailystudio.sbs.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    //null을 좀더 안전하게 다뤄줌
    Optional<Account> findByEmail(String email);
    //Optional<List<Account>> findALLS();
}



