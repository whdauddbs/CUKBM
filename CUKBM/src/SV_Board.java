

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


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
		String event = request.getParameter("event"); //종목
		int pageNum=1;
		//전달된 페이지 번호가 없는경우=> 첫페이지를 보여줌
		if(request.getParameter("pageNum")!=null) {

			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
//		Board board = readDB(value, page);
//		request.setAttribute("value", value);
//		request.setAttribute("page", page);
//		request.setAttribute("board", board);
		
		String[] m_name = new String[10]; // 제목
		String[] id = new String[10]; // 작성자
		String[] m_date = new String[10]; // 저장일자
		String[] date = new String[10]; // 경기일자
		Integer[] m_number = new Integer[10]; // 모집인원
		Integer[] c_number = new Integer[10]; // 현재 인원
		Integer[] is_set = new Integer[10]; // 매치 확정 여부
		String[] detail = new String[10]; // 상세 내용
		Integer[] team = new Integer[10]; // 팀/개인
//		String[] event = new String[10]; // 종목
		
		if(event == null) {
			//전달된 매개변수가 없는 경우
			log("***event : null value");
			try {
				throw new Exception("전달된 매개변수 없음");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			Board board = new Board();
			board.setEvent(event); //종목 세팅
			board.setPageNum(pageNum); //페이지 세팅
			String result = board.readDB(); //DB에서 매치 읽어오기
			if(result.equals("success")) {
				m_name = board.getTitle();
				id = board.getWriter();
				date = board.getDate();
				m_date = board.getm_date();
				m_number = board.getMNumber();
				c_number = board.getCNumber();
				is_set = board.getIsSet();
				detail = board.getDetail();
				team = board.getIsTeam();
//				event = board.getEvent();
				
				//읽어온 내용 세팅
				request.setAttribute("m_name", m_name);
				request.setAttribute("id", id);
				request.setAttribute("date", date);
				request.setAttribute("m_date", m_date);
				request.setAttribute("m_number", m_number);
				request.setAttribute("c_number", c_number);
				request.setAttribute("is_set", is_set);
				request.setAttribute("detail", detail);
				request.setAttribute("team", team);
				request.setAttribute("event", event);
//				페이지 지정
				request.setAttribute("pageNum", pageNum);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cb_Board.jsp");
				dispatcher.forward(request, response);
			}
			else {
				try {
					throw new Exception("DB read 실패");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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