package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //init
        Member member = new Member();
        member.setName("kim");
        //condition
        Long savedId = memberService.join(member);
        //result
        assertEquals(member, memberRepository.findOne((savedId)));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예회() throws Exception {
        //init
        Member member1 = new Member();
        member1.setName("dan");

        Member member2 = new Member();
        member2.setName("dan");

        //condition
        memberService.join(member1);
        memberService.join(member2);


        //result
        fail("예외가 발생하여야 한다.");

    }
}