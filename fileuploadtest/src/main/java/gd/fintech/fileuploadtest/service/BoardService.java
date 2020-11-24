package gd.fintech.fileuploadtest.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.fileuploadtest.mapper.BoardMapper;
import gd.fintech.fileuploadtest.mapper.BoardfileMapper;
import gd.fintech.fileuploadtest.vo.Board;
import gd.fintech.fileuploadtest.vo.BoardForm;
import gd.fintech.fileuploadtest.vo.Boardfile;
import gd.fintech.fileuploadtest.vo.Comment;

import java.awt.SystemTray;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BoardService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String PATH = "D:\\spring_git\\fileuploadtest\\src\\main\\webapp\\upload\\";
	@Autowired BoardMapper boardMapper;
	@Autowired BoardfileMapper boardfileMapper;
	
	//전체 데이터 양
	public int getTotalRow() {
		return boardMapper.totalRow();
	}
	//한개의 리스트가 갖고있는 댓글 총 수
	public int getTotalBoardOneComment(int boardId) {
		return boardMapper.totalBoardOneComment(boardId);
	}
	//페이징 작업한 리스트 출력
	public List<Board> getBoardListByPage(int currentPage,int rowPerPage){
		//페이징작업
		
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String,Object> map = new HashMap<>();
		map.put("beginRow",beginRow);
		map.put("rowPerPage", rowPerPage);
		
		return boardMapper.selectBoardListByPage(map);
	}
	
	//리스트 상세보기
	public Board getBoardOne(int boardId, int currentPage, int rowPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		int beginRow = (currentPage-1)*rowPerPage;
		map.put("beginRow", beginRow);
		map.put("rowPerPage",rowPerPage);
		map.put("boardId", boardId);
		return boardMapper.selectBoardOne(map);
	}
	
	//보드 추가 (파일 경로 및 저장 기능)
	public void addBoard(BoardForm boardForm) {
		Board board = new Board(); // 프로토 타입에서는 new 연산자를 사용하는 것이 좋다
		board.setBoardTitle(boardForm.getBoardTitle());
		board.setBoardContent(boardForm.getBoardContent());
		
		//1. board db 입력 -> key값 받음
		
		List<Boardfile> boardfile = null; // BoardForm 데이터 타입에 List<MultipartFile> boardfile-> 불러올 파일이 여러개일 수 있기 때문에
		
		if(boardForm.getBoardfile() != null) {
			boardfile = new ArrayList<Boardfile>();
			
			boardMapper.insertBoard(board);
			
			for(MultipartFile mf : boardForm.getBoardfile()) {
				Boardfile bf = new Boardfile();
				bf.setBoardId(board.getBoardId()); // select Key로 받아온 id
				
				int p = mf.getOriginalFilename().lastIndexOf("."); //aaa.txt -> .의 위치 번호 = int
				String ext = mf.getOriginalFilename().substring(p).toLowerCase(); // '.txt' 저장
				String filename = UUID.randomUUID().toString().replace("-", "");  // UUID를 사용하면 가독에 어렵게 ㅁㅁ-ㅁ--ㅁ A-형식으로 나오기 때문에 '-','' 제거
				// UUID는 Universally Unique IDentifier의 약어이고 범용 고유 식별자 =>  실제 사용 상에서 중복될 가능성이 거의 없다고 인정되기 때문에 많이 사용
				bf.setBoardfileName(filename+ext);
				bf.setBoardfileSize((int)mf.getSize());
				bf.setBoardfileType(mf.getContentType());
				boardfile.add(bf);
				logger.debug("for 문 :" + bf);
				
				//파일저장
				try {
					mf.transferTo(new File(this.PATH+filename+ext)); // 생성경로 지정
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
	//파일 삭제
	public void removeBoardOne(int boardfileNo) {
		String boardfileList = boardfileMapper.selectDeleteBoardFileName(boardfileNo);
		File file = new File(this.PATH+boardfileList);
		if(file.exists()) {
			file.delete();
		}
		boardfileMapper.deleteBoardfileOne(boardfileNo); // db에 파일 이름 삭제	
	}

	//리스트 삭제
	public void removeBoard(int boardId) {
		//1. 게시글 참조하는 파일들을 삭제
		List<String> boardfileList = boardfileMapper.selectBoardFileNameList(boardId);
		for(String s : boardfileList) {
			File file = new File(this.PATH+s);
			if(file.exists()) {
				file.delete();
			}
		}
		//2. 게시글 참조하는 파일테이블 데이터 삭제
		boardfileMapper.deleteBoardfile(boardId);
		//3. 게시글 삭제
		boardMapper.deleteBoard(boardId);
	}
	
	//리스트 수정 폼
	public Board updateBoardForm(int boardId) {
		return boardMapper.selectUpdateBoardForm(boardId);
	}
	
	//리스트 수정 액션
	public void updateBoard(BoardForm boardForm,int boardId) {
		Board board = new Board();
		List<Boardfile> boardfile = null;
		
		//board 수정
		board.setBoardId(boardId);
		board.setBoardTitle(boardForm.getBoardTitle());
		board.setBoardContent(boardForm.getBoardContent());
		boardMapper.updateBoard(board);
		
		if(boardForm.getBoardfile() != null) {
			boardfile = new ArrayList<Boardfile>();
			
			for(MultipartFile mf : boardForm.getBoardfile()) {
				Boardfile bf = new Boardfile();
				bf.setBoardId(boardId);
				
				int p = mf.getOriginalFilename().lastIndexOf("."); //aaa.txt -> .의 위치 번호 = int
				String ext = mf.getOriginalFilename().substring(p).toLowerCase(); // '.txt' 저장
				String filename = UUID.randomUUID().toString().replace("-", "");  // UUID를 사용하면 가독에 어렵게 ㅁㅁ-ㅁ--ㅁ A-형식으로 나오기 때문에 '-','' 제거
				// UUID는 Universally Unique IDentifier의 약어이고 범용 고유 식별자 =>  실제 사용 상에서 중복될 가능성이 거의 없다고 인정되기 때문에 많이 사용
				bf.setBoardfileName(filename+ext);
				bf.setBoardfileSize((int)mf.getSize());
				bf.setBoardfileType(mf.getContentType());
				boardfile.add(bf);
				logger.debug("for 문 :" + bf);
				
				//파일저장
				try {
					mf.transferTo(new File(this.PATH+filename+ext)); // 생성경로 지정
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



















