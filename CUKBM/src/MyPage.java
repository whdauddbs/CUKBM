import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyPage {
	
	private String name, id, pw, team, kakao_id;
	private String[] match_name, match_date;
	public MyPage() {}
	
	public void setId(String id){
		this.id=id;
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
	public String[] getMatchName() {
		return match_name;
	}
	public String[] getMatchDate() {
		return match_date;
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
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC", "root", "root123");
			if(conn == null) {
				throw new Exception("db 연결 불가");
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM p_match WHERE id='"+id+"';");
			
			match_name = new String[20];
			match_name = new String[20];
			
			int i=0;
			while(rs.next()) {
				match_name[i] = rs.getString("m_name");
				match_date[i] = rs.getString("date");
				i++;
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( stmt != null ) try{stmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
		}
	}
}
