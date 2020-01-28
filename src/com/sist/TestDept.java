package com.sist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDept {

	public static void main(String[] args) {
		
		// DB 연동 결과를 저장
		Connection con = null;
		
		// 쿼리문 전송 객체 설정
		PreparedStatement pstmt = null;
		// 쿼리문 실행 결과 객체 설정
		ResultSet rs = null;
			
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521: xe";
		String user = "genie";
		String password = "1234";
		try {
			// 1. 접속할 드라이버를 메모리에 올리자
			Class.forName(driver);
			
			// 2. 데이터베이스와 연결하자(접속URL, 계정명, 계정암호)
			con = DriverManager.getConnection(url, user, password);
			// java.sql.Connection을 말함
			
			if(con != null) {
				System.out.println("DB 연동 성공");
			} else {
				System.out.println("DB 연동 실패");
				
			}
			// 3. 쿼리문을 작성하자.
			//String sql = "select * from dept";
			
			// 4. 쿼리문을 전송하자.
			pstmt = con.prepareStatement("select * from dept");
			
			// 5. 실제 DB상에서 쿼리문을 실행하도록 하자.
			rs = pstmt.executeQuery();	// 실제로 select 쿼리문 실행
			
			System.out.println("deptno |      dname     | loc");
			while(rs.next()) {	// 레코드 수만큼 반복
				int deptno = rs.getInt(1);	// 레코드 인덱스 될 수 있으면 컬럼라벨 붙이는 것이 좋음
				String dname = rs.getString(2);
				String loc = rs.getString(3);
				System.out.println(deptno+ "     | " + dname + "\t| " + loc);
			}
			
			// open된 객체 닫기
			rs.close(); pstmt.close(); con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} /*finally {
			// 예외와 상관없이 실행되는 코드	
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}*/
		
		
				
	}

}
