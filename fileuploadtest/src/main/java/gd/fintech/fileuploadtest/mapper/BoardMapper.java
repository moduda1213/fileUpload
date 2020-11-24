package gd.fintech.fileuploadtest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.fileuploadtest.vo.Board;

@Mapper
public interface BoardMapper {
	List<Board> selectBoardListByPage(Map<String,Object> map);
	Board selectUpdateBoardForm(int boardId); // 수정 폼
	Board selectBoardOne(Map<String,Object> map);// 리스트 상세보기
	int totalBoardOneComment(int boardId);//한개의 리스트가 갖고있는 댓글 총 수
	int updateBoard(Board board); // 수정 액션
	int totalRow(); // 총 데이터 양
	int deleteBoard(int boardId); //리스트 삭제
	int insertBoard(Board board); // 리스트 추가
}
