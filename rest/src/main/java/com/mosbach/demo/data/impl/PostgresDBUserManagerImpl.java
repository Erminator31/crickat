package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.User;
import com.mosbach.demo.data.api.UserManager;
import com.mosbach.demo.model.task.Task;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresDBUserManagerImpl implements UserManager {
    
    String databaseURL = "jdbc:postgresql://ec2-54-208-11-146.compute-1.amazonaws.com:5432/d80h9tmb4ulhek";
    String username = "vvlgekykzgvzmf";
    String password = "817c7da92c483a4dd61333fd7b17f8b98767175ab0cd2ca3700e348c796ceb9f";
    BasicDataSource basicDataSource;


    // Singleton
    static PostgresDBUserManagerImpl postgresDBUserManager = null;
    private PostgresDBUserManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBUserManagerImpl getPostgresDBUserManagerImpl() {
        if (postgresDBUserManager == null)
            postgresDBUserManager = new PostgresDBUserManagerImpl();
        return postgresDBUserManager;
    }

    public void createUserTable() {

        // Be carefull: It deletes data if table already exists.
        //
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            // String dropTable = "DROP TABLE tasks";

            String createTable = "CREATE TABLE users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "firstname varchar(100) NOT NULL, " +
                    "lastname varchar(100) NOT NULL, " +
                    "password varchar(100) NOT NULL, " +
                    "email varchar(100) NOT NULL, " +
                    "token varchar(100) NOT NULL, " +
                    "validuntil int NOT NULL)";

            // stmt.executeUpdate(dropTable);

            stmt.executeUpdate(createTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> readAllUsers() {

        final Logger readUserLogger = Logger.getLogger("ReadUserLogger");
        readUserLogger.log(Level.INFO,"Start reading ");

        List<User> users = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            /*
            while (rs.next()) {
                users.add(
                        new UserImpl(
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("password"),
                                rs.getString("email"),
                                rs.getString("token"),
                                rs.getInt("validuntil")
                        )
                );
            }
             */
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return
                users;
    }


    @Override
    public User createUser(String firstName, String lastName, String userPassword, String email) {

        final Logger createUserLogger = Logger.getLogger("CreateUserLogger");
        createUserLogger.log(Level.INFO,"Start creating " + email);

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into users (firstname, lastname, password, email, token, validuntil) VALUES (" +
                    "'" + firstName + "', " +
                    "'" + lastName + "', " +
                    "'" + password + "', " +
                    "'" + email + "', " +
                    "'logged-off', " +
                    "0)";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // User newUser = new UserImpl(firstName, lastName, userPassword, email, "logged-off",0);

        return
                null;
    }


    @Override
    public User logUserIn(String email, String password) {

        final Logger loginUserLogger = Logger.getLogger("LoginUserLogger");
        loginUserLogger.log(Level.INFO,"Start logging in " + email);

        // Update set

       return null;
    }

    @Override
    public User logUserOff(String email, String token) {

        final Logger loginOffLogger = Logger.getLogger("LogoffUserLogger");
        loginOffLogger.log(Level.INFO,"Start logging off " + email);

        // update set

        return null;
    }

    @Override
    public String getEmailForToken(String token) {

        // SQL WHERE

        return "not-found";
    }
}
