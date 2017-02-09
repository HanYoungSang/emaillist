package com.bit2017.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2017.emaillist.vo.EmailListVo;

public class EmailListDao {
	
	public List<EmailListVo> getList() {
		List<EmailListVo> list = new ArrayList<EmailListVo>();
	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Loading ( JDBC Class Loading )
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
			//2. Connection 얻어오기( Connect to DB )
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL문 실행
			String sql = "select no, first_name, last_name, email from emaillist order by no";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			//4. 결과 처리
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String firstName = rs.getString( 2 );
				String lastName = rs.getString( 3 );
				String email = rs.getString( 4 );
				
				EmailListVo vo = new EmailListVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				list.add( vo );
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println( "error: 드라이버 로딩 실패 - " + e );
		} catch ( SQLException e ) {
			System.out.println( "error:" + e );
		} finally {
			//3. 자원정리
			try {
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException e ) {
				System.out.println( "error:" + e );
			}
		}				
		
		
		return list;
	}
	
	public boolean insert( EmailListVo emailListVo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. JDBC Driver Loading ( JDBC Class Loading )
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
			//2. Connection 얻어오기( Connect to DB )
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL문 준비
			String sql = 
				" insert" +
				"   into emaillist" +
				" values ( seq_emaillist.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString( 1, emailListVo.getFirstName() );
			pstmt.setString( 2, emailListVo.getLastName() );
			pstmt.setString( 3, emailListVo.getEmail());
			
			//5. SQL문 실행
			int count = pstmt.executeUpdate();
			
			//6. 결과
			return count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println( "error: 드라이버 로딩 실패 - " + e );
			return false;
		} catch ( SQLException e ) {
			System.out.println( "error:" + e );
			return false;
		} finally {
			//3. 자원정리
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException e ) {
				System.out.println( "error:" + e );
			}
		}		
	}
}
