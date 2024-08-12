package GyungMaeGo.jpashop.Service;

import GyungMaeGo.jpashop.Repository.MemberRepository;
import GyungMaeGo.jpashop.domain.Member;
import jakarta.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)  //junit 실행할때 스프링이랑 같이 실행하기위해 사용.
@SpringBootTest   //스프링부트 띠운상태에서 테스트하기위해 사용.
@Transactional  //기본으로 롤백을 해버림. 테스트에서는 .
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;



    //@Rollback(false) // 롤백을 안하고 커밋하도록 설정.
    @Test
    public void 회원가입() throws Exception{

        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId =  memberService.join(member);

        //then
        em.flush(); // insert 쿼리문을 롤백해도 볼 수 있음.
        Assert.assertEquals(member, memberRepository.findOne(saveId));

    }

    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{

        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다.");  //코드가 오다가 여기로 오면 안됨( 여기로 오면 뭔가 잘못된거임)
    }


}