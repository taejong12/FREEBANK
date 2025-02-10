package main.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.account.AccountDTO;
import main.board.BoardDTO;
import main.shop.ShopDTO;

public class AdminDAO {

	Connection con;

	public AdminDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "c##FREEBANK";
			String pass = "1234";
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오라클 연결 실패");
		}
	}

	// 공지사항 등록
	public int insertAdminBoard(BoardDTO boardDTO) {

		int result = 0;

		String sql = "insert into FREEBANKBOARD(FREEBANKBOARD_TITLE, FREEBANKBOARD_AUTHOR, FREEBANKBOARD_CONTENT) values(?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getBoardTitle());
			pstmt.setString(2, boardDTO.getBoardAuthor());
			pstmt.setString(3, boardDTO.getBoardContent());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("공지사항 등록 실패");
			e.printStackTrace();
		}

		return result;
	}

	// 공지사항 목록 조회
	public List<BoardDTO> selectAdminBoardList() {

		List<BoardDTO> boardList = new ArrayList<BoardDTO>();

		String sql = "select * from FREEBANKBOARD order by FREEBANKBOARD_ID desc";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			// ResultSet : SQL 에서 가져오는 값 저장 객체
			ResultSet rs = pstmt.executeQuery();

			// ResultSet.next()
			// - 값이 있으면 true, 없으면 false
			while (rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				// ResultSet.get데이터타입(컬럼순서)
				// ResultSet.get데이터타입("컬럼이름");
				boardDTO.setBoardId(rs.getInt(1));
				boardDTO.setBoardTitle(rs.getString(2));
				boardDTO.setBoardAuthor(rs.getString(3));
				boardDTO.setBoardContent(rs.getString(4));
				boardDTO.setBoardCreated(rs.getDate(5));
				boardDTO.setBoardUpdate(rs.getDate(6));
				boardList.add(boardDTO);
			}

		} catch (Exception e) {
			System.out.println("공지사항이 존재하지 않습니다.");
			e.printStackTrace();
		}

		return boardList;
	}

	// 공지사항 삭제하기
	public int deleteAdminBoard(int boardId) {

		int result = 0;

		String sql = "delete from FREEBANKBOARD where FREEBANKBOARD_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardId);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("공지사항 삭제 실패");
			e.printStackTrace();
		}

		return result;
	}

	// 공지사항 수정하기
	public int updateAdminBoard(BoardDTO boardDTO) {

		int result = 0;

		String sql = "update FREEBANKBOARD set FREEBANKBOARD_TITLE=?, FREEBANKBOARD_AUTHOR=?, FREEBANKBOARD_CONTENT=? where FREEBANKBOARD_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getBoardTitle());
			pstmt.setString(2, boardDTO.getBoardAuthor());
			pstmt.setString(3, boardDTO.getBoardContent());
			pstmt.setInt(4, boardDTO.getBoardId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("공지사항 수정 실패");
			e.printStackTrace();
		}

		return result;

	}

	public List<AccountDTO> selectAccountListAll() {
		List<AccountDTO> accountList = new ArrayList<AccountDTO>();
		String sql = "select * from FREEBANKACCOUNT order by FREEBANKACCOUNT_CREATE desc";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			// ResultSet : SQL 에서 가져오는 값 저장 객체
			ResultSet rs = pstmt.executeQuery();

			// ResultSet.next()
			// - 값이 있으면 true, 없으면 false
			while (rs.next()) {
				AccountDTO accountDTO = new AccountDTO();
				// ResultSet.get데이터타입(컬럼순서)
				// ResultSet.get데이터타입("컬럼이름");
				accountDTO.setAccountAccount(rs.getString(1));
				accountDTO.setAccountBalance(rs.getInt(2));
				accountDTO.setAccountCreate(rs.getDate(3));
				accountDTO.setAccountId(rs.getString(4));
				accountList.add(accountDTO);
			}

		} catch (Exception e) {
			System.out.println("계좌가 존재하지 않습니다.");
			e.printStackTrace();
		}
		
		return accountList;
	}

}
