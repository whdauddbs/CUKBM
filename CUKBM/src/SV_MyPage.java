

import java.io.IOException;

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
		
		if(session.getAttribute("login")!=null) {
			//�α����� �Ǿ� ���� ��
			String id = (String) session.getAttribute("id");
			
			//DB���� id�� �ش��ϴ� �� ������ �������� ����.
			MyPage mp = new MyPage();
			mp.setId(id); //���̵� set
			mp.getInfo(); //�� ���� �˻�
			mp.getPMatchList(); //�������� ��ġ ����Ʈ �˻�
			
			String name = mp.getName();
			String pw = mp.getPw();
			String team = mp.getTeam();
			String kakao_id = mp.getKakaoId();
			String[] match_name = mp.getMatchName();
			String[] match_date = mp.getMatchDate();
			
			//���� ������ view�� ����
			request.setAttribute("name", name);
			request.setAttribute("pw", pw);
			request.setAttribute("team", team);
			request.setAttribute("kakao_id", kakao_id);
			request.setAttribute("match_name", match_name);
			request.setAttribute("match_date", match_date);
			RequestDispatcher rd = request.getRequestDispatcher("cb_MyPage.jsp");
            rd.forward(request, response);
		}
		else {
			//�α��� �� �� ������ ��
			RequestDispatcher rd = request.getRequestDispatcher("cb_Login.jsp");
            rd.forward(request, response);
			
            //���� ����..?
//			response.sendRedirect("cb_Login.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
