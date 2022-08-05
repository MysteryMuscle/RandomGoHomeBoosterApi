package org.mysterymuscle.randomgohomebooster;

import lombok.RequiredArgsConstructor;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.service.MemberService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class dbInit {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final MemberService memberService;

        public void dbInit1() {

            // if there's no admin, make one
            if (memberService.getMember("admin") == null) {
                Member admin = Member.createMember("admin", "1234", "관리자", "admin@test.com");
                admin.setAdmin(true);
                memberService.insertMember(admin);
            }
        }

    }
}
