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
		//�� ����ȭ�鿡�� �����ϱ�(Ȯ�ι�ư) Ŭ������ ��
		//���޵� ���� Ȯ���ϰ� db�� �Է�
		//db�Է� �� error���ٸ� result:success������ �Խ��� ȭ�� �����ֱ�
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
			//�Ű������� ���� �ԷµǾ��� �� ����
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC", "root", "root123");
				if(conn == null) {
					throw new Exception("db���� �Ұ�");
				}
				String sql = "INSERT INTO match_info (m_name, id, m_date, m_number, c_number, is_set, detail, team, event VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				//���⼭ �Է��� ������ ����
				pstmt.setString(1, m_name);//m_name
				pstmt.setString(2, id);//id
				pstmt.setString(3, m_date);//m_date
				pstmt.setInt(4, m_number);//m_number
				pstmt.setInt(5, c_number);//c_number
				pstmt.setInt(6, is_set);//is_set
				pstmt.setString(7, detail);//detail
				pstmt.setInt(8, team);//team
				pstmt.setString(9, event);//event

				// insert ������
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
