package gd.fintech.fileuploadtest.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardForm {
	private String boardTitle;
	private String boardContent;
	private List<MultipartFile> boardfile;
}
