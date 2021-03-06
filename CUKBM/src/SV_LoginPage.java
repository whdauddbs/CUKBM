

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/SV_LoginPage")
public class SV_LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_LoginPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//로그인 페이지로 이동
		String path = request.getParameter("path");
		String date = request.getParameter("date");
		System.out.println("SV_LoginPage path: " + path);
		System.out.println("SV_LoginPage date: " + date);
		if(path == null | "null".equals(path) | path=="") {
			
			response.sendRedirect("cb_Login.jsp");
		}
		else {
			if(date ==null | "null".equals(date) | date=="") {
				RequestDispatcher rd = request.getRequestDispatcher("cb_Login.jsp?path="+path);
				rd.forward(request, response);				
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("cb_Login.jsp?path="+path+"&date="+date);
				rd.forward(request, response);
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
