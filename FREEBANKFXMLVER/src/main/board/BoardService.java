package main.board;

import java.util.List;

import javafx.scene.Parent;
import main.user.UserDTO;

public interface BoardService {

	public List<BoardDTO> selectBoardList(); // 공지사항 목록 조회

	public void boardMainDetailPage(Parent root, BoardDTO boardDTO, UserDTO userDTO); // 메인 페이지(비회원) 공지사항 목록 상세 페이지

	public void boardMainUserDetailPage(Parent root, BoardDTO boardDTO, UserDTO userDTO); // 메인 페이지(회원) 공지사항 목록 상세 페이지
}
