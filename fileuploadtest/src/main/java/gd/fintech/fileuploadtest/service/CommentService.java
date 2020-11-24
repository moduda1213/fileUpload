package gd.fintech.fileuploadtest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.fileuploadtest.mapper.CommentMapper;
import gd.fintech.fileuploadtest.vo.Comment;

@Service
@Transactional
public class CommentService {
	@Autowired private CommentMapper commentMapper;
	
	//댓글 추가
	public int addComment(Comment comment) {
		return commentMapper.insertComment(comment);
	}
	//댓글 삭제
	public int removeComment(int commentId) {
		return commentMapper.deleteComment(commentId);
	}
	//댓글 수정
	public int updateComment(Comment comment) {
		return commentMapper.updateComment(comment);
	}
	
}
