package gd.fintech.fileuploadtest.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.fileuploadtest.vo.Board;

@Mapper
public interface BoardMapper {
	int insertBoard(Board board);
}
