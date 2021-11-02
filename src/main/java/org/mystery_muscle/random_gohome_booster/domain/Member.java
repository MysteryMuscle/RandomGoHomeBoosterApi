package org.mystery_muscle.random_gohome_booster.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class Member{

    @Id @Generated
    @Column(name="member_id")
    private Long id;
    private String loginId; // 회원 아이디
    private String name; // 회원 이름

    private String email; // 회원 이메일
    // TODO : 암호화 작업

    private String password; // 회원비밀번호
    private LocalDateTime RegDate; // 회원등록일
    private boolean admin; // 관리자 여부

    @OneToMany(mappedBy = "member")
    private List<ApprovedDeck> memberApprovedDecks = new ArrayList<>();


}
