package com.spinrgmvc.board.controller;

import com.spinrgmvc.board.domain.BoardVO;
import com.spinrgmvc.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    @RequestMapping(value = "/board/write", method = RequestMethod.GET)
    public String write(Model model) {
        model.addAttribute("boardVO", new BoardVO());
        return "board/write";
    }

    /**
     * boardVO : form 태그에서 전송된 request 를 자동으로 BoardVO로 바인딩
     * bindingResult : boardVO 바인딩 할때 오류가 발생하는 경우 오류 내용을 저장함
     */
    @RequestMapping(value = "/board/write", method = RequestMethod.POST)
    public String write(@Valid BoardVO boardVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/board/write";
        } else {
            boardService.write(boardVO);
            return "redirect:/board/write";
        }

    }
}
