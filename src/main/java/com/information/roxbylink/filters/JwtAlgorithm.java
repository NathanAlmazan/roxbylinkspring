package com.information.roxbylink.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.information.roxbylink.account.dto.AccountDto;
import com.information.roxbylink.errors.CustomErrorResponse;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class JwtAlgorithm {
    private final Algorithm algorithm;

    public JwtAlgorithm() {
        this.algorithm = Algorithm.HMAC256(System.getenv("PASSWORD_SECRET").getBytes());
    }

    public String createAccessToken(String username, String issuer, List<String> authorities) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(issuer)
                .withClaim("roles", authorities)
                .sign(algorithm);
    }

    public String createRefreshToken(String username, String issuer) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public AccountDto verifyToken(String jwtToken) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(jwtToken);

        AccountDto account = new AccountDto();
        account.setUsername(decodedJWT.getSubject());
        account.setRoles(Arrays.asList(decodedJWT.getClaim("roles").asArray(String.class)));

        return account;
    }
    public String customServerError(int errorCode, String message) throws JsonProcessingException {
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorCode);
        errorResponse.addErrorMessage(message);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(errorResponse.getErrorObject());
    }
}
