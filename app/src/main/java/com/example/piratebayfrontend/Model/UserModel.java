package com.example.piratebayfrontend.Model;

public class UserModel {

    private Integer userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String catUserStatus;

    public UserModel() {
    }

    public UserModel(Integer userId, String username, String email, String phoneNumber, String catUserStatus) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.catUserStatus = catUserStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCatUserStatus() {
        return catUserStatus;
    }

    public void setCatUserStatus(String catUserStatus) {
        this.catUserStatus = catUserStatus;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", catUserStatus='" + catUserStatus + '\'' +
                '}';
    }
}
