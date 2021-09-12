package org.mystery_muscle.random_gohome_booster.member.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mystery_muscle.random_gohome_booster.member.entity.Role;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Member{

    @Id
    private String memberId; // 회원 아이디
    private String memberName; // 회원 이름
    private String memberPass; // 회원비밀번호
    private String memberEmail; // 회원 이메일
    private Date memberRegDate; // 회원등록일

    @OneToOne
    @JoinColumn
    private Role memberRole; // 회원레벨

    // TODO : 암호화 작업
    // memberLevel 은 별도 클래스 만들어 memberRole 로 바꾸는 게 좋은가?

}
