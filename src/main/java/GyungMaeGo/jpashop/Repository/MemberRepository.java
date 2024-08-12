package GyungMaeGo.jpashop.Repository;


import GyungMaeGo.jpashop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {


    private final EntityManager em;  //의존성 주입함. EntityManager


    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class , id);   //Member찾기. 단건조회.
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m" , Member.class)  //쿼리만들어서 회원리스트 반환. JPA는 테이블대상이아니라 객체대상으로 조회시킴.
                  .getResultList();

    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name" , name)
                .getResultList();
    }


    public String findByPassword(String name) {
        return em.createQuery("select m.password from Member m where m.name = :name", String.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
