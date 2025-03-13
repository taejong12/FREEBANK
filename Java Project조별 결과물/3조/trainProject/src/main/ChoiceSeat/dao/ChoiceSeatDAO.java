package main.ChoiceSeat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoiceSeatDAO {
	Connection con;

	public ChoiceSeatDAO() {
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
	
	
	public List<String> getReservedSeats(String trainNumber) {
	    List<String> reservedSeats = new ArrayList<>();
	    String sql = "SELECT SEAT_NUMBER FROM Seat WHERE TR_NUMBER = ? AND IS_RESERVED = 'Y'";

	    try (
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, trainNumber);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            reservedSeats.add(rs.getString("SEAT_NUMBER")); // 예약된 좌석 추가
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return reservedSeats;
	}
	
	public boolean reserveSeat(String seatNumber, String trainNumber, String userId) {
	    String checkSql = "SELECT COUNT(*) FROM Seat WHERE TR_NUMBER = ? AND SEAT_NUMBER = ? AND IS_RESERVED = 'Y'";
	    String updateSql = "UPDATE Seat SET IS_RESERVED = 'Y' WHERE TR_NUMBER = ? AND SEAT_NUMBER = ?";

	    try (
	         PreparedStatement checkStmt = con.prepareStatement(checkSql)) {

	        // 예약된 좌석인지 확인
	        checkStmt.setString(1, trainNumber);
	        checkStmt.setString(2, seatNumber);
	        ResultSet rs = checkStmt.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            return false; // 이미 예약된 좌석이면 false 반환
	        }

	        // 좌석 예약 처리
	        try (PreparedStatement updateStmt = con.prepareStatement(updateSql)) {
	            updateStmt.setString(1, trainNumber);
	            updateStmt.setString(2, seatNumber);
	            updateStmt.executeUpdate();
	        }
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
//    // 좌석 예약 처리
//    public boolean reserveSeat(String seatNumber, String trNumber) {
//        String sql = "UPDATE Seat SET IS_RESERVED = 'Y' WHERE SEAT_NUMBER = ? AND TR_NUMBER = ?";
//        
//        try (
//             PreparedStatement stmt = con.prepareStatement(sql)) {
//            
//            stmt.setString(1, seatNumber);  // 선택된 좌석 번호
//            stmt.setString(2, trNumber);    // 기차 번호
//            
//            int rowsUpdated = stmt.executeUpdate();
//            if (rowsUpdated > 0) {
//                System.out.println(seatNumber + " 좌석 예약 성공");
//                return true;  // 예약 성공
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//        return false;  // 예약 실패
//    }
}
