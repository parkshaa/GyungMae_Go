package GyungMaeGo.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserNo")  // 기본키 설정.
    private Long userNo;

    @Column(nullable = false, unique = true, name = "user_id")
    private String userId;

    @Column(nullable = false, name = "user_pw")
    private String userPw;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "provider")
    private String provider; // Google, Kakao, Local 등

    @Column(name = "provider_id")
    private String providerId; // 소셜 로그인 시 사용되는 고유 ID
}
