package main.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardServiceImpl implements BoardService {

	Scanner sc;
	List<BoardDTO> boardList;
	BoardDAO boardDAO;
	BoardDTO board;

	public BoardServiceImpl() {
		sc = new Scanner(System.in);
		boardList = new ArrayList<BoardDTO>();
		boardDAO = new BoardDAO();
		board = new BoardDTO();
	}

	// 게시글 조회
	public void boardOutput() {
		boardList = boardDAO.boardSelect(); // 리스트로 받아야 함

		if (boardList == null || boardList.isEmpty()) {
			System.out.println("게시글이 없습니다.");
			return;
		}

		System.out.println("=== 게시글 목록 ===");
		for (BoardDTO board : boardList) {
			System.out.println("게시글 번호: " + board.getId());
			System.out.println("제목: " + board.getTitle());
			System.out.println("내용: " + board.getContent());
			System.out.println("작성 날짜: " + board.getCreated());
			System.out.println("수정 날짜: " + board.getUpdate());
			System.out.println("----------------------");
		}
	}

}
