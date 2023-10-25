package com.mosbach.demo.data.api;

import java.util.List;

public interface UserManager {

    List<User> readAllUsers();
    User createUser(String firstName, String lastName, String userPassword, String email);
    User logUserIn(String email, String password);
    User logUserOff(String email, String token);
    String getEmailForToken(String token);

}
