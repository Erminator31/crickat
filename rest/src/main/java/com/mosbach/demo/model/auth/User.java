package com.mosbach.demo.model.auth;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String userEmail = "";
    private String userPassword = "";
    private String username = "";

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public User(String userEmail, String userPassword, String username) {
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

   public String getUsername() {
       return username;
   }

   public void setUsername(String username) {
       this.username = username;
   }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}
