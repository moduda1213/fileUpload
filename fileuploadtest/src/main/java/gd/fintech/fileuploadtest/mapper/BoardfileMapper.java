package gd.fintech.fileuploadtest.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.fileuploadtest.vo.Boardfile;

@Mapper
public interface BoardfileMapper {
	int insertBoardfile(Boardfile boardfile);
}
