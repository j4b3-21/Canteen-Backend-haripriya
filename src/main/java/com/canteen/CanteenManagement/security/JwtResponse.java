package com.canteen.CanteenManagement.security;

public class JwtResponse {

    private String token;

    // Required by Jackson
    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
