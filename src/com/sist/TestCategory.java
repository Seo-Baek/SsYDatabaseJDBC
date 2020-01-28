package com.sist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestCategory {

	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try {
			// 드라이버 설치
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "genie", "1234");
			pre = con.prepareStatement("select * from category");
			rs = pre.executeQuery();
			
			while(rs.next()) {
				int cnum = rs.getInt("cnum");
				String category_code = rs.getString("category_code");
				String category_name = rs.getString("category_name");
				char delete_chk = rs.getString("delete_chk").charAt(0);
				System.out.println(cnum + " / " +
						category_code + " / " +
						category_name + " / " +
						delete_chk);
			}
			rs.close(); pre.close(); con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
