package org.mystery_muscle.random_gohome_booster.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.repository.MemberRepository;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
        Member member = new Member();
        member.setName("test");

        // when
        member = memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        // then
        assertEquals(member, findMember);
    }

    @Test()
    public void MemberDuplicateExceptionTest() throws IllegalStateException{
        // given
        Member member = new Member();
        member.setName("test name1");
        member.setEmail("test@mail.com");
        member.setPassword(UUID.randomUUID().toString());

        Member duplicate = new Member();
        duplicate.setName("test name2");
        duplicate.setEmail("test@mail.com");
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
}