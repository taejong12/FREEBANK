package main.Mypage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MypageDAO {

	Connection con;

	public MypageDAO() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "c##user01";
			String pass = "1234";

			con = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
	public boolean deleteAccount(String userId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM MEMBER WHERE MEM_ID = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, userId);
			
			int result = pstmt.executeUpdate();

			pstmt.close();
			if(result >= 1) {
				System.out.println("회원탈퇴 완료!");
			return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("회원탈퇴 실패!");
		}
		return false;
	}

}
