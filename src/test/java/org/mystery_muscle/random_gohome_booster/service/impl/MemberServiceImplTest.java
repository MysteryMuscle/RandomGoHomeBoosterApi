package org.mystery_muscle.random_gohome_booster.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.repository.MemberRepository;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceImplTest {

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
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(saveId);

        // then
        assertEquals(member, findMember);
    }

    @Test
    public void MemberDuplicateExceptionTest(){
        // given

        // when

        // then
    }
}