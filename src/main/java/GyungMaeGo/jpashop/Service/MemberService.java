//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GyungMaeGo.jpashop.Service;

import GyungMaeGo.jpashop.Repository.MemberRepository;
import GyungMaeGo.jpashop.domain.Member;
import java.util.List;
import lombok.Generated;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        readOnly = true
)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        this.validateDuplidateMember(member);
        this.memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplidateMember(Member member) {
        List<Member> findMembers = this.memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return this.memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return this.memberRepository.findOne(memberId);
    }

    public String findPassword(String name) {
        return this.memberRepository.findByPassword(name);
    }

    @Generated
    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
