package gd.fintech.fileuploadtest.vo;

import lombok.Data;

@Data
public class Comment {
	private int commentId;
	private int boardId;
	private String commentContent;
}
