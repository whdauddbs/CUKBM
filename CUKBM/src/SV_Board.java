

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
		String event = request.getParameter("event"); //����
		int pageNum=1;
		//���޵� ������ ��ȣ�� ���°��=> ù�������� ������
		if(request.getParameter("pageNum")!=null) {

			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
//		Board board = readDB(value, page);
//		request.setAttribute("value", value);
//		request.setAttribute("page", page);
//		request.setAttribute("board", board);
		
		String[] m_name = new String[10]; // ����
		String[] id = new String[10]; // �ۼ���
		String[] m_date = new String[10]; // ��������
		String[] date = new String[10]; // �������
		Integer[] m_number = new Integer[10]; // �����ο�
		Integer[] c_number = new Integer[10]; // ���� �ο�
		Integer[] is_set = new Integer[10]; // ��ġ Ȯ�� ����
		String[] detail = new String[10]; // �� ����
		Integer[] team = new Integer[10]; // ��/����
//		String[] event = new String[10]; // ����
		
		if(event == null) {
			//���޵� �Ű������� ���� ���
			log("***event : null value");
			try {
				throw new Exception("���޵� �Ű����� ����");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			Board board = new Board();
			board.setEvent(event); //���� ����
			board.setPageNum(pageNum); //������ ����
			String result = board.readDB(); //DB���� ��ġ �о����
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
				
				//�о�� ���� ����
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
//				������ ����
				request.setAttribute("pageNum", pageNum);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cb_Board.jsp");
				dispatcher.forward(request, response);
			}
			else {
				try {
					throw new Exception("DB read ����");
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