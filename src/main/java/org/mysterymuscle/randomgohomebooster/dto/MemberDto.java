package org.mysterymuscle.randomgohomebooster.dto;

import lombok.Getter;
import lombok.Setter;
import org.mysterymuscle.randomgohomebooster.domain.Member;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {

    private String loginId;
    private String name;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private boolean admin;

    public static MemberDto from(Member member) {
        MemberDto dto = new MemberDto();
        dto.setLoginId(member.getLoginId());
        dto.setName(member.getName());
        dto.setEmail(member.getEmail());
        dto.setRegDate(member.getRegDate());
        dto.setModDate(member.getModDate());
        dto.setAdmin(member.isAdmin());
        return dto;
    }
}
