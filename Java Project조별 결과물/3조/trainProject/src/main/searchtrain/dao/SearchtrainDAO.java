package main.searchtrain.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.searchtrain.dto.Searchtrain;

public class SearchtrainDAO {
	Connection con;

	public SearchtrainDAO() {
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


	public List<Searchtrain> searchtrain(String trainName, String trainDeparture, String trainDestination) {
		List<Searchtrain> trainList = new ArrayList<Searchtrain>();
	    String sql = "select tr_number, tr_name, tr_local, tr_local2, tr_leave, tr_arrive, tr_time, tr_free, tr_price from train where tr_name = ? and tr_local =? and tr_local2 = ?";
	    
	    try {
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, trainName); //무궁화호
	        pstmt.setString(2, trainDeparture); 
	        pstmt.setString(3, trainDestination);

	        System.out.println("📢 검색 요청한 열차 이름: " + trainName); // 입력값 디버깅

	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Searchtrain s = new Searchtrain();
	            s.setTr_number(rs.getString(1));
	            s.setTr_name(rs.getString(2));
	            s.setTr_local(rs.getString(3));
	            s.setTr_local2(rs.getString(4));
	            s.setTr_leave(rs.getString(5));
	            s.setTr_arrive(rs.getString(6));
	            s.setTr_time(rs.getString(7));
	            s.setTr_free(rs.getString(8));
	            s.setTr_price(rs.getString(9));
	           

	            System.out.println("✅ 검색된 열차: " + s.getTr_name()); // 값 확인
	            trainList.add(s);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // 오류 메시지 확인
	    }
	    return trainList;
	}
}
