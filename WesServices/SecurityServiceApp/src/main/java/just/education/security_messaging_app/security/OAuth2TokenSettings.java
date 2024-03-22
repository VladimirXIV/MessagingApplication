package just.education.security_messaging_app.security;

import just.education.security_messaging_app.enums.MongoDbSignatureAlgorithm;

import java.time.Duration;

public class OAuth2TokenSettings {
    private Duration accessTokenTimeToLive;
    private boolean reuseRefreshTokens = true;
    private Duration refreshTokenTimeToLive;
    private MongoDbSignatureAlgorithm idTokenSignatureAlgorithm;

    public OAuth2TokenSettings(Duration accessTokenTimeToLive, boolean reuseRefreshTokens, Duration refreshTokenTimeToLive, MongoDbSignatureAlgorithm idTokenSignatureAlgorithm) {
        this.accessTokenTimeToLive = accessTokenTimeToLive;
        this.reuseRefreshTokens = reuseRefreshTokens;
        this.refreshTokenTimeToLive = refreshTokenTimeToLive;
        this.idTokenSignatureAlgorithm = idTokenSignatureAlgorithm;
    }
}
