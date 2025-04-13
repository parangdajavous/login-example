package com.example.loginexample.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> 게시글목록() {
        List<Board> boardList = boardRepository.findALl();
        return boardList;
    }
}
