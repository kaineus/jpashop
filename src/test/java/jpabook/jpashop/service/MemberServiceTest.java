package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void joinMember() {

        //given
        Member member = new Member();
        member.setName("TEST");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    void duplicateCheck() throws Exception {
        Member member1 = new Member();
        member1.setName("TEST");

        Member member2 = new Member();
        member2.setName("TEST");

        //when
        Long savedId1 = memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> {
            Long savedId2 = memberService.join(member2);
        });

    }
}