package gd.fintech.fileuploadtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.fileuploadtest.service.CommentService;
import gd.fintech.fileuploadtest.vo.Comment;

@Controller
public class CommentController {
	@Autowired CommentService commentService;
	
	//댓글 추가 액션
	@PostMapping("/addComment")
	public String addComment(Comment comment) {
		commentService.addComment(comment);
		return "redirect:/boardOne/"+comment.getBoardId()+"/1";
	}
	//댓글 삭제 액션
	@GetMapping("/removeComment")
	public String removeComment(@RequestParam(value="commentId") int commentId,
								@RequestParam(value="boardId") int boardId) {
		commentService.removeComment(commentId);
		return "redirect:/boardOne/"+boardId+"/1";
	}
	
	//댓글 수정 액션
	@PostMapping("/updateComment")
	public String updateComment(Comment comment) {
		commentService.updateComment(comment);
		return "redirect:/boardOne/"+comment.getBoardId()+"/1";
	}
	
}
