package org.mysterymuscle.randomgohomebooster.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberDeck> memberDeckList = new ArrayList<>();

    // 생성한 덱 목록
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Deck> OwnedDecks = new ArrayList<>();

    // 생성한 카드 목록
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Card> OwnedCards = new ArrayList<>();

    // 생성한 아이템 목록
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Item> OwnedItems = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Member(String loginId, String password, String name, String email) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.roles.add("ROLE_USER");
    }

    public static Member createMember(String loginId, String password, String name, String email){
        return new Member(loginId, password, name, email);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public void setAdmin(boolean b) {
        if(!this.roles.contains("ROLE_ADMIN")){
            this.roles.add("ROLE_ADMIN");
        }
    }

    public void setPassword(String encode) {
        this.password = encode;
    }
}
