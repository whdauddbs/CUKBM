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
			//Ȯ������ ���� ��ġ && ���� ������ ��ġ�� ������ --> ����/�� ������ �ʿ��ϸ� �������� ���ǿ� team=0 or 1 �߰�, ���� �����Ϸ��� event="" �߰�
			String sql = "SELECT * from match_info WHERE is_set=0 AND c_number<m_number order by rand() limit 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			//������ ��ġ�߿��� �������� �ϳ� ����
			if(!rs.next()) {
				//�˻��� ��ġ�� ���� ���
				return "fail";
			}
			
			else {
				//�˻��� ��ġ�� �ִ� ���
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
