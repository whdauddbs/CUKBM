import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class JoinMatch {
	
	private String date;
	private String time_stamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(new Date()); // 참가 버튼을 누른 시간
	private String id;
	private String m_name;
	
	public JoinMatch() {}
	
	public void setM_Name(String m_name) {
		this.m_name=m_name;
	}
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root123");
			if(conn==null) {
				throw new Exception("JoinMatch : DB연결 실패");
			}
			String sql = "INSERT INTO p_match (m_name, id, date) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_name);
			pstmt.setString(2,  id);
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
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC", "root", "root123");
			if(conn==null) {
				throw new Exception("JoinMatch : DB연결 실패");
			}
			String sql ="UPDATE match_info SET c_number=c_number+1 WHERE date=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  date);
			
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			
			sql = "select * from match_info where date=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,date);
			rs = pstmt.executeQuery();
			rs.next();
			String c_id = rs.getString("id");
			Integer c_number = rs.getInt("c_number");
			Integer m_number = rs.getInt("m_number");
			pstmt.close();
			if (c_number==m_number) {
				sql = "insert into alert values(?,?,?,0)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,c_id);
				pstmt.setString(2,"생성한 방에 참가인원이 모두 찼습니다.");
				pstmt.setString(3,time_stamp);
				pstmt.executeUpdate();
			}
			else {
				sql = "insert into alert values(?,?,?,0)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,c_id);
				pstmt.setString(2,"생성한 방에 참가자가 입장하였습니다.");
				pstmt.setString(3,time_stamp);
				pstmt.executeUpdate();
			}
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
				if(rs!=null) rs.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
