package gd.fintech.fileuploadtest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gd.fintech.fileuploadtest.service.BoardService;
import gd.fintech.fileuploadtest.vo.BoardForm;

@Controller
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private BoardService boardService;
	
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	
	@PostMapping("/addBoard")
	public String addBoard(BoardForm boardForm) {
		logger.debug(boardForm.toString());
		logger.debug("size : " + boardForm.getBoardfile().size());
		boardService.addBoard(boardForm);
		return "redirect:/";
	}
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
















