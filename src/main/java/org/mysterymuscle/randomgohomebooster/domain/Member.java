package org.mysterymuscle.randomgohomebooster.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
 @EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(name = "member_login_id", unique = true, length = 20)
    private String loginId; // 회원 아이디
    private String password; // 회원비밀번호

    private String name; // 회원 이름
    private String email; // 회원 이메일
    // TODO : 암호화 작업

    @CreatedDate
    private LocalDateTime regDate; // 회원등록일
    @LastModifiedDate
    private LocalDateTime modDate;

    private boolean admin = false; // 관리자 여부

    // 승인된 덱 목록
    @OneToMany(mappedBy = "member")
    private List<MemberDeck> memberDeckList = new ArrayList<>();

    // 생성한 덱 목록
    @OneToMany(mappedBy = "owner")
    private List<Deck> OwnedDecks = new ArrayList<>();

    // 생성한 카드 목록
    @OneToMany(mappedBy = "creator")
    private List<Card> OwnedCards = new ArrayList<>();

    // 생성한 아이템 목록
    @OneToMany(mappedBy = "creator")
    private List<Item> OwnedItems = new ArrayList<>();

    public static Member createMember(String loginId, String password, String name, String email){
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setName(name);
        member.setEmail(email);
        return member;
    }

}
