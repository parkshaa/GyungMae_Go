package GyungMaeGo.jpashop.Service;

import GyungMaeGo.jpashop.Repository.MemberRepository;
import GyungMaeGo.jpashop.domain.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  //  트랜잭션 읽기 전용 설정.
@RequiredArgsConstructor //final 에있는 필드만 가지고 생성자를 만들어줌.
public class MemberService {


    private final MemberRepository memberRepository;


    //회원가입
    @Transactional //디폴트값이 readOnly = false임. 일반적으로 쓰기같은경우  readOnly = false 사용.
    public Long join(Member member){
        validateDuplidateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();

    }


    private void validateDuplidateMember(Member member){
        List<Member> findMembers =  memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


    public String findPassword(String name){
        return memberRepository.findByPassword(name);
    }

}
