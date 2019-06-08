import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CreateMatch {
	
	private String m_name, id, m_date, detail, event;
	private Integer m_number, c_number, is_set, team;
	
	public CreateMatch() {}
	
	public String getEvent() {
		return event;
	}
	public void setParams(String m_name, String id, String m_date, Integer m_number, Integer c_number, Integer is_set, String detail, Integer team, String event) {
		this.m_name=m_name;
		this.id=id;
		this.m_date=m_date;
		this.m_number=m_number;
		this.c_number=c_number;
		this.is_set=is_set;
		this.detail=detail;
		this.team=team;
		this.event=event;
		
	}
	
	public String create() {
		String result = null;
		if(m_name!=null && id!=null && m_date!=null && m_number!=null && c_number!=null && is_set!=null
				&& detail!=null && team!=null && event!=null) {
			//매개변수를 전부 입력되었을 때 실행
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root123");
				if(conn == null) {
					throw new Exception("db연결 불가");
				}
				String sql = "INSERT INTO match_info (m_name, id, m_date, date, m_number, c_number, is_set, detail, team, event) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				//여기서 입력할 값들을 세팅
				pstmt.setString(1, m_name);//m_name
				pstmt.setString(2, id);//id
				pstmt.setString(3, m_date);//m_date
				pstmt.setString(4, new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(new Date()));
				pstmt.setInt(5, m_number);//m_number
				pstmt.setInt(6, c_number);//c_number
				pstmt.setInt(7, is_set);//is_set
				pstmt.setString(8, detail);//detail
				pstmt.setInt(9, team);//team
				pstmt.setString(10, event);//event
				// insert 실행
				pstmt.executeUpdate();
				pstmt.close();
				
				sql = "INSERT INTO p_match (m_name, id, date) VALUES(?, ?, ?)";
				//여기서 입력할 값들을 세팅
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, m_name);//m_name
				pstmt.setString(2, id);//id
				pstmt.setString(3, new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(new Date()));//date

				// insert 실행
				pstmt.executeUpdate();
				
				return "success";
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		else {
			return "fail";
		}
		return "";
	}
	
}
