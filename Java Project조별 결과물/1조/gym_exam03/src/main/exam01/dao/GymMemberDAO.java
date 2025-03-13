package main.exam01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.exam01.common.CommonService;
import main.exam01.common.DatabaseConnection;
import main.exam01.dto.GymMember;

public class GymMemberDAO {
	Connection con;
	CommonService ls;
	
	public GymMemberDAO() {
		// TODO Auto-generated constructor stub
		try {
            if (con == null || con.isClosed()) {
                // DB 연결 코드 (예시: JDBC 연결)
                String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
                String user = "c##gym";
                String password = "1234";
                con = DriverManager.getConnection(url,user,password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
    // 회원이 로그인 했을때 데이터 가져오는 코드
    public GymMember getMemberById(String id) {
        GymMember member = null;
        String sql = "SELECT * FROM gym_member WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // 데이터베이스에서 값을 가져와서 GymMember 객체에 설정
                member = new GymMember(
                    rs.getString("id"),
                    rs.getString("pwd"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getDouble("height"),
                    rs.getDouble("weight"),
                    rs.getDouble("bodyfat"),
                    rs.getDouble("met_consumption"),
                    rs.getDouble("growthfactor_consumption"),
                    rs.getDouble("total_met_consumption"),
                    rs.getDouble("total_growthfactor_consumption"),
                    rs.getString("mem_type")
                
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return member;
    }
	
	
}
