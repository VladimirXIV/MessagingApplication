package just.education.security_messaging_app.security;

public class OAuth2ClientSettings {
    private boolean requireProofKey = false;
    private boolean requireAuthorizationConsent = false;

    public OAuth2ClientSettings(boolean requireProofKey, boolean requireAuthorizationConsent) {
        this.requireProofKey = requireProofKey;
        this.requireAuthorizationConsent = requireAuthorizationConsent;
    }
}
