package ua.product.manager.security;

public class SecurityConstants {
    public static final String SECRET = "qwdqwfqwddSDWdqdw123d1231dqwe1231";
    public static final long EXPIRATION_TIME = 32_536_000;
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";

    public static final String SIGN_IN_URL = "/api/auth/sign-in";
}
