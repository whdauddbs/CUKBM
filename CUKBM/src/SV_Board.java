

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String value = request.getParameter("value");
		
		if(value == null) {
			//���޵� �Ű������� ���� ���
			log("*** null value");
		}
		else {
			//value�� ���� �ش��ϴ� view�� ����
			switch (value) {
				case "soccer" :
					response.sendRedirect("cb_Board.jsp?value=soccer");
					break;
				case "basketball":
					response.sendRedirect("cb_Board.jsp?value=basketball");
					break;
				case "pingpong":
					response.sendRedirect("cb_Board.jsp?value=pingpong");
					break;
				case "etc": //����Ʈ ��Ÿ
					response.sendRedirect("cb_Board.jsp?value=etc");
					break;
				case "lol":
					response.sendRedirect("cb_Board.jsp?value=lol");
					break;
				case "overwatch":
					response.sendRedirect("cb_Board.jsp?value=overwatch");
					break;
				case "bg":
					response.sendRedirect("cb_Board.jsp?value=bg");
					break;
				case "e_etc": //e������ ��Ÿ
					response.sendRedirect("cb_Board.jsp?value=e_etc");
					break;
				default:
					break;
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
