package com.jwd.repository.auth;

import com.jwd.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ILoginRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = ?1 and u.endDate is null")
    User findByEmail(String email);

    //boolean isExistsByEmail(String email);
}
