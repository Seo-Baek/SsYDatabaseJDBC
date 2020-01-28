package com.sist;

import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EMP extends JFrame{
	
	// DB 연동 결과를 저장
	Connection con = null;
	// 쿼리문 전송 객체 설정
	PreparedStatement pstmt = null;
	// 쿼리문 실행 결과 객체 설정
	ResultSet rs = null;
				
	DefaultTableModel model = null;
	
	
	
	public EMP() {
		
		JPanel jp = new JPanel();
		
		String[] title = {"사번", "이름", "담당업무"
				           , "관리자No.", "입사일", "급여", "보너스", "부서번호"};
		model = new DefaultTableModel(title, 0);
		
		
		connect();	// DB 연동하는 메소드
		select("select * from emp");	// DB에서 전체내역 조회하는 메소드
		
		// 1. 드라이버 로딩
		
		// 2. DB 연동
		
		// 3. SQL문 작성
		
		// 4. DB에 쿼리문 전송
		
		// 5. 쿼리문 실행
		
		
		JTable table = new JTable(model);
		
		JScrollPane jsp = new JScrollPane(table, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(jsp);
		
		setBounds(100, 100, 500, 500);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	

	public static void main(String[] args) {
		new EMP();
	}


	// DB 연동하는 메소드
	private void connect() {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521: xe";
		String user = "genie";
		String password = "1234";
		
		try {
			// 1.접속할 드라이버를 메모리에 올리자 - 정적메소드
			Class.forName(driver);
			
			// 2. 데이터베이스와 연결하자
			con = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// 전체 내역 조회하는 메소드
	private void select(String query) {
		
		try {
			// 3, 쿼리문을 전송하자
			pstmt = con.prepareStatement(query);

			// 5. 실제 DB 상에서 쿼리문을 실행하도록 하자.
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	// 레코드 수만큼 반복
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");
				
				Object[] data = { empno, ename, job, mgr, hiredate, sal, comm, deptno};
				
				// 저장한 한 개의 레코드(data)를 model에 넣어준다.
				model.addRow(data);
				
			}
			
			// open 객체 닫기
			rs.close(); pstmt.close(); con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
