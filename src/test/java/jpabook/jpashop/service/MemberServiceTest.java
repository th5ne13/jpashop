package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    
    @Test
    public void joinTest() throws Exception {
        // given
        Member member = new Member();
        member.setName("testMember");
        // when
        Long saveId = memberService.join(member);
        // then
        Member findMember = memberService.findOne(saveId);
        assertThat(findMember).isEqualTo(member);
    }

    @Test(expected = IllegalStateException.class)
    public void memberDuplicatedJoinTest() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        memberService.join(member2);    // exception 발생
        // then

        fail("exception occurs");
    }

}
