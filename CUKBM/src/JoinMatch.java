import java.sql.*;
public class JoinMatch {
	
	private String date;
	private String id;
	private String m_name;
	
	public JoinMatch() {}
	
	public void setDate(String date) {
		this.date=date;
	}
	public void setId(String id) {
		this.id=id;
	}
	public void InsertPMatch() {
		Connection conn = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone?UTC", "root", "root123");
			if(conn==null) {
				throw new Exception("JoinMatch : DB연결 실패");
			}
			String sql = "INSERT INTO p_match (m_name, id, date) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_name);
			pstmt.setString(1,  id);
			pstmt.setString(3,  date);
			
			int result = pstmt.executeUpdate();
			System.out.printf("insert 반환값 :%d\n", result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
//				if(rs!=null) rs.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public void UpdateMatchInfo() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC", "root", "root123");
			if(conn==null) {
				throw new Exception("JoinMatch : DB연결 실패");
			}
			String sql ="UPDATE match_info SET c_number=c_number+1 WHERE date=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  date);
			
			int result = pstmt.executeUpdate();
			//  반환값??
			System.out.println("UPDATE 반환값 :"+result);
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
//				if(rs!=null) rs.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
