package org.mysterymuscle.randomgohomebooster.configuration;

import com.nimbusds.jose.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${spring.jwt.secret}")
    private String secretKey;

    private final long tokenValidityInMilliseconds = 1000L * 60L * 60L;

    @PostConstruct
    protected void init() {
        secretKey = Base64Utils.encodeToString(secretKey.getBytes());
    }

    // create Jwt Token
    public String createToken(String userId, List<String> roles){
        return null;
    }

}
