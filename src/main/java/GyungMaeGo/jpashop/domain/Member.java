package GyungMaeGo.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")  // 기본키 설정.
    private Long id;

    private String name;

    private String password;

    @Embedded //내장타입을 포함했다.
    private Address address;

    @OneToMany(mappedBy = "member")  //order테이블과 1대 다 관계임 , order테이블에 있는 member 에 의해서 매핑이된다는뜻임.
    private List<Order> orders = new ArrayList<>();





}
