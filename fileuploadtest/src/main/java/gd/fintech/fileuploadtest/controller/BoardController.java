package gd.fintech.fileuploadtest.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.fileuploadtest.service.BoardService;
import gd.fintech.fileuploadtest.vo.Board;
import gd.fintech.fileuploadtest.vo.BoardForm;

@Controller
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private BoardService boardService;
	//초기 화면
		@GetMapping("/")
		public String index() {
			return "index";
		}
	
	//전체 리스트 페이징
	@GetMapping("/boardList/{currentPage}")
	public String boardList(Model model,
			@PathVariable(name="currentPage", required = true) int currentPage) {
		int rowPerPage = 5;
		int totalRow = boardService.getTotalRow();//전체 행
		int lastPage= 0;
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow/rowPerPage;
		}else {
			lastPage = (totalRow/rowPerPage)+1;
		}
		List<Board> boardList = boardService.getBoardListByPage(currentPage,rowPerPage);
		model.addAttribute("boardList",boardList);
		model.addAttribute("lastPage",lastPage);
		logger.debug("boardList size : " + boardList.size());
		return "boardList";
	}
	//리스트 상세보기 (댓글 페이징)
	@GetMapping("/boardOne/{boardId}/{currentPage}")
	public String boardOne(Model model,
						@PathVariable(name="boardId", required = true) int boardId,
						@PathVariable(name="currentPage", required = true) int currentPage) {
		int rowPerPage = 5;
		int totalRow = boardService.getTotalBoardOneComment(boardId);
		int lastPage = 0;
		if(totalRow % rowPerPage != 0) {
			lastPage = (totalRow / rowPerPage)+1;
		}else {
			lastPage = (totalRow / rowPerPage);
		}
		Board boardListOne=boardService.getBoardOne(boardId,currentPage,rowPerPage);
		model.addAttribute("boardListOne",boardListOne);
		model.addAttribute("lastPage",lastPage);
		return "boardOne";
	}
	
	//리스트 추가 폼
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	//리스트 추가 액션
	@PostMapping("/addBoard")
	public String addBoard(BoardForm boardForm) {
		//logger.debug(boardForm.toString());
		//logger.debug("size : " + boardForm.getBoardfile().size());
		boardService.addBoard(boardForm);
		return "redirect:/";
	}
	//리스트 삭제 액션
	@GetMapping("/removeBoard")
	public String removeBoard(@RequestParam(value="boardId") int boardId,
							@RequestParam(value="currentPage") int currentPage) {
		boardService.removeBoard(boardId);
		return "redirect:/boardList/"+currentPage;
	}
	//리스트 수정 폼
		@GetMapping("/updateBoard/{boardId}")
		public String updateBoard(Model model,
								@PathVariable(name="boardId", required = true) int boardId) {
			Board board = boardService.updateBoardForm(boardId);
			model.addAttribute("board",board);
			return "updateBoard";
		}
	//리스트 수정 액션
	@PostMapping("/updateBoard")
	public String updateBoard(BoardForm boardForm,
			@RequestParam(value="boardId") int boardId) {
		boardService.updateBoard(boardForm,boardId);
		return "redirect:/boardList/1";
	}
	
	//file삭제
	@GetMapping("/deleteFileOne")
	public String deleteFileOne(@RequestParam(value="boardfileNo") int boardfileNo,
								@RequestParam(value="boardId") int boardId) {
		boardService.removeBoardOne(boardfileNo);
		return "redirect:/updateBoard/"+boardId;
	}
}
















