package com.example.loginexample.user;


import com.example.loginexample._core.Sha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        // 1. 동일 username 있는지 검사 (조회)
        User user = userRepository.findByUsername(joinDTO.getUsername());

        // 2. 있으면 exception 터트리기
        if (user != null) throw new RuntimeException("동일한 username이 존재합니다.");

        // 3. 없으면 회원가입하기
        String encPassword = Sha.encode(joinDTO.getPassword());
        joinDTO.setPassword(encPassword);
        userRepository.save(joinDTO.getUsername(), encPassword, joinDTO.getEmail());
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        // 1. 해당 username이 있는지
        User user = userRepository.findByUsername(loginDTO.getUsername());

        // 2. username, password가 불일치하는 것들을 Filtering
        if (user == null) {
            throw new RuntimeException("해당 username이 없습니다.");
        }

        String encPassword = Sha.encode(loginDTO.getPassword());
        if (!(user.getPassword().equals(encPassword))) {
            throw new RuntimeException("해당 password가 틀렸습니다.");
        }

        // 3. 인증
        return user;
    }

    @Transactional
    public void 회원정보수정(UserRequest.UpdateDTO updateDTO, Integer id) {
        User user = userRepository.findById(id);
        if (user == null) throw new RuntimeException("회원을 찾을 수 없습니다");

        String encPassword = Sha.encode(updateDTO.getPassword());
        updateDTO.setPassword(encPassword);
        userRepository.update(encPassword, updateDTO.getEmail(), id);
    }
}
