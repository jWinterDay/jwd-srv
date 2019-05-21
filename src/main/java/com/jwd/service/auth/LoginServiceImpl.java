package com.jwd.service.auth;

import com.jwd.exception.CustomException;
import com.jwd.model.auth.Login;
import com.jwd.model.auth.LoginResponse;
import com.jwd.model.auth.User;
import com.jwd.repository.auth.ILoginRepository;
import com.jwd.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    ILoginRepository loginRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Value("${security.password.secret-key}")
    private String passwordSecretKey = "6d2ba458bf1d45bdb19eb74547eae9bd";

    @Override
    public User findByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    @Override
    public int updateRefreshTokenByEmail(String refreshToken, String email) {
        return loginRepository.updateRefreshTokenByEmail(refreshToken, email);
    }

    @Override
    public LoginResponse login(Login loginForm) {
        User user = loginRepository.findByEmail(loginForm.getEmail());

        if (user == null) {
            throw new CustomException("User not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(passwordSecretKey, 10000, 128);

        String dbHash = user.getPasswordHash();
        boolean isMatches = encoder.matches(loginForm.getPassword(), dbHash);

        if (!isMatches) {
            throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        String token = jwtTokenProvider.createToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        //update refresh token in database
        loginRepository.updateRefreshTokenByEmail(refreshToken, loginForm.getEmail());

        LoginResponse loginResponse = new LoginResponse(token, refreshToken);

        return loginResponse;
    }

    @Override
    public LoginResponse refreshToken() {
        /*User user = loginRepository.findByEmail(loginForm.getEmail());

        if (user == null) {
            throw new CustomException("Invalid user credentials", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtTokenProvider.createToken(loginForm.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(loginForm.getEmail());*/

        LoginResponse loginResponse = new LoginResponse();

        return loginResponse;
    }
}
