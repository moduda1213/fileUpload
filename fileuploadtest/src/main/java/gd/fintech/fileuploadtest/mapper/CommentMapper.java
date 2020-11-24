package gd.fintech.fileuploadtest.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.fileuploadtest.vo.Comment;

@Mapper
public interface CommentMapper {
	int insertComment(Comment comment);
	int deleteComment(int commentId);
	int updateComment(Comment comment);
	
}
