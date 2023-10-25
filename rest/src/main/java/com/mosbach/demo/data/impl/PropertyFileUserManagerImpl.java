package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.User;
import com.mosbach.demo.data.api.UserManager;
import com.mosbach.demo.model.task.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyFileUserManagerImpl implements UserManager {

    String userPropertyFile;

    // Singleton
    static PropertyFileUserManagerImpl propertyFileUserManager = null;
    private PropertyFileUserManagerImpl(String userPropertyFile) {
        this.userPropertyFile = userPropertyFile;
    }
    public static PropertyFileUserManagerImpl getPropertyFileUserManagerImpl(String userPropertyFile) {
        if (propertyFileUserManager == null)
                propertyFileUserManager = new PropertyFileUserManagerImpl(userPropertyFile);
        return propertyFileUserManager;
    }


    @Override
    public User createUser(String firstName, String lastName, String userPassword, String email) {

            final Logger createUserLogger = Logger.getLogger("CreateUserLogger");
            createUserLogger.log(Level.INFO,"Start creating " + email);

        List<User> users = readAllUsers();

            createUserLogger.log(Level.INFO,"Adding new user.");

        // User newUser = new UserImpl(firstName, lastName, userPassword, email, "logged-off",0);
        // users.add(newUser);

            createUserLogger.log(Level.INFO,"Start storing all users.");

        storeAllUsers(users);

        return
                null;
    }

    public void storeAllUsers(List<User> users) {
        // todo
    }


    public List<User> readAllUsers() {

        // todo

        return null;
    }

    @Override
    public User logUserIn(String email, String password) {

        final Logger loginUserLogger = Logger.getLogger("LoginUserLogger");
        loginUserLogger.log(Level.INFO,"Start logging in " + email);

        List<User> users = readAllUsers();

        loginUserLogger.log(Level.INFO,"Finding user.");
        for (int i = 0; i < users.size(); i++) {
            User temp = users.get(i);
            if (temp.getEmail().equals(email)) {
                loginUserLogger.log(Level.INFO,"User found. Setting his token. Ignoring password check.");
                // users.set(i, new UserImpl(temp.getFirstName(), temp.getLastName(), temp.getPassword(), temp.getEmail(),
                //        System.currentTimeMillis() + "", 0)
                //        );
            }
            loginUserLogger.log(Level.INFO,"Writing data back to file.");
            storeAllUsers(users);

            return
                    users.get(i);
        }

       return null;
    }

    @Override
    public User logUserOff(String email, String token) {

        final Logger loginOffLogger = Logger.getLogger("LogoffUserLogger");
        loginOffLogger.log(Level.INFO,"Start logging off " + email);

        List<User> users = readAllUsers();

        loginOffLogger.log(Level.INFO,"Finding user.");
        for (int i = 0; i < users.size(); i++) {
            User temp = users.get(i);
            if (temp.getEmail().equals(email)) {
                loginOffLogger.log(Level.INFO,"User found. Setting his token. Ignoring token check.");
                // users.set(i, new UserImpl(temp.getFirstName(), temp.getLastName(), temp.getPassword(), temp.getEmail(),
                //        "logged-off", 0)
                //);
            }

            loginOffLogger.log(Level.INFO,"Writing data back to file.");
            storeAllUsers(users);

            return
                    users.get(i);
        }

        return null;
    }

    @Override
    public String getEmailForToken(String token) {
        for(User u : readAllUsers())
            if (u.getToken().equals(token))
                return u.getEmail();
        return "not-found";
    }
}
