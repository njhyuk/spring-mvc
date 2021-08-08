package com.spinrgmvc.board.controller;

import com.spinrgmvc.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/board/list")
    public String list(Model model) { // dispatcherServlet 이 모델을 생성 해 중
        model.addAttribute("boardList", boardService.list());
        return "board/list"; // View 힌트, dispatcherServlet 이 사용자에게 보여줄 뷰를 선정
    }

    @RequestMapping(value = "/board/read/{seq}")
    public String read(Model model, @PathVariable int seq) {
        model.addAttribute("boardVO", boardService.read(seq));
        return "board/read";
    }

    @RequestMapping(value = "/board/write")
    @ResponseBody
    public String write(Model model) {
        return "todo";
    }
}
