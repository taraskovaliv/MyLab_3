package com.kovaliv.lab3.security.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
