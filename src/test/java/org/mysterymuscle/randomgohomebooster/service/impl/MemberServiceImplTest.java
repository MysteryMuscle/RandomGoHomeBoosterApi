package org.mysterymuscle.randomgohomebooster.service.impl;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.dto.Login;
import org.mysterymuscle.randomgohomebooster.dto.LoginResponse;
import org.mysterymuscle.randomgohomebooster.repository.MemberRepository;
import org.mysterymuscle.randomgohomebooster.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImplTest.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void MemberJoinTest(){
        // given
        Member member = getMemberEntity();

        // when
        member = memberService.insertMember(member);
        Member findMember = memberService.getMember(member.getLoginId());

        // then
        assertEquals(member, findMember);
    }

    private Member getMemberEntity() {
        return Member.createMember(
                "name"
                , "password"
                , "name"
                , "test@email.com"
        );
    }

    private Member getMemberDuplicateEntity() {
        return Member.createMember(
                "name2"
                , "password2"
                , "name2"
                , "test@email.com"
        );
    }

    @Test()
    public void MemberDuplicateExceptionTest() throws IllegalStateException{
        // given
        Member member = getMemberEntity();
        member.setPassword(UUID.randomUUID().toString());

        Member duplicate = getMemberDuplicateEntity();
        duplicate.setPassword(UUID.randomUUID().toString());

        // when
        memberService.insertMember(member);
        Exception exception = assertThrows(IllegalStateException.class, () ->{
            memberService.insertMember(duplicate);
        });

        // then
        if(exception == null || exception.getClass() != IllegalStateException.class){
            fail("this test is failed!");
        }
    }

    @Test
    void login() {
        // given
        Member member = getMemberEntity();

        String loginId = member.getLoginId();
        String password = member.getPassword();

        member = memberService.insertMember(member);

        // when
        Login login = new Login();
        login.setLoginId(loginId);
        login.setPassword(password);

        LoginResponse loginMember = memberService.login(login);

        // then

    }

    @Test
    void getMember() {
        // given

        Member member = getMemberEntity();
        member = memberService.insertMember(member);

        // when
        Member findMember = memberService.getMember(member.getLoginId());

        // then
        assertEquals(member, findMember);
    }
}