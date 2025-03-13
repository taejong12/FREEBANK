package main.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
	Connection con;

	public LoginDAO() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName
			("oracle.jdbc.driver.OracleDriver");
			String url = 
					"jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "c##user01";
			String pass = "1234";

			con = DriverManager.
					getConnection(url,user,pass);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public boolean loginChk(String id, String pwd) {
		// TODO Auto-generated method stub
		String sql = "select decode(count(*), 1, 'true', 'false') from MEMBER where MEM_ID=? and MEM_PW=?";
		boolean result = false;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);   //
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			ResultSet rs = pstmt.executeQuery();


			if (rs.next()) {
				result = Boolean.parseBoolean(rs.getString(1));
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public String getUserName(String id, String pwd) {
        String userName = null;
        String sql = "SELECT MEM_NAME FROM MEMBER WHERE MEM_ID = ? AND MEM_PW = ?";

        try (
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userName = rs.getString(1); // 사용자 이름 가져오기
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }

	public String getUserId(String id, String pwd) {
		// TODO Auto-generated method stub
		String userId = null;
        String sql = "SELECT MEM_ID FROM MEMBER WHERE MEM_ID = ? AND MEM_PW = ?";

        try (
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
            	userId = rs.getString(1); // 사용자 이름 가져오기
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
	}

	public String getUserAddress(String id, String pwd) {
		// TODO Auto-generated method stub
		String userAddress = null;
        String sql = "SELECT MEM_ADDRESS FROM MEMBER WHERE MEM_ID = ? AND MEM_PW = ?";

        try (
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
            	userAddress = rs.getString(1); // 사용자 이름 가져오기
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userAddress;
	}

	public String getUserPhone(String id, String pwd) {
		// TODO Auto-generated method stub
		String userPhone = null;
        String sql = "SELECT MEM_PHONE FROM MEMBER WHERE MEM_ID = ? AND MEM_PW = ?";

        try (
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
            	userPhone = rs.getString(1); // 사용자 이름 가져오기
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userPhone;
	}
	
	// String SQL = "SELECT MEM_NAME FROM MEMBER WHERE MEM_ID =? AND MEM_PW=?";

}
