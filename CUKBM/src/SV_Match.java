

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SV_Match
 */
@WebServlet("/SV_Match")
public class SV_Match extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_Match() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String value = request.getParameter("value");
		
		if(value == null) {
			//���޵� �Ű������� ���� ���
			log("**************** null value");
		}
		else {
			//value�� ���� �ش��ϴ� view�� ����
			switch (value) {
				case "create_page" :
					//�� ����� ȭ������ �ѱ��(�۾��� ȭ��)
					response.sendRedirect("cb_CreateGameroom.jsp");
					break;
				case "create":
					//�� ����ȭ�鿡�� �����ϱ�(Ȯ�ι�ư) Ŭ������ ��
					//���޵� ���� Ȯ���ϰ� db�� �Է�
					//db�Է� �� error���ٸ� result:success������ �Խ��� ȭ�� �����ֱ�
					String m_name = request.getParameter("m_name");
					String id = request.getParameter("id");
					String m_date = request.getParameter("m_date");
					String m_number = request.getParameter("m_number");
					String c_number = request.getParameter("c_number");
					String is_set = request.getParameter("is_set");
					String detail = request.getParameter("detail");
					String team = request.getParameter("team");
					String event = request.getParameter("event");
					
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
							pstmt.setString(1, "");//m_name
							pstmt.setString(2, "");//id
							pstmt.setString(3, "");//m_date
							pstmt.setString(4, "");//m_number
							pstmt.setString(5, "");//c_number
							pstmt.setString(6, "");//is_set
							pstmt.setString(7, "");//detail
							pstmt.setString(8, "");//team
							pstmt.setString(9, "");//event
							
							// insert ������
							boolean result = pstmt.execute();
							if(result==true) {
								log("INSERT ����");
								//���� �� �ش� �Խ������� �̵�
								response.sendRedirect("cb_Board.jsp?value="+event);
							}
							else {
								log("INSERT ����");
								throw new Exception("insert ����");
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
						log("SV_Match_create******************�Էµ��� ���� �Ű������� ����.");
					}
					
				case "join":
					//�� ���� �ڵ�
					response.sendRedirect("cb_ShowGameroom.jsp"); //todo ��� ������ �� ����(date �� �����ϸ� �ش� ������ ���� ����)
					break;
				case "set":
					//��ġ Ȯ�� �ڵ�
					break;
				case "random":
					//���� ���� �ڵ�
					RandomMatch rm = new RandomMatch();
					String result = rm.getRandomMatch();
					if(result.equals("success")) {
						response.sendRedirect("cb_ShowGameroom.jsp"); //todo ��� ������ �� ����(date �� �����ϸ� �ش� ������ ���� ����)
					}
					break;
				default:
					break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
