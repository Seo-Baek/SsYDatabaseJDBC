package com.sist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class Insert_Dept {

	public static void main(String[] args) {
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,"genie","1234");

			int deptno = Integer.parseInt(JOptionPane.showInputDialog("부서번호 입력"));
			String deptName = JOptionPane.showInputDialog("부서명 입력 : ");
			String loc = JOptionPane.showInputDialog("근무지 입력 : ");
			
			// 3. 쿼리문 작성
			String sql = "insert into dept values(?, ?, ?)";
			
			// 4. 쿼리문 전송
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  deptno);
			pstmt.setString(2,  deptName);
			pstmt.setString(3,  loc);
			
			// 5. 실제 DB 상에서 쿼리문을 실행하도록 하자.
			// 쿼리문이 insert, update, delete인 경우
			//executeUpdate() 메소드 사용 시 성공적으로 DB에 반영되면 1이라는 값을 반환
			int result = pstmt.executeUpdate();   
			if(result == 1) {
				JOptionPane.showMessageDialog(null, "데이터 추가 성공");
			} else {
				JOptionPane.showMessageDialog(null, "데이터 추가 실패");
			}
			
			//open 객체 닫기
			pstmt.close(); con.close();
		} catch (Exception e) {
			e.printStackTrace();
		
		}


	}
}
