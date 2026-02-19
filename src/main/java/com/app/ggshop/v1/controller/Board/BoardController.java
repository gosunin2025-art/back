package com.app.ggshop.v1.controller.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/p2p")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

        @GetMapping("/p2p_write_gg") // 주소를 요청 주소와 일치시킵니다.
        public String goToWriteForm(){
            return "p2p/p2p_write_gg";
        }
}
