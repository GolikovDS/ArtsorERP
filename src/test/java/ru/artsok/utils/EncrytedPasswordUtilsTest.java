package ru.artsok.utils;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class EncrytedPasswordUtilsTest {

    @Test
    public void encrytePassword() {
        String password = "22";
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(password);
        System.out.println("Encryted Password: " + encrytedPassword);
        assertTrue(new BCryptPasswordEncoder().matches("22", encrytedPassword));
    }
}