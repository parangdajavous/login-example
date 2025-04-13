package com.example.loginexample.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    @GetMapping("/")
    public String list(){
        return "board/list";
    }
}
