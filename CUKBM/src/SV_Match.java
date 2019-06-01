

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
		
		String m_name, id, m_date, detail, event;
		Integer m_number, c_number, is_set, team;
		
		if(value == null) {
			//전달된 매개변수가 없는 경우
			log("****************event : null value");
		}
		else {
			//event의 값에 해당하는 view로 연결
			switch (value) {
				case "create_page" :
					//방 만드는 화면으로 넘기기(글쓰기 화면)
					response.sendRedirect("cb_CreateGameroom.jsp");
					break;
				case "create":
					//방 생성화면에서 생성하기(확인버튼) 클릭했을 때
					//전달된 값을 확인하고 db에 입력
					//db입력 후 error없다면 result:success받으면 게시판 화면 보여주기
					m_name = request.getParameter("m_name");
					id = request.getParameter("id");
					m_date = request.getParameter("m_date");
					m_number = new Integer(request.getParameter("m_number"));
					c_number = new Integer(request.getParameter("c_number"));
					is_set = new Integer(request.getParameter("is_set"));
					detail = request.getParameter("detail");
					team = new Integer(request.getParameter("team"));
					event = request.getParameter("event");

					if(m_name!=null && id!=null && m_date!=null && m_number!=null && c_number!=null && is_set!=null
							&& detail!=null && team!=null && event!=null) {
						//매개변수를 전부 입력되었을 때 실행
						Connection conn=null;
						PreparedStatement pstmt=null;
						ResultSet rs=null;
						try {
							Class.forName("com.mysql.jdbc.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC", "root", "root123");
							if(conn == null) {
								throw new Exception("db연결 불가");
							}
							String sql = "INSERT INTO match_info (m_name, id, m_date, m_number, c_number, is_set, detail, team, event VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
							pstmt = conn.prepareStatement(sql);
							//여기서 입력할 값들을 세팅
							pstmt.setString(1, m_name);//m_name
							pstmt.setString(2, id);//id
							pstmt.setString(3, m_date);//m_date
							pstmt.setInt(4, m_number);//m_number
							pstmt.setInt(5, c_number);//c_number
							pstmt.setInt(6, is_set);//is_set
							pstmt.setString(7, detail);//detail
							pstmt.setInt(8, team);//team
							pstmt.setString(9, event);//event

							// insert 실행결과
							boolean result = pstmt.execute();
							if(result==true) {
								log("INSERT 성공");
								//성공 시 해당 게시판으로 이동
								response.sendRedirect("cb_Board.jsp?event="+event);
							}
							else {
								log("INSERT 실패");
								throw new Exception("insert 실패");
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						finally {
							try {
								if(conn!=null) conn.close();
								if(pstmt!=null) pstmt.close();
								if(rs!=null) rs.close();
							}
							catch(Exception e) {
								e.printStackTrace();
							}
						}
					}
					else {
						log("SV_Match_create******************입력되지 않은 매개변수가 있음.");
					}

				case "join":
					//방 참가 코드, 알람 - 방장(참가시, 꽉찼을시)
					response.sendRedirect("cb_ShowGameroom.jsp");
					break;
				case "set":
					date = request.getParameter("date");
					SetMatch setMatch = new SetMatch();
					setMatch.changeSet(date);
					
					break;
				case "random":{
					//랜덤 참가 코드
					RandomMatch rm = new RandomMatch();
					String result = rm.getRandomMatch();
					if(result.equals("success")) {
						response.sendRedirect("cb_ShowGameroom.jsp");
					}
					break;
				}
				case "show":{
					// 방 보기
					date = request.getParameter("date");
					if(date!=null) {
						ShowMatch sm = new ShowMatch();
						sm.setDate(date); //검색할 매치의 날짜 설정
						String result = sm.readDB(); //해당 값으로 DB에서 읽음
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
