

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/SV_Login")
public class SV_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id!=null && pw!=null) {
			//���۵� id, pw �� null�� �ƴ� ���
			Login login = new Login();
			login.setID("id");
			login.setPW("pw");
			String result = login.getLoginResult();
		
			if(result.equals("success")) {
				//�α��� ���� �� ���Ǿȿ� id, login �� �߰�
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				session.setAttribute("login", true);
				
				//�����̷�Ʈ ��ġ -> �̵��Ϸ��� ������
			}
			else {
				//�α��� ���� �� ó��.. alert? or ȭ�鿡 ���ڷ�?
			}
		}
		
	}

}
