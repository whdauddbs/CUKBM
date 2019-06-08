import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyPage {
	
	private String name, id, pw, team, kakao_id;
	private String[] m_name, m_date, m_number, c_number, is_set;
	int db_count_num; //db select한거 개수 편하게 세려고만듬.
	public MyPage() {}
	
	public void setId(String id){
		this.id=id;
	}
	public int getDBCountNum() {
		return db_count_num;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getTeam() {
		return team;
	}
	public String getKakaoId() {
		return kakao_id;
	}
	public String[] getM_Name() {
		return m_name;
	}
	public String[] getM_Date() {
		return m_date;
	}
	public String[] getM_number() {
		return m_number;
	}
	public String[] getC_number() {
		return c_number;
	}
	public String[] getIs_set() {
		return is_set;
	}
	public void getInfo() {
		//user_info 정보 가져오기
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC", "root", "root123");
			if(conn == null) {
				throw new Exception("db 연결 불가");
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user_info WHERE id='"+id+"';");
			if(!rs.next()) {
				throw new Exception("해당하는 아이디 없음.");
			}
			name = rs.getString("name");
			pw = rs.getString("pw"); // 비번 필요한지 확인
			team = rs.getString("team");
			kakao_id = rs.getString("kakao_id");
		}
		catch (Exception e){
			e.printStackTrace();
		}
        finally {
        	try {
				if(rs!=null) rs.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(stmt!=null) stmt.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
        }
		
	}
	public void getPMatchList() {
		//참가중인 매치 가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root123");
			if(conn == null) {
				throw new Exception("db 연결 불가");
			}
			String sql = "SELECT p.m_name, m_date, m_number, c_number, is_set FROM p_match p,match_info m WHERE p.id=? AND p.date=m.date";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			
			m_name = new String[10];
			m_date = new String[10];
			m_number = new String[10];
			c_number = new String[10];
			is_set = new String[10];
			
			int i=0;
			while(rs.next()) {
				m_name[i] = rs.getString("m_name");
				m_date[i] = rs.getString("m_date");
				m_number[i] = rs.getString("m_number");
				c_number[i] = rs.getString("c_number");
				is_set[i] = rs.getString("is_set");
				i++;
			}
			db_count_num = i;
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
		}
	}
}
