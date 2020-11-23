package gd.fintech.fileuploadtest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.fileuploadtest.vo.Board;

@Mapper
public interface BoardMapper {
	int insertBoard(Board board);
	List<Board> selectBoardListByPage(Map<String,Object> map);
	int totalRow();
	int deleteBoard(int boardId);
	Board selectUpdateBoardForm(int boardId); // 수정 폼
	int updateBoard(Board board);
}
