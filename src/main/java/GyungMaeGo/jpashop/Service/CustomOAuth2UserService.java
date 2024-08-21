package GyungMaeGo.jpashop.Service;
import GyungMaeGo.jpashop.Repository.UserRepository;
import GyungMaeGo.jpashop.domain.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // OAuth2 provider 정보
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getName(); // OAuth2 사용자 식별자

        // OAuth2User에서 사용자 정보 추출
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // 사용자 정보가 데이터베이스에 있는지 확인
        Optional<User> userOptional = userRepository.findByProviderAndProviderId(provider, providerId);

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            // 새 사용자 생성
            user = new User();
            user.setUserId(email); // 이메일을 UserId로 사용
            user.setName(name);
            user.setProvider(provider);
            user.setProviderId(providerId);
            userRepository.save(user);
        }

        // 반환된 OAuth2User의 권한과 속성들을 사용하여 처리할 수 있음
        return oAuth2User;
    }
}
