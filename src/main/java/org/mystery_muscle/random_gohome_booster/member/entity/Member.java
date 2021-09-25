package org.mystery_muscle.random_gohome_booster.member.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

}
