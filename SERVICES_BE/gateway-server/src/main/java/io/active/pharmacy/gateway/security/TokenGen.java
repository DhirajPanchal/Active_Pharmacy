package io.active.pharmacy.gateway.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class TokenGen {

    public static void main(String[] args) {
        System.out.println("--------------- TokenGen");
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Kex: " + secretString);
    }
}
