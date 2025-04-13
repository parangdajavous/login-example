package com.example.loginexample._core.error;

import com.example.loginexample._core.ex.Exception401;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // data return
public class ErrorController {


    //401 -> 인증 안됨
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e) {
        // catch 자리
        String html = """
                <script>
                    alert('${msg}')
                    location.href="/login-form";
                </script>
                """.replace("${msg}", e.getMessage());
        return html;
    }

    @ExceptionHandler(Exception.class)
    public String exUnKnown(Exception e) {
        // catch 자리
        String html = """
                 <script>
                     alert('${msg}');
                     history.back();
                 </script>
                 """.replace("${msg}", "관리자에게 문의해주세요.");
        System.out.println("관리자님 보세요: " + e.getMessage());
        return html;
    }
}