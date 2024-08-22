package GyungMaeGo.jpashop.Service;

import GyungMaeGo.jpashop.Repository.UserRepository;
import GyungMaeGo.jpashop.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getUserPw()) // 패스워드는 반드시 인코딩되어 있어야 합니다
                .roles("USER") // 사용자의 역할을 설정 (필요에 따라 다르게 설정)
                .build();
    }
}
