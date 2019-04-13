package ua.product.manager.security;

public class SecurityConstants {
    public static final String SECRET = "йцвйца213124цйвйцайцв";
    public static final long EXPIRATION_TIME = 1_284_012_568;
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";

    public static final String SIGN_IN_URL = "/api/auth/sign-in";
}
