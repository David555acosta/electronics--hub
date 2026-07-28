package com.curso.expecializacion.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {

    @Value("${spring.jwt.secret.key}")
    private String secretKey;
    @Value("${spring.jwt.time.expiration}")
    private String expiration;


    private String REFRESH_WINDOW = ""


    //Generar token de acceso
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis())) //fecha cracion token
                .expiration(new Date(System.currentTimeMillis() + Integer.parseInt(expiration))) //fecha expiracion token
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Obtener firma del token
    private SecretKey getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); //se decodifica el token en un array de bytes
        return Keys.hmacShaKeyFor(keyBytes); //
    }


    //validar firma del token
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignatureKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (Exception e) {
            log.error("Token invalido, error: ".concat(e.getMessage()));
            return false;
        }
    }


    // Obtener el username del token
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // Obtener un solo claim
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = getClaimsFromToken(token);
        return claimsTFunction.apply(claims);
    }


    //Obtener todos los claims del token
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSignatureKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    ///Metodos para consultar y actualizar tokens expirados

    public Date getExpirationDateFromToken(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }


    public boolean canBeTokenRenoved(String token) {
        Claims getExpirationDateFromToken(token)
    }
}
