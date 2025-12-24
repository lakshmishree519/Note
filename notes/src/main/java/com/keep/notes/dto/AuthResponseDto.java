package com.keep.notes.dto;

public class AuthResponseDto {
    private String token;
    private Integer userId;
    private String name;


    public AuthResponseDto(String token, Integer userId, String name) {
        this.token = token;
        this.userId = userId;
        this.name = name;
    }


    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
