package GyungMaeGo.jpashop.Service;

import GyungMaeGo.jpashop.domain.User;
import GyungMaeGo.jpashop.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // Client Registration (Google, Kakao etc.)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        if ("google".equals(registrationId)) {
            return processGoogleUser(oAuth2User);
        } else if ("kakao".equals(registrationId)) {
            return processKakaoUser(oAuth2User);
        } else {
            throw new OAuth2AuthenticationException("Unsupported provider: " + registrationId);
        }
    }

    private OAuth2User processGoogleUser(OAuth2User oAuth2User) {
        // 구글 사용자 정보 처리 로직
        // 예시: email, name, picture 정보를 DB에 저장하는 로직 작성
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");
        String role = "ROLE_USER"; // 기본 역할

        // 사용자 정보 저장 로직
        saveOrUpdateUser(email, name, picture, role, "google");

        return oAuth2User;
    }

    private OAuth2User processKakaoUser(OAuth2User oAuth2User) {
        // 카카오 계정 정보를 추출합니다.
        Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
        if (kakaoAccount != null) {
            Object profileObj = kakaoAccount.get("profile");
            if (profileObj instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> profile = (Map<String, Object>) profileObj;

                String ID = profile != null ? (String) profile.get("id") : "Unknown User";
                String email = (String) kakaoAccount.getOrDefault("email", "default_email@domain.com");
                String name = profile != null ? (String) profile.get("nickname") : "Unknown User";
                String picture = profile != null ? (String) profile.get("profile_image_url") : null;
                String role = "ROLE_USER";

                // 로그 정보를 출력합니다.
                log.info("Kakao User Info - ID {}, Email: {}, Name: {}, Picture: {}, Role: {}", ID, email, name, picture, role);

                // 데이터베이스에 사용자 정보를 저장하거나 업데이트합니다.
                saveOrUpdateUser(email, name, picture, role, "kakao");
            }
        }
        return oAuth2User;
    }



    private void saveOrUpdateUser(String email, String name, String picture, String role, String provider) {
        // DB에 사용자 정보 저장 또는 업데이트
        User user = userRepository.findByEmail(email)
                .orElse(new User(email, name, picture, role, provider));
        user.setName(name);
        user.setPicture(picture);
        user.setProvider(provider);
        userRepository.save(user);
    }
}