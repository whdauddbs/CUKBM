

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
//		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//��δ� path�� �޴´�. ��Ÿ������ �����ϱ����� requestDispatcher�� ����Ѵ�.
		
		String path = request.getParameter("path");//�� ������ ��û�� ���������� path ������ ���� �������� path�� �Ѱ���ߵ�.
		String date = request.getParameter("date");
		
		
		System.out.println("SV_Login : path: " + path);
		if(id!=null && pw!=null) {
			//���۵� id, pw �� null�� �ƴ� ���
			Login login = new Login();
			login.setID(id);
			login.setPW(pw);
			String result = login.getLoginResult();
		
			if(result.equals("success")) {
				//�α��� ���� �� ���Ǿȿ� id, login �� �߰�
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				if(path == null | "null".equals(path) | path=="") {
					response.sendRedirect("cb_Main.jsp");
				//�����̷�Ʈ ��ġ -> �̵��Ϸ��� ������
				}
				else {
//					RequestDispatcher rd = request.getRequestDispatcher(path);
//					rd.forward(request, response);

					if(date == null | "null".equals(date) | date=="") {
						RequestDispatcher rd = request.getRequestDispatcher(path);
						rd.forward(request, response);				
					}
					else {
						RequestDispatcher rd = request.getRequestDispatcher(path+"&date="+date);
						rd.forward(request, response);
					}
				}
				
			}
			else {
				//�α��� ���� �� ó��.. alert? or ȭ�鿡 ���ڷ�?
				response.sendRedirect("cb_Login.jsp");
			}
		}
		else {
			log("**********���޵� id �Ǵ� pw �� null �Դϴ�.");
			response.sendRedirect("cb_Login.jsp");
		}
		
	}

}
