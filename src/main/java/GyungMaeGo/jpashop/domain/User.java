package GyungMaeGo.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    @Column(name="UserNo")  // 기본키 설정.
    private Long UserNo;

    private String UserId;
    private String UserPw;
    private String Name;
    private String Phone;
    private String Address;
}
