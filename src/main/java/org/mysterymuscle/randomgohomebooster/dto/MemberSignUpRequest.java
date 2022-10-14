package org.mysterymuscle.randomgohomebooster.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignUpRequest {

    private String loginId;
    private String password;
    private String name;
    private String email;
}
