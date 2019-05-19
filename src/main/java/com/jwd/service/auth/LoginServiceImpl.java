package com.jwd.service.auth;

import com.jwd.model.auth.User;
import com.jwd.repository.auth.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    ILoginRepository loginRepository;

    @Override
    public User findByEmail(String email) {
        return loginRepository.findByEmail(email);
    }
}
