package com.sist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestEmp {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "genie";
		String password = "1234";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pre = con.prepareStatement("select * from emp");
			rs = pre.executeQuery();
			
			while(rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");
				System.out.println(empno + "/" +
								ename + "/" +
								job + "/" +
								mgr + "/" +
								hiredate + "/" +
								sal + "/" +
								comm + "/" +
								deptno + "/" );
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
