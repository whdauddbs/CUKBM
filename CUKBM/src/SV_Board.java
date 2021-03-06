

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;


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
		int pageNum=1;	//전달된 페이지 번호가 없는경우=> 첫페이지를 보여줌
		int select = 2; //전달된  팀/개인 구별이 없는경우 => 모든 분류를 보여줌
		// 0 : 개인, 1 : 팀, 2 : 개인과 팀
		if(request.getParameter("pageNum")!=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(request.getParameter("select")!=null) {
			select = Integer.parseInt(request.getParameter("select"));
		}
		String[] m_name = new String[10]; // 제목
		String[] id = new String[10]; // 작성자
		String[] m_date = new String[10]; // 저장일자
		String[] date = new String[10]; // 경기일자
		Integer[] m_number = new Integer[10]; // 모집인원
		Integer[] c_number = new Integer[10]; // 현재 인원
		Integer[] is_set = new Integer[10]; // 매치 확정 여부
		String[] detail = new String[10]; // 상세 내용
		Integer[] team = new Integer[10]; // 팀/개인
		int board_cnt;
		
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
			String result = board.readDB(select); //DB에서 매치 읽어오기
			board.countDB(select);
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
				board_cnt = board.getBoardCnt();
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
				request.setAttribute("board_cnt", board_cnt);
				request.setAttribute("path", "/CUKBM/board?event="+event+"&pageNum="+pageNum);
				
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