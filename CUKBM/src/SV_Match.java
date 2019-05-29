

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
			//전달된 매개변수가 없는 경우
			log("*** null value");
		}
		else {
			//value의 값에 해당하는 view로 연결
			switch (value) {
				case "create_page" :
					//방 만드는 화면으로 넘기기(글쓰기 화면)
					response.sendRedirect("cb_CreateGameroom.jsp");
					break;
				case "create":
					//방 생성화면에서 생성하기 클릭했을 때
					//전달된 값을 확인하고 db에 입력
					//db입력 후 error없다면 result:success받으면 게시판 화면 보여주기
				case "join":
					//방 참가 코드, 알람 - 방장(참가시, 꽉찼을시)
					response.sendRedirect("cb_ShowGameroom.jsp");
					break;
				case "set":
					date = request.getParameter("date");
					changeSet(date);
					break;
				case "random":
					//랜덤 참가 코드
					break;
				case "show":
					date = request.getParameter("date");
					request.setAttribute("board", showRoom(date));
					RequestDispatcher dispatch = request.getRequestDispatcher("cb_ShowGameroom.jsp");
					dispatch.forward(request, response);
					break;
					// 방 보기
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
	
	private void changeSet(String date) throws ServletException { // set 바꾸고, 알람 쿼리 전송
        Connection conn = null;
        Statement stmt = null;
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd_HHmmss");
        Date time = new Date();
        String time1 = format1.format(time);

        
        try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm","root","1234");
	        if (conn == null)
	        	throw new Exception("데이터베이스에 연결할 수 없습니다.");
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
	                        rs.getString("id"), "매치가 확정되었습니다", time1, 0));
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
	
	private Board showRoom(String date) throws ServletException { // set 바꾸고, 알람 쿼리 전송
        Connection conn = null;
        Statement stmt = null;
        Board board = new Board();
        try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm","root","1234");
	        if (conn == null)
	        	throw new Exception("데이터베이스에 연결할 수 없습니다.");
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
