

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
		String path=request.getParameter("path");
		
		if(session.getAttribute("id")!=null) {
			//로그인이 되어 있을 때
			String id = (String) session.getAttribute("id");
			System.out.println("SV_Mypage 첫 if문 :세션 id ::"+id);
			//DB에서 id에 해당하는 내 정보를 가져오게 구현.
			MyPage mp = new MyPage();
			mp.setId(id); //아이디 set
			mp.getInfo(); //내 정보 검색
			mp.getPMatchList(); //참가중인 매치 리스트 검색
			
			String name = mp.getName();
			String pw = mp.getPw();
			String team = mp.getTeam();
			String kakao_id = mp.getKakaoId();
			String[] match_name = mp.getMatchName();
			String[] match_date = mp.getMatchDate(); //이 date는 매치를 등록한 시간임.
			
			//위에 값들을 view에 전달
			request.setAttribute("name", name);
//			request.setAttribute("pw", pw);
			request.setAttribute("team", team);
			request.setAttribute("kakao_id", kakao_id);
			request.setAttribute("match_name", match_name);
			request.setAttribute("match_date", match_date);
			if(match_name!=null) {
				request.setAttribute("count",  match_name.length);
			}
			RequestDispatcher rd = request.getRequestDispatcher("cb_MyPage.jsp");
            rd.forward(request, response);
		}
		else {
			//로그인 안 된 상태일 때
//			RequestDispatcher rd = request.getRequestDispatcher("cb_Login.jsp");
//            rd.forward(request, response);
			System.out.println("여기로 빠짐::");
			
			//로그인 페이지로 이동시키고 로그인 성공 시 다시 마이페이지로 돌아오게 한다.
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
