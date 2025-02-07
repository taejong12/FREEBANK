package main.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	Connection con;

	public UserDAO() {
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
	
	public void insertUser(UserDTO userDTO) {
		
		String sql = "insert into FREEBANKUSER(FREEBANKUSER_ID, FREEBANKUSER_NAME, FREEBANKUSER_PWD, FREEBANKUSER_AGE, FREEBANKUSER_SEX, FREEBANKUSER_EMAIL) values(?,?,?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUserId());
			pstmt.setString(2, userDTO.getUserName());
			pstmt.setString(3, userDTO.getUserPwd());
			pstmt.setInt(4, userDTO.getUserAge());
			pstmt.setString(5, userDTO.getUserSex());
			pstmt.setString(6, userDTO.getUserEmail());
			

			int result = pstmt.executeUpdate();
			
			if(result >=1) {
				System.out.println("회원가입 완료");
			}
			
		} catch (Exception e) {
			System.out.println("UserDAO.insertUser 에러(회원가입)");
			e.printStackTrace();
		}

		
	}

	public UserDTO selectUserInfoById(String userId) {
		
		UserDTO userDTO = new UserDTO();
		
		String sql = "select * from FREEBANKUSER where FREEBANKUSER_ID = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				userDTO.setUserId(rs.getString(1));
				userDTO.setUserName(rs.getString(2));
				userDTO.setUserPwd(rs.getString(3));
				userDTO.setUserAge(rs.getInt(4));
				userDTO.setUserSex(rs.getString(5));
				userDTO.setUserEmail(rs.getString(6));
				userDTO.setUserAdmin(rs.getString(7));
				userDTO.setUserCreditRating(rs.getInt(8));
				userDTO.setUserTotal(rs.getInt(9));
				System.out.println("회원 조회완료");
			}

		} catch (Exception e) {
			System.out.println("UserDAO.selectUserInfoById 에러(회원정보조회)");
			e.printStackTrace();
		}
		
		return userDTO;
		
		
		
	}

}
