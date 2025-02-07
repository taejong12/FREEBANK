package main.board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	Connection con;
	BoardDTO board;

	public BoardDAO() {
		board = new BoardDTO();
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

	public List<BoardDTO> boardSelect() {
		List<BoardDTO> boardList = new ArrayList<>();

		// 필요한 컬럼만 명시적으로 선택
		String sql = "SELECT FREEBANKBOARD_ID, FREEBANKBOARD_TITLE, FREEBANKBOARD_AUTHOR, FREEBANKBOARD_CONTENT, FREEBANKBOARD_CREATED, FREEBANKBOARD_UPDATE FROM FREEBANKBOARD";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setId(rs.getInt("FREEBANKBOARD_ID"));
				board.setTitle(rs.getString("FREEBANKBOARD_TITLE"));
				board.setAuthor(rs.getString("FREEBANKBOARD_AUTHOR")); // 작성자 추가
				board.setContent(rs.getString("FREEBANKBOARD_CONTENT"));
				board.setCreated(rs.getDate("FREEBANKBOARD_CREATED"));
				board.setUpdate(rs.getDate("FREEBANKBOARD_UPDATE"));
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return boardList; // null 대신 빈 리스트 반환
	}

}
