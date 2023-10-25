package com.mosbach.demo.data.api;

public interface User {

    String getFirstName();
    String getLastName();
    String getPassword();
    String getEmail();
    String getToken();
    long getValidUntil();
}
