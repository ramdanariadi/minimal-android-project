package pl.czak.minimal.dto.api;

public class GetAccessTokenRequest {
    private final String refreshToken;
    public GetAccessTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
}
