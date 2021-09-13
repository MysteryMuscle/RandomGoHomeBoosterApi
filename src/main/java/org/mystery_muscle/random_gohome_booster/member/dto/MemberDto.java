package org.mystery_muscle.random_gohome_booster.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mystery_muscle.random_gohome_booster.member.entity.Role;
import java.util.Date;

@Getter
@Setter
@ToString
public class MemberDto {


    private String memberId; // 회원 아이디
    private String memberName; // 회원 이름
    private String memberPass; // 회원비밀번호
    private String memberEmail; // 회원 이메일
    private Date memberRegDate; // 회원등록일
    private Role memberRole; // 회원레벨

    // TODO : 암호화 작업
    // memberLevel 은 별도 클래스 만들어 memberRole 로 바꾸는 게 좋은가?

}
