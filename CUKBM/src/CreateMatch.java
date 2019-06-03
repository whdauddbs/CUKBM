import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateMatch {
	
	private String m_name, id, m_date, detail, event;
	private Integer m_number, c_number, is_set, team;
	
	public String getEvent() {
		return event;
	}
	
	public CreateMatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//방 생성화면에서 생성하기(확인버튼) 클릭했을 때
		//전달된 값을 확인하고 db에 입력
		//db입력 후 error없다면 result:success받으면 게시판 화면 보여주기
		m_name = request.getParameter("m_name");
		id = request.getParameter("id");
		m_date = request.getParameter("m_date");
		m_number = new Integer(request.getParameter("m_number"));
		c_number = new Integer(request.getParameter("c_number"));
		is_set = new Integer(request.getParameter("is_set"));
		detail = request.getParameter("detail");
		team = new Integer(request.getParameter("team"));
		event = request.getParameter("event");
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
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC", "root", "root123");
				if(conn == null) {
					throw new Exception("db연결 불가");
				}
				String sql = "INSERT INTO match_info (m_name, id, m_date, m_number, c_number, is_set, detail, team, event VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				//여기서 입력할 값들을 세팅
				pstmt.setString(1, m_name);//m_name
				pstmt.setString(2, id);//id
				pstmt.setString(3, m_date);//m_date
				pstmt.setInt(4, m_number);//m_number
				pstmt.setInt(5, c_number);//c_number
				pstmt.setInt(6, is_set);//is_set
				pstmt.setString(7, detail);//detail
				pstmt.setInt(8, team);//team
				pstmt.setString(9, event);//event

				// insert 실행결과
				boolean dbResult = pstmt.execute();
				if(dbResult==true) {
					result =  "success";
				}
				else {
					return "fail_db";
				}

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
			result =  "fail";
		}
		return result;
	}
	
}
