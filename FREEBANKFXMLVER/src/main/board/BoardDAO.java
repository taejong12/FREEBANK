package main.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

	Connection con;

	public BoardDAO() {
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

	// 공지사항 목록 조회
	public List<BoardDTO> selectBoardList() {
		
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
			System.out.println("공지사항 목록조회 실패");
			e.printStackTrace();
		}

		return boardList;
	}

	
	
}
