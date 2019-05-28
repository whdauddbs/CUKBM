

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;


/**
 * Servlet implementation class SV_Board
 */
@WebServlet("/SV_Board")
public class SV_Board extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_Board() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("value");
		Integer page = Integer.parseInt(request.getParameter("page"));
		RequestDispatcher dispatcher = null;
		Board board = readDB(value, page);
		request.setAttribute("value", value);
		request.setAttribute("page", page);
		request.setAttribute("board", board);
		
		if(value == null) {
			//전달된 매개변수가 없는 경우
			log("*** null value");
		}
		else {
			dispatcher = request.getRequestDispatcher("cb_Board.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private Board readDB(String value, int page) throws ServletException {
        Connection conn = null;
        Statement stmt = null;
        Board board = new Board();
        try {
        	
        	Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm","root","1234");
            if (conn == null)
            	throw new Exception("데이터베이스에 연결할 수 없습니다.");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from match_info where event = " + value + " order by date desc;");
            
            for (int cnt = 0; cnt < page*10; cnt++) { // 
                if (!rs.next()) {
                	break;
                }
                if(cnt>=(page-1)*10) {
          		  	board.setTitle(cnt%10, rs.getString("m_name"));
          		  	board.setMatchDate(cnt%10, rs.getString("m_date"));
          		  	board.setMNumber(cnt%10, rs.getInt("m_number"));
          		  	board.setCurrentNumber(cnt%10, rs.getInt("c_number"));
          		  	board.setCurrentNumber(cnt%10, rs.getInt("is_set"));
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
	     return board;
	}
}
