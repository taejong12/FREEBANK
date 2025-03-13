package main.board;

import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.shop.ShopController;
import main.user.UserDTO;

public class BoardServiceImpl implements BoardService {

	BoardDAO bd = new BoardDAO();
	
	// 공지사항 목록 조회
	public List<BoardDTO> selectBoardList() {
		return bd.selectBoardList();
	}

	// 메인 페이지(비회원) 공지사항 목록 상세 페이지
	public void boardMainDetailPage(Parent root, BoardDTO boardDTO, UserDTO userDTO) {
		Stage boardMainDetailPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/board/boardMainDetailPage.fxml"));

		try {

			Parent boardMainDetailPageRoot = loader.load();

			BoardController boardMainDetailPageCtrl = loader.getController();

			boardMainDetailPageCtrl.setRoot(boardMainDetailPageRoot);
			boardMainDetailPageCtrl.setUser(userDTO);
			boardMainDetailPageCtrl.setBoard(boardDTO);
			// 공지사항 상세페이지 정보
			boardMainDetailPageCtrl.mainDetailBoardInfo();
			

			boardMainDetailPage.setScene(new Scene(boardMainDetailPageRoot));
			boardMainDetailPage.setTitle("공지사항 상세페이지(메인페이지(비회원))");
			boardMainDetailPage.show();


		} catch (Exception e) {
			System.out.println("공지사항 상세페이지(메인페이지(비회원)) 출력 실패");
			e.printStackTrace();
		}
	}

	// 메인 페이지(회원) 공지사항 목록 상세 페이지
	public void boardMainUserDetailPage(Parent root, BoardDTO boardDTO, UserDTO userDTO) {
		Stage boardMainUserDetailPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/board/boardMainUserDetailPage.fxml"));

		try {

			Parent boardMainUserDetailPageRoot = loader.load();

			BoardController boardMainUserDetailPageCtrl = loader.getController();

			boardMainUserDetailPageCtrl.setRoot(boardMainUserDetailPageRoot);
			boardMainUserDetailPageCtrl.setUser(userDTO);
			boardMainUserDetailPageCtrl.setBoard(boardDTO);
			// 공지사항 상세페이지 정보
			boardMainUserDetailPageCtrl.mainUserDetailBoardInfo();
			boardMainUserDetailPageCtrl.loginUserId();
			
			boardMainUserDetailPage.setScene(new Scene(boardMainUserDetailPageRoot));
			boardMainUserDetailPage.setTitle("공지사항 상세페이지(메인페이지(회원))");
			boardMainUserDetailPage.show();


		} catch (Exception e) {
			System.out.println("공지사항 상세페이지(메인페이지(회원)) 출력 실패");
			e.printStackTrace();
		}
	}
}