package main.pay;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Text;




public class Dao {
	Connection con;
	Dto dto = new Dto();
	public Dao() {
		// TODO Auto-generated constructor stub
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		// 오라클 서버 접속 링크
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "C##user01";
		String pwd = "1234";
		con = DriverManager.getConnection(url,user,pwd);
		System.out.println("연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public boolean loginChek(String id, String pw) {
		// TODO Auto-generated method stub
		String sql = "select decode(count(*), 1, 'true', 'false') from member where id = ? and pw = ? ";
		boolean result = false;
		try {
			PreparedStatement pstmt = 
					con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				result = Boolean.parseBoolean(rs.getString(1));
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	public List<Dto> selecAll() {
		// TODO Auto-generated method stub
		String sql = "select * from pay";
		List<Dto> payList =
				new ArrayList<Dto>();
		try {
			PreparedStatement pstmt =
					con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return payList;
	}
	public boolean deliveryCheck(int delinum) {
		String sql = "select decode(count(*), 1, 'true', 'false') from pay where deliverynum = ?";
		boolean result = false;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, delinum);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = Boolean.parseBoolean(rs.getString(1));
			}
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	public void deliDate() {
		String sql = "insert into pay "
				+ "(deliverynum,paydate) values (cus_delinum.nextval, sysdate)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			
			int result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void deliveryNum(Dto d) {
		String sql = "SELECT max(deliverynum) FROM pay";
			
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int deliveryNum = rs.getInt(1);
				
				System.out.println(deliveryNum);
			d.setDeliveryNum(deliveryNum+1);
			
			}
			pstmt.close();
			 
		
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void cardsave(long card, int cvc) {
		String sql = "insert into abc_user (cardnum,cvc,money) values (?,?,200000) ";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, card);
			pstmt.setInt(2, cvc);
			int result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public boolean cardchk(long card) {
		String sql = "select decode(count(*), 1, 'true', 'false') from abc_user where cardnum = ?";
		boolean result = false;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, card);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = Boolean.parseBoolean(rs.getString(1));
			}
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	public int cardmoney(long card) {
		String sql = "select money from abc_user where cardnum = ?";
		int result=0; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, card);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
			 result = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}
	public void total(int total,int card) {
		String sql = "update abc_user set money = ? where cardnum = ? ";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, total);
			pstmt.setInt(2, card);
			
			int result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	

}
}