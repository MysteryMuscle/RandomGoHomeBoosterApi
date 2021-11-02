package org.mystery_muscle.random_gohome_booster.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(name = "member_login_id", unique = true)
    private String loginId; // 회원 아이디
    private String password; // 회원비밀번호

    private String name; // 회원 이름
    private String email; // 회원 이메일
    // TODO : 암호화 작업

    private LocalDateTime RegDate; // 회원등록일
    private boolean admin = false; // 관리자 여부

    @OneToMany(mappedBy = "member")
    private List<ApprovedDeck> memberApprovedDecks = new ArrayList<>();


}
