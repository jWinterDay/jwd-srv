package com.jwd.service.auth;

import com.jwd.model.auth.User;

public interface ILoginService {
    User findByEmail(String email);
}
