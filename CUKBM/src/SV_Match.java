

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		String date = null;
		String result = null;
		
		System.out.println("SV_Match.java value : "+value);
		
		if(value == null) {
			//전달된 매개변수가 없는 경우
			log("SV_match.java :****************value : null value");
		}
		else { 
			//event의 값에 해당하는 view로 연결
			switch (value) {
				case "create_page" :{
					//방 만드는 화면으로 넘기기(글쓰기 화면)
					response.sendRedirect("cb_CreateGameroom.jsp");
					break;
				}
				case "create":{
					//방 생성화면에서 생성하기(확인버튼) 클릭했을 때
					//전달된 값을 확인하고 db에 입력
					//db입력 후 error없다면 result:success받으면 게시판 화면 보여주기
						String m_name = request.getParameter("m_name");
						String id = (String) request.getSession().getAttribute("id"); //id는 세션에서 가져옴
						String m_date = request.getParameter("m_date");
						Integer m_number = new Integer(Integer.parseInt(request.getParameter("m_number")));
						Integer c_number = new Integer(1);
						Integer is_set = new Integer(0);
						String detail = request.getParameter("detail");
						Integer team = new Integer(Integer.parseInt(request.getParameter("team")));
						String event = request.getParameter("event");
						
						System.out.println(m_name + id + m_date + event);
						
						CreateMatch create = new CreateMatch();
						create.setParams(m_name, id, m_date, m_number, c_number, is_set, detail, team, event);
						result = create.create();
						if(result.equals("success")) {
							log("INSERT 성공");
							//성공 시 해당 게시판으로 이동
							response.sendRedirect("./board?event="+create.getEvent());
						}
						else {
							
							log("SV_Match_create******************입력되지 않은 매개변수가 있음.");
							try {
								throw new Exception("SV_Match.java : INSERT 실패");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						break;
					}
				case "join":
					//방 참가 코드, 알람 - 방장(참가시, 꽉찼을시)
					//해당 match_info 현재 참가중인 인원 + 1
					//p_match에 참가한 사람 정보 추가
					
					//필요한 매개변수 : id, date
					{
						JoinMatch jm = new JoinMatch();
						String id = (String)request.getSession().getAttribute("id");
						date = request.getParameter("date");
						jm.setM_name(request.getParameter("m_name"));
						jm.setId(id);
						jm.setDate(date);
						jm.InsertPMatch(); //p_match 테이블에 insert
						jm.UpdateMatchInfo();//match_info 테이블의 현재인원수 + 1
						response.sendRedirect("./match?value=show&date="+ date);
						break;
					}
				case "set":
					//매치 확정
						{
						date = request.getParameter("date");
						SetMatch setMatch = new SetMatch();
						setMatch.changeSet(date);
						
						break;
						}
				case "random":{
					//랜덤 입장 코드
					RandomMatch rm = new RandomMatch();
					result = rm.getRandomMatch();
					if(result.equals("success")) {
						response.sendRedirect("cb_ShowGameroom.jsp");
					}
					break;
				}
				case "show":{
					// 방 보기
					date = request.getParameter("date");
					String id = (String)request.getSession().getAttribute("id");
					
					if(date!=null) {
						ShowMatch sm = new ShowMatch();
						sm.setDate(date); //검색할 매치의 날짜 설정
						result = sm.readDB(); //해당 값으로 DB에서 읽음
						if(id!=null) {
							sm.comparePMatch(id);
						}
						if(result.equals("success")) {
							request.setAttribute("m_name", sm.getM_name());
							request.setAttribute("id", sm.getId());
							request.setAttribute("date", sm.getDate());
							request.setAttribute("m_date", sm.getM_date());
							request.setAttribute("m_number", sm.getM_number());
							request.setAttribute("c_number", sm.getC_number());
							request.setAttribute("is_set", sm.getIs_set());
							request.setAttribute("detail", sm.getDetail());
							request.setAttribute("team", sm.getTeam());
							request.setAttribute("event", sm.getEvent());
							request.setAttribute("is_joined", sm.getIs_joined());
							
							RequestDispatcher dispatch = request.getRequestDispatcher("cb_ShowGameroom.jsp");
							dispatch.forward(request, response);
						}
						else {
							try {
								throw new Exception("DB검색 실패");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
					}
					break;
				}
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
