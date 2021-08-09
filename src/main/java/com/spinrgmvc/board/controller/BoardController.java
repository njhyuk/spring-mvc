package com.spinrgmvc.board.controller;

import com.spinrgmvc.board.domain.BoardVO;
import com.spinrgmvc.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("boardVO")
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
    public String write(@Valid BoardVO boardVO, BindingResult result) {
        if (result.hasErrors()) {
            return "/board/write";
        } else {
            boardService.write(boardVO);
            return "redirect:/board/write";
        }
    }

    @RequestMapping(value = "/board/edit/{seq}", method = RequestMethod.GET)
    public String edit(@PathVariable int seq, Model model) {
        model.addAttribute("boardVO", boardService.read(seq)); // 세션에 자동 등록됨
        return "/board/edit";
    }

    @RequestMapping(value = "/board/edit/{seq}", method = RequestMethod.POST)
    public String edit(
            @Valid @ModelAttribute BoardVO boardVO, // 세션에서 boardVO 바인딩 됨 (HttpServletRequest)
            int pwd,
            SessionStatus sessionStatus,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "/board/edit";
        } else {
            if (boardVO.getPassword() == pwd) {
                boardService.edit(boardVO);
                sessionStatus.setComplete(); // 세션에서 해제
                return "redirect:/board/list";
            }
        }

        model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
        return "/board/edit";
    }

    @RequestMapping(value = "/board/delete/{seq}", method = RequestMethod.GET)
    public String delete(@PathVariable int seq, Model model) {
        model.addAttribute("boardVO", boardService.read(seq));
        return "/board/delete";
    }

    @RequestMapping(value = "/board/delete", method = RequestMethod.POST)
    public String delete(int seq, int pwd, Model model) {
        int rowCount;
        BoardVO boardVO = new BoardVO();
        boardVO.setSeq(seq);
        boardVO.setPassword(pwd);

        rowCount = boardService.delete(boardVO);

        if (rowCount == 0) {
            model.addAttribute("seq", seq);
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            return "/board/delete";
        } else {
            return "redirect:/board/list";
        }
    }
}
