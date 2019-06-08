import java.sql.*;

public class RandomMatch {

	private String m_name, id, m_date, date, detail, team, event;
	private int m_number, c_number, is_set;
	public RandomMatch(){
		
	}
	public String getM_name() {
		return this.m_name;
	};
	public String getId() {
		return this.id;
	};
	public String getM_date() {
		return this.m_date;
	};
	public String getDate() {
		return this.date;
	}
	public String getDetail() {
		return this.detail;
	};
	public String getTeam() {
		return this.team;
	};
	public String getEvent() {
		return this.event;
	};
	public int getM_number() {
		return this.m_number;
	};
	public int getC_number() {
		return this.c_number;
	};
	public int getIs_set() {
		return this.is_set;
	};
	public String getRandomMatch() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root123");
			//확정되지 않은 매치 && 참가 가능한 매치를 가져옴 --> 개인/팀 구분이 필요하면 쿼리문의 조건에 team=0 or 1 추가, 종목 설정하려면 event="" 추가
			String sql = "SELECT * from match_info WHERE is_set=0 AND c_number<m_number order by rand() limit 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			//가져온 매치중에서 랜덤으로 하나 선택
			if(!rs.next()) {
				//검색된 매치가 없는 경우
				return "fail";
			}
			
			else {
				//검색된 매치가 있는 경우
				m_name = rs.getString("m_name");
				id = rs.getString("id");
				m_date = rs.getString("m_date");
				date = rs.getString("date");
				m_number = rs.getInt("m_number");
				c_number = rs.getInt("c_number");
				is_set = rs.getInt("is_set");
				detail = rs.getString("detail");
				team = rs.getString("team");
				event = rs.getString("event");
				
				return "success";
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return "exception";
		}
		
		
		
	}
}
