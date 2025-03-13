package main.exam01.common;

import java.sql.Connection;
import java.sql.DriverManager;

import main.exam01.service.GymService;

public class CommonDAO {
	Connection con;
	public CommonDAO() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "gym";
			String pass = "1234";

			con = DriverManager.getConnection(url,user,pass);
			if (con != null) {
	            System.out.println("DB 연결 성공");
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 System.out.println("DB 연결 실패");
		}	
	}
	public static Connection getConnection(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return null;
	}
}
