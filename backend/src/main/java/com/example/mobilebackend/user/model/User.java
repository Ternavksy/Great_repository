package com.example.mobilebackend.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String role;
    private String notificationSettings;

    public User() {
    }

    public User(String username, String password, String role, String notificationSettings) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.notificationSettings = notificationSettings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getNotificationSettings(){
        return notificationSettings;
    }
    public void setNotificationSettings(String notificationSettings){
        this.notificationSettings = notificationSettings;
    }
}
