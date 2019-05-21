package com.jwd.repository.auth;

import com.jwd.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ILoginRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = ?1 and u.endDate is null")
    User findByEmail(String email);

    @Transactional(timeout = 10)
    @Modifying
    @Query("update User u" +
           "   set u.refreshToken = ?1" +
           " where u.email = ?2")
    int updateRefreshTokenByEmail(String refreshToken, String email);

    //boolean isExistsByEmail(String email);
}
