package GyungMaeGo.jpashop.Repository;

import GyungMaeGo.jpashop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
    Optional<User> findByUserId(String userId); // UserId로 사용자 찾기
}
