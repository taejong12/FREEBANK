package main.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {

	Connection con;

	public ShopDAO() {
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

	// 쇼핑몰 리스트
	public List<ShopDTO> selectShopList() {
		List<ShopDTO> shopList = new ArrayList<ShopDTO>();

		String sql = "select * from FREEBANKSHOP order by FREEBANKSHOP_ID desc";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			// ResultSet : SQL 에서 가져오는 값 저장 객체
			ResultSet rs = pstmt.executeQuery();

			// ResultSet.next()
			// - 값이 있으면 true, 없으면 false
			while (rs.next()) {
				ShopDTO shopDTO = new ShopDTO();
				// ResultSet.get데이터타입(컬럼순서)
				// ResultSet.get데이터타입("컬럼이름");
				shopDTO.setShopId(rs.getInt(1));
				shopDTO.setShopName(rs.getString(2));
				shopDTO.setShopContents(rs.getString(3));
				shopDTO.setShopPrice(rs.getInt(4));
				shopDTO.setShopAdminId(rs.getString(5));
				shopDTO.setShopCreate(rs.getDate(6));
				shopDTO.setShopUpdate(rs.getDate(7));
				shopList.add(shopDTO);
			}

		} catch (Exception e) {
			System.out.println("조회할 상품이 존재하지 않습니다.");
			e.printStackTrace();
		}
		return shopList;
	}

}
