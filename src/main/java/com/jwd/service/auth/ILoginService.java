package com.jwd.service.auth;

import com.jwd.model.auth.Login;
import com.jwd.model.auth.LoginResponse;
import com.jwd.model.auth.User;

public interface ILoginService {
    User findByEmail(String email);

    int updateRefreshTokenByEmail(String refreshToken, String email);

    LoginResponse login(Login loginForm);

    LoginResponse refreshToken();
}
