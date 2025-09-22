package org.example.seminar.service;

import lombok.RequiredArgsConstructor;
import org.example.seminar.dto.SignupRequestDTO;
import org.example.seminar.entity.User;
import org.example.seminar.exception.CustomException;
import org.example.seminar.exception.HttpStatus;
import org.example.seminar.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(SignupRequestDTO requestDTO) {
        // 1. 아이디 중복 검사
        if (userRepository.existsByUsername(requestDTO.getUsername())) {
            throw new CustomException(HttpStatus.CONFLICT, "이미 사용 중인 아이디입니다.");
        }

        // 2. 이메일 도메인 검사
        if (!requestDTO.getEmail().endsWith("@sookmyung.ac.kr")) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "숙명여대 이메일만 사용할 수 있습니다.");
        }

        // 3. User 엔티티 생성
        User user = User.builder()
                .username(requestDTO.getUsername())
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .build();

        userRepository.save(user);
    }
}
