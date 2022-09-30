package org.mysterymuscle.randomgohomebooster.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final long tokenValidityInMilliseconds = 1000L * 60L * 60L;
    private final UserDetailsService userDetailsService;
    // @Value("${jwt.secret}")
    private String secretKey = "secret";

    // create Jwt Token
    public String createToken(String userId, Collection<String> roles) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .claim("roles", roles)
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityInMilliseconds))
                .signWith(getSecretKey())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String userId = getUserId(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private String getUserId(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody().getSubject();

    }

    private Key getSecretKey() {
        byte[] keyBytes = Base64Utils.decodeFromString(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public LocalDateTime getExpiredAt(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody().getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
