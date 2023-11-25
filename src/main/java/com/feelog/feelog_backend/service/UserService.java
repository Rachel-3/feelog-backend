package com.feelog.feelog_backend.service;

import com.feelog.feelog_backend.model.User;
import com.feelog.feelog_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String email, String name, String password) {
        validateEmail(email);
        validatePassword(password);
        if (emailExists(email)) {
            throw new RuntimeException("이미 등록된 이메일입니다: " + email);
        }
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
        return userRepository.save(newUser);
    }

    private void validateEmail(String email) {
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            throw new RuntimeException("이메일 형식이 유효하지 않습니다: " + email);
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new RuntimeException("비밀번호는 최소 8자 이상이어야 합니다.");
        }
        // 여기에 추가적인 비밀번호 복잡성 검증 로직을 추가할 수 있습니다.
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + email));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    // 기타 서비스 메서드 추가
}