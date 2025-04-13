package com.example.loginexample.user;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }


    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/user/update-form")
    public String updateForm() {
        // 인증로직
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("인증이 필요합니다.");

        return "user/update-form";
    }

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO updateDTO) {
        // 인증로직
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("인증이 필요합니다.");
        userService.회원정보수정(updateDTO,sessionUser.getId());
        return "redirect:/";

    }


}
