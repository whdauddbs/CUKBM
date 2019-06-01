

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
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
		String date = null;
		
		String m_name, id, m_date, detail, event;
		Integer m_number, c_number, is_set, team;
		
		if(value == null) {
			//���޵� �Ű������� ���� ���
			log("****************event : null value");
		}
		else {
			//event�� ���� �ش��ϴ� view�� ����
			switch (value) {
				case "create_page" :
					//�� ����� ȭ������ �ѱ��(�۾��� ȭ��)
					response.sendRedirect("cb_CreateGameroom.jsp");
					break;
				case "create":
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
							boolean result = pstmt.execute();
							if(result==true) {
								log("INSERT ����");
								//���� �� �ش� �Խ������� �̵�
								response.sendRedirect("cb_Board.jsp?event="+event);
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
					//�� ���� �ڵ�, �˶� - ����(������, ��á����)
					response.sendRedirect("cb_ShowGameroom.jsp");
					break;
				case "set":
					date = request.getParameter("date");
					SetMatch setMatch = new SetMatch();
					setMatch.changeSet(date);
					
					break;
				case "random":{
					//���� ���� �ڵ�
					RandomMatch rm = new RandomMatch();
					String result = rm.getRandomMatch();
					if(result.equals("success")) {
						response.sendRedirect("cb_ShowGameroom.jsp");
					}
					break;
				}
				case "show":{
					// �� ����
					date = request.getParameter("date");
					if(date!=null) {
						ShowMatch sm = new ShowMatch();
						sm.setDate(date); //�˻��� ��ġ�� ��¥ ����
						String result = sm.readDB(); //�ش� ������ DB���� ����
						if(result.equals("success")) {
							request.setAttribute("m_name", sm.getM_name());
							request.setAttribute("id", sm.getId());
							request.setAttribute("date", sm.getDate());
							request.setAttribute("m_date", sm.getM_date());
							request.setAttribute("m_number", sm.getM_number());
							request.setAttribute("c_number", sm.getC_number());
							request.setAttribute("is_set", sm.getIs_set());
							request.setAttribute("detail", sm.getDetail());
							request.setAttribute("team", sm.getTeam());
							request.setAttribute("event", sm.getEvent());
							
							RequestDispatcher dispatch = request.getRequestDispatcher("cb_ShowGameroom.jsp");
							dispatch.forward(request, response);
						}
						else {
							try {
								throw new Exception("DB�˻� ����");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
					}
					break;
				}
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
