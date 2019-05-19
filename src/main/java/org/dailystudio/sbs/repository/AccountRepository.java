package org.dailystudio.sbs.repository;

import org.dailystudio.sbs.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
    //Optional을 사용하는게 좋대. .orElseThrow(RuntimeExceoption::new)
}
