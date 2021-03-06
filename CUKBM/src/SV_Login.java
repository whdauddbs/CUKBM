

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
		
		//경로는 path로 받는다. 기타정보를 유지하기위해 requestDispatcher를 사용한다.
		
		String path = request.getParameter("path");//이 서블릿을 요청한 페이지에서 path 값으로 원래 페이지의 path를 넘겨줘야됨.
		String date = request.getParameter("date");
		
		
		System.out.println("SV_Login : path: " + path);
		if(id!=null && pw!=null) {
			//전송된 id, pw 가 null이 아닌 경우
			Login login = new Login();
			login.setID(id);
			login.setPW(pw);
			String result = login.getLoginResult();
		
			if(result.equals("success")) {
				//로그인 성공 시 세션안에 id, login 값 추가
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				if(path == null | "null".equals(path) | path=="") {
					response.sendRedirect("cb_Main.jsp");
				//리다이렉트 위치 -> 이동하려던 페이지
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
				//로그인 실패 시 처리.. alert? or 화면에 글자로?
				response.sendRedirect("cb_Login.jsp");
			}
		}
		else {
			log("**********전달된 id 또는 pw 가 null 입니다.");
			response.sendRedirect("cb_Login.jsp");
		}
		
	}

}
