package gd.fintech.fileuploadtest.vo;

import lombok.Data;

@Data
public class Boardfile {
	private int boardfileNo;
	private int boardId;
	private String boardfileName;
	private String boardfileType;
	private int boardfileSize;
}
