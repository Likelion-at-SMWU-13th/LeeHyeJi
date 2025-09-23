package org.example.seminar.service;

import org.example.seminar.User.User;
import org.example.seminar.dto.JoinDTO;
import org.example.seminar.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {

        // DB에 같은 username이 존재하는지 확인
        if (userRepository.existsByUsername(joinDTO.getUsername())){
            return;
        }

        // joinDTO를 User 엔티티로 변경
        User user = new User();
        user.setUsername(joinDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }
}
