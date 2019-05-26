

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SV_Match
 */
@WebServlet("/SV_Match")
public class SV_Match extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_Match() {
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
			//전달된 매개변수가 없는 경우
			log("*** null value");
		}
		else {
			//value의 값에 해당하는 view로 연결
			switch (value) {
				case "create_page" :
					//방 만드는 화면으로 넘기기(글쓰기 화면)
					response.sendRedirect("cb_CreateGameroom.jsp");
					break;
				case "create":
					//방 생성화면에서 생성하기 클릭했을 때
					//전달된 값을 확인하고 db에 입력
					//db입력 후 error없다면 result:success받으면 게시판 화면 보여주기
				case "join":
					//방 참가 코드
					response.sendRedirect("cb_ShowGameroom.jsp");
					break;
				case "set":
					//매치 확정 코드
					break;
				case "random":
					//랜덤 참가 코드
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
