package gd.fintech.fileuploadtest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.fileuploadtest.vo.Boardfile;

@Mapper
public interface BoardfileMapper {
	int insertBoardfile(Boardfile boardfile);
	int deleteBoardfile(int boardId);
	List<String> selectBoardFileNameList(int boardId);
	int updateBoardfile(Boardfile boardfile);
	int deleteBoardfileOne(int boardfileNo);
	String selectDeleteBoardFileName(int boardfileNo);
}
