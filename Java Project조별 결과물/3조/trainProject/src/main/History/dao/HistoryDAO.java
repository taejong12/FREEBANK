package main.History.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.History.dto.History;
import main.searchtrain.dto.Searchtrain;

public class HistoryDAO {
	Connection con;

	public HistoryDAO() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "c##user01";
			String pass = "1234";

			con = DriverManager.getConnection(url,user,pass);
			System.out.println("DB연결성공");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public List<History> searchPayment(String userId) {
		List<History> HistoryList = new ArrayList<History>();
	    String sql = "SELECT * FROM PAY WHERE MEM_ID = ?"; 
	    try {
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, userId);
	       
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	History h = new History();
	            h.setTr_day(rs.getString(1));
	            h.setTr_local(rs.getString(2));
	            h.setTr_local2(rs.getString(3));
	            h.setTr_price(rs.getString(4));
	            h.setTr_seat(rs.getString(5));
	          

	            HistoryList.add(h);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // 오류 메시지 확인
	    }
	    return HistoryList;
	}
}
