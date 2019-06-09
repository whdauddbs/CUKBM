

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SV_MyPage
 */
@WebServlet("/SV_MyPage")
public class SV_MyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_MyPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String path=request.getParameter("path");
		
		if(session.getAttribute("id")!=null) {
			//�α����� �Ǿ� ���� ��
			String id = (String) session.getAttribute("id");
			System.out.println("SV_Mypage ù if�� :���� id ::"+id);
			//DB���� id�� �ش��ϴ� �� ������ �������� ����.
			MyPage mp = new MyPage();
			mp.setId(id); //���̵� set
			mp.getInfo(); //�� ���� �˻�
			mp.getPMatchList(); //�������� ��ġ ����Ʈ �˻�
			
			String name = mp.getName();
			String pw = mp.getPw();
			String team = mp.getTeam();
			String kakao_id = mp.getKakaoId();
			
			String[] m_name = mp.getM_Name();
			String[] m_date = mp.getM_Date(); //�� date�� ��ġ�� ����� �ð���.
			String[] m_number = mp.getM_number();
			String[] c_number = mp.getC_number();
			String[] is_set = mp.getIs_set();
			int db_count = mp.getDBCountNum();
			
			//���� ������ view�� ����
			request.setAttribute("name", name);
//			request.setAttribute("pw", pw);
			request.setAttribute("team", team);
			request.setAttribute("kakao_id", kakao_id);
			request.setAttribute("m_name", m_name);
			request.setAttribute("m_date", m_date);
			request.setAttribute("m_number", m_number);
			request.setAttribute("c_number", c_number);
			request.setAttribute("is_set", is_set);
			request.setAttribute("count",  db_count);
			 
			RequestDispatcher rd = request.getRequestDispatcher("cb_MyPage.jsp");
            rd.forward(request, response);
		}
		else {
			//�α��� �� �� ������ ��
//			RequestDispatcher rd = request.getRequestDispatcher("cb_Login.jsp");
//            rd.forward(request, response);
			System.out.println("����� ����::");
			
			//�α��� �������� �̵���Ű�� �α��� ���� �� �ٽ� ������������ ���ƿ��� �Ѵ�.
			response.sendRedirect("cb_Login.jsp?path="+path);
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
