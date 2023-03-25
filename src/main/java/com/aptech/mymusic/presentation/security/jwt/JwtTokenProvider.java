package com.aptech.mymusic.presentation.security.jwt;

import com.aptech.mymusic.domain.entities.User;
import com.aptech.mymusic.domain.entities.UserDetail;
import com.aptech.mymusic.utils.JsonHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private static final String KEY_USER_OBJECT = "k_user_object";
    @Value("${jwt.secret}")
    private String secretKey;
    private SecretKey key;

    @PostConstruct
    public void init() {
        // Decode the secret key from base64 and create a SecretKey instance
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(@NotNull UserDetail userDetails, long expirationMs) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.ID, userDetails.getUser().getId());
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        claims.put(KEY_USER_OBJECT, JsonHelper.objToJson(userDetails.getUser()));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetail userDetail = getUserDetail(token);
        return new UsernamePasswordAuthenticationToken(userDetail, "", userDetail.getAuthorities());
    }

    public UserDetail getUserDetail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        User user = JsonHelper.jsonToObj((String) claims.get(KEY_USER_OBJECT), User.class);
        return new UserDetail(user);
    }

    public String getUserName(String token) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            return "";
        }
    }

    public String resolveToken(@NotNull HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
