package com.example.loginexample.user;


import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO{
        public String username;
        public String password;
        public String email;
    }

    @Data
    public static class LoginDTO{
        public String username;
        public String password;
    }

    @Data
    public static class UpdateDTO {
        private String password;
        private String email;
    }
}
