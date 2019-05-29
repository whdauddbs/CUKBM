

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
		if(value == null) {
			//���޵� �Ű������� ���� ����
			log("**************** null value");
		}
		else {
			//value�� ���� �ش��ϴ� view�� ����
			switch (value) {
				case "create_page" :
					//�� ������ ȭ������ �ѱ���(�۾��� ȭ��)
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

							// insert ��������
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
					//�� ���� �ڵ�, �˶� - ����(������, ��á����)
					response.sendRedirect("cb_ShowGameroom.jsp");
					break;
				case "set":
					date = request.getParameter("date");
					changeSet(date);
					break;
				case "random":
					//���� ���� �ڵ�
					RandomMatch rm = new RandomMatch();
					String result = rm.getRandomMatch();
					if(result.equals("success")) {
						response.sendRedirect("cb_ShowGameroom.jsp"); //todo ���� ������ �� ����(date �� �����ϸ� �ش� ������ ���� ����)
					}
					break;
				case "show":
					date = request.getParameter("date");
					request.setAttribute("board", showRoom(date));
					RequestDispatcher dispatch = request.getRequestDispatcher("cb_ShowGameroom.jsp");
					dispatch.forward(request, response);
					break;
					// �� ����
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

	private void changeSet(String date) throws ServletException { // set �ٲٰ�, �˶� ���� ����
        Connection conn = null;
        Statement stmt = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(new Date());


        try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm","root","1234");
	        if (conn == null)
	        	throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
	        stmt = conn.createStatement();
	        stmt.executeQuery("update match_info set is_set=1 where date = " + date + ";");
	        ResultSet rs = stmt.executeQuery("select * from match_info where date = " + date + ";");
	        if (!rs.next()) {
            }
	        else {
		        String m_name = rs.getString("m_name");
		        rs = stmt.executeQuery("select * from p_match where m_name = " + m_name + ";");
		        while(true) {
		        	if (!rs.next()) {
		            	break;
		            }
		        	stmt.executeQuery(String.format("insert into alert(id, message, date, is_checked) values('%s', '%s', '%s', %s;",
	                        rs.getString("id"), "��ġ�� Ȯ���Ǿ����ϴ�", timeStamp, 0));
		        }
	        }
        }
        catch (Exception e) {
        	throw new ServletException(e);
        }
        finally {
		    try {
		    	stmt.close();
		    }
		    catch (Exception ignored) {
		    }
		    try {
		        conn.close();
		    }
		    catch (Exception ignored) {
		    }
        }
	return;
	}

	private Board showRoom(String date) throws ServletException { // set �ٲٰ�, �˶� ���� ����
        Connection conn = null;
        Statement stmt = null;
        Board board = new Board();
        try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm","root","1234");
	        if (conn == null)
	        	throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("select * from match_info where date = " + date + ";");
	        if (!rs.next()) {
            }
	        else {
	        	board.setTitle(0, rs.getString("m_name"));
	        	board.setWriter(0, rs.getString("id"));
	        	board.setMatchDate(0, rs.getString("m_date"));
	        	board.setDate(0, rs.getString("date"));
	        	board.setMNumber(0, rs.getInt("m_number"));
	        	board.setCurrentNumber(0, rs.getInt("c_number"));
	        	board.setIsSet(0,rs.getInt("is_set"));
	        	board.setDetail(0, rs.getString("detail"));
	        	board.setIsTeam(0, rs.getInt("team"));
	        	board.setEvent(0, rs.getInt("event"));

	        }
        }
        catch (Exception e) {
        	throw new ServletException(e);
        }
        finally {
		    try {
		    	stmt.close();
		    }
		    catch (Exception ignored) {
		    }
		    try {
		        conn.close();
		    }
		    catch (Exception ignored) {
		    }
        }
	return board;
	}

}
