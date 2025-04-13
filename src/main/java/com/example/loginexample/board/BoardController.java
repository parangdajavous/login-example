package com.example.loginexample.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    public final BoardService boardService;

    @GetMapping("/")
    public String list(HttpServletRequest request) {
        List<Board> boardList = boardService.게시글목록();
        request.setAttribute("boardList", boardList);
        return "board/list";
    }
}
