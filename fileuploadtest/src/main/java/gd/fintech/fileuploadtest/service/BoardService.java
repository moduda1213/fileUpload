package gd.fintech.fileuploadtest.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.fileuploadtest.mapper.BoardMapper;
import gd.fintech.fileuploadtest.mapper.BoardfileMapper;
import gd.fintech.fileuploadtest.vo.Board;
import gd.fintech.fileuploadtest.vo.BoardForm;
import gd.fintech.fileuploadtest.vo.Boardfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BoardService {
		private final Logger logger = LoggerFactory.getLogger(this.getClass());
		@Autowired BoardMapper boardMapper;
		@Autowired BoardfileMapper boardfileMapper;
		
	public void addBoard(BoardForm boardForm) {
		Board board = new Board(); // 프로토 타입에서는 new 연산자를 사용하는 것이 좋다
		board.setBoardTitle(boardForm.getBoardTitle());
		board.setBoardContent(boardForm.getBoardContent());
		
		//1. board db 입력 -> key값 받음
		
		List<Boardfile> boardfile = null;
		if(boardForm.getBoardfile() != null) {
			boardfile = new ArrayList<Boardfile>();
			
			boardMapper.insertBoard(board);
			
			for(MultipartFile mf : boardForm.getBoardfile()) {
				Boardfile bf = new Boardfile();
				bf.setBoardId(board.getBoardId());
				
				int p = mf.getOriginalFilename().lastIndexOf(".");
				String ext = mf.getOriginalFilename().substring(p).toLowerCase();
				String filename = UUID.randomUUID().toString().replace("-", "");
				
				bf.setBoardfileName(filename+ext);
				bf.setBoardfileSize((int)mf.getSize());
				bf.setBoardfileType(mf.getContentType());
				boardfile.add(bf);
				logger.debug("for 문 :" + bf);
				
				//파일저장
				try {
					mf.transferTo(new File("D:\\springwork\\fileuploadtest\\src\\main\\webapp\\upload\\"+filename+ext));
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException(); // @Transactional에게 RuntimeException을 던져준다.
				}
			}
		}
		if(boardfile != null) {
			for(Boardfile bf : boardfile) {
				boardfileMapper.insertBoardfile(bf);
			}
			//2. boardfile db 입력
			//3. boardfile.size() 횟수만큼 입력
			//4. 입력시 boardId 1번에서 받은 키값 사용
		}
	}
}


















