package main.Payment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class PaymentDAO {
	Connection con;

	public PaymentDAO() {
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

	public void insertData(String today, String selectedTrainDeparture, String selectedTrainDestination, String price, String seat, String userId) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO PAY values(?,?,?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);   // insert into member values(?,?,?,?,?,?,?);
			pstmt.setString(1, today);
			pstmt.setString(2, selectedTrainDeparture);
			pstmt.setString(3, selectedTrainDestination);
			pstmt.setString(4, price);
			pstmt.setString(5, seat);
			pstmt.setString(6, userId);
			
			int result = pstmt.executeUpdate();

			pstmt.close();
			if(result >= 1) {

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}
}
