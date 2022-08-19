package org.mysterymuscle.randomgohomebooster.dto;

import lombok.Getter;
import lombok.Setter;
import org.mysterymuscle.randomgohomebooster.configuration.JwtTokenProvider;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoginResponse {

    private String loginId;
    private String jwt;
    private LocalDateTime expiredAt;

    public LoginResponse(String loginId, String jwt, LocalDateTime expiredAt) {
        this.loginId = loginId;
        this.jwt = jwt;
        this.expiredAt = expiredAt;
    }
}
