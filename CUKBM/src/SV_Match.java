

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			//���޵� �Ű������� ���� ���
			log("*** null value");
		}
		else {
			//value�� ���� �ش��ϴ� view�� ����
			switch (value) {
				case "create_page" :
					//�� ����� ȭ������ �ѱ��(�۾��� ȭ��)
					response.sendRedirect("cb_CreateGameroom.jsp");
					break;
				case "create":
					//�� ����ȭ�鿡�� �����ϱ� Ŭ������ ��
					//���޵� ���� Ȯ���ϰ� db�� �Է�
					//db�Է� �� error���ٸ� result:success������ �Խ��� ȭ�� �����ֱ�
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
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd_HHmmss");
        Date time = new Date();
        String time1 = format1.format(time);

        
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
	                        rs.getString("id"), "��ġ�� Ȯ���Ǿ����ϴ�", time1, 0));
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
