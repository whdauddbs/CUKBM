import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;

public class ShowMatch {
	//특정 매치를 DB에서 검색하고 가져오는 클래스
	public ShowMatch() {}
	
	private String date;
	private String m_name, id, m_date, detail, event, is_joined;
	private Integer m_number, c_number, is_set, team;
	
	public void setDate(String date) { this.date=date; }
	
	public String getM_name() { return this.m_name; }
	public String getId() { return this.id; }
	public String getDate() { return this.date; }
	public String getM_date() { return this.m_date; }
	public Integer getM_number() { return this.m_number; }
	public Integer getC_number() { return this.c_number; }
	public Integer getIs_set() { return this.is_set; }
	public String getDetail() { return this.detail; }
	public Integer getTeam() { return this.team; }
	public String getEvent() { return this.event; }
	public String getIs_joined() {return this.is_joined; }
	
	public String readDB() {

		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try { 
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC","root","root123");
	        if (conn == null)
	        	throw new Exception("데이터베이스에 연결할 수 없습니다.");
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery("select * from match_info where date = '" + date + "';");
	      
	        if (!rs.next()) {
	        	System.out.println("ShowMatch : 검색결과 없음");
	        	return "fail";
	        }
	        else {
	        	m_name = rs.getString("m_name");
	        	id = rs.getString("id");
	        	m_date = rs.getString("m_date");
	        	date = rs.getString("date");
	        	m_number = new Integer(rs.getInt("m_number"));
	        	c_number = new Integer(rs.getInt("c_number"));
	        	is_set = new Integer(rs.getInt("is_set"));
	        	detail = rs.getString("detail");
	        	team = new Integer(rs.getInt("team"));
	        	event = rs.getString("event");
	        	System.out.println("디비검색결과 확인 :: showMatch.java :"+m_name);
	        }
	        
	        stmt.close();
	        rs.close();
	        conn.close();
	        
	        return "success";
	        
	    }
	    catch (Exception e) {
	    	return e.getMessage();
	    }
	    finally {
	    	if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( stmt != null ) try{stmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
	    }
	}
	public void comparePMatch(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC", "root", "root123");
			if(conn==null) {
				throw new Exception("DB연결 실패 : ShowMatch.java");
			}
			String sql = "SELECT * from p_match WHERE date=? and id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, id);
			System.out.println("**********123 "+date + id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				is_joined = "1";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
		}
	}
	
}
