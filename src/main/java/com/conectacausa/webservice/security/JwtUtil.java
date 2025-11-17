package com.conectacausa.webservice.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * Utilitário para geração, extração e validação de tokens JWT.
 */
public class JwtUtil {

    /** Chave secreta utilizada para assinar os tokens. */
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /** Tempo de expiração do token em milissegundos (1 dia). */
    private static final long EXPIRATION_TIME = 86400000;

    /**
     * Gera um token JWT para o username informado.
     *
     * @param username nome do usuário
     * @return token JWT gerado
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrai o username contido no token JWT.
     *
     * @param token token JWT
     * @return username extraído do token
     */
    public static String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Valida se o token JWT é válido.
     *
     * @param token token JWT
     * @return true se o token for válido, false caso contrário
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}