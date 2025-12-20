package pl.czak.minimal.dto.api;

import pl.czak.minimal.dto.User;

public class LoginResponse {

    private Response data;

    public LoginResponse(Response data) {
        this.data = data;
    }
    public Response getData() {
        return data;
    }

    public void setData(Response data) {
        this.data = data;
    }

    public static class Response {
        private String accessToken;
        private String refreshToken;
        private User user;

        public Response(String accessToken, String refreshToken, User user) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.user = user;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
