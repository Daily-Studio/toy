package org.dailystudio.sbs.repository;

import org.dailystudio.sbs.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByEmail(String email);
}



