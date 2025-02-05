package main.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
   Connection con;
   UserDTO user;

   public UserDAO() {
      user = new UserDTO();
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

   public int userRegister(UserDTO u) {
      String sql = "insert into FREEBANKUSER values(?,?,?,?,?,?,?,?,?)";
      try {
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, u.getUserId());
         pstmt.setString(2, u.getUserName());
         pstmt.setString(3, u.getUserPwd());
         pstmt.setInt(4, u.getUserAge());
         pstmt.setString(5, u.getUserSex());
         pstmt.setString(6, u.getUserEmail());
         pstmt.setString(7, u.getUserAdmin());
         pstmt.setInt(8, u.getUserCreditRating());
         pstmt.setInt(9, u.getUserTotal());

         int result = pstmt.executeUpdate();
         return result;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return 0;
   }

   public List<UserDTO> userSelect() {
      List<UserDTO> userList = new ArrayList<UserDTO>();
      String sql = "select * from FREEBANKUSER"; 
      try {
         PreparedStatement pstmt = con.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            UserDTO u = new UserDTO();
            u.setUserId(rs.getString(1));
            u.setUserName(rs.getString(2));
            u.setUserPwd(rs.getString(3));
            u.setUserAge(rs.getInt(4));
            u.setUserSex(rs.getString(5));
            u.setUserEmail(rs.getString(6));
            u.setUserAdmin(rs.getString(7));
            u.setUserCreditRating(rs.getInt(8));
            u.setUserTotal(rs.getInt(9));
            userList.add(u);
         }
         return userList;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   public UserDTO userSelectId(String id) {
      String sql = "select * from FREEBANKUSER where FREEBANKUSER_ID = ?";
      try {
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            UserDTO u = new UserDTO();
            u.setUserId(rs.getString(1));
            u.setUserName(rs.getString(2));
            u.setUserPwd(rs.getString(3));
            u.setUserAge(rs.getInt(4));
            u.setUserSex(rs.getString(5));
            u.setUserEmail(rs.getString(6));
            u.setUserAdmin(rs.getString(7));
            u.setUserCreditRating(rs.getInt(8));
            u.setUserTotal(rs.getInt(9));
            return u;
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   public int UserUpdate(UserDTO user) {
      String sql = "update FREEBANKUSER set FREEBANKUSER_PWD=?,  FREEBANKUSER_EMAIL=?  where FREEBANKUSER_ID=?";
      try {
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, user.getUserPwd());
         pstmt.setString(2, user.getUserEmail());
         pstmt.setString(3, user.getUserId());

         int result = pstmt.executeUpdate();
         return result;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return 0;
   }

   public int userDelete(String pwd) {
      String sql = "delete from FREEBANKUSER where FREEBANKUSER_PWD=?";
      try {
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, pwd);

         int result = pstmt.executeUpdate();
         return result;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return 0;
   }
}
