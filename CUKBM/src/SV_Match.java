

import java.io.IOException;
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
		String result = null;
		
		System.out.println("SV_Match.java value : "+value);
		
		if(value == null) {
			//���޵� �Ű������� ���� ���
			log("SV_match.java :****************value : null value");
		}
		else { 
			//event�� ���� �ش��ϴ� view�� ����
			switch (value) {
				case "create_page" :{
					//�� ����� ȭ������ �ѱ��(�۾��� ȭ��)
					response.sendRedirect("cb_CreateGameroom.jsp");
					break;
				}
 				case "create":{
					//�� ����ȭ�鿡�� �����ϱ�(Ȯ�ι�ư) Ŭ������ ��
					//���޵� ���� Ȯ���ϰ� db�� �Է�
					//db�Է� �� error���ٸ� result:success������ �Խ��� ȭ�� �����ֱ�
						String m_name = request.getParameter("m_name");
						String id = (String) request.getSession().getAttribute("id"); //id�� ���ǿ��� ������
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
							log("INSERT ����");
							//���� �� �ش� �Խ������� �̵�
							response.sendRedirect("./board?event="+create.getEvent());
						}
						else {
							
							log("SV_Match_create******************�Էµ��� ���� �Ű������� ����.");
							try {
								throw new Exception("SV_Match.java : INSERT ����");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						break;
					}
				case "join":
					//�� ���� �ڵ�, �˶� - ����(������, ��á����)
					//�ش� match_info ���� �������� �ο� + 1
					//p_match�� ������ ��� ���� �߰�
					
					//�ʿ��� �Ű����� : id, date
					{
						JoinMatch jm = new JoinMatch();
						String id = (String)request.getSession().getAttribute("id");
						date = request.getParameter("date"); // ������ ���� ���� �ð�
//						String match_info_date = request.getParameter("date"); // ������ ���� ���� �ð�
//						date = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(new Date()); // ���� ��ư�� ���� �ð�
						jm.setM_Name(request.getParameter("m_name"));
						jm.setId(id);
						jm.setDate(date); // ��ư �����ð� ����
						jm.InsertPMatch(); //p_match ���̺� insert
						jm.UpdateMatchInfo();//match_info ���̺��� �����ο��� + 1
						response.sendRedirect("./match?value=show&date="+ date);
						break;
					} 
				case "set":
					//��ġ Ȯ��
						{
						date = request.getParameter("date");
						System.out.println("SV_Match set Ȯ�� date: "+date);
						SetMatch setMatch = new SetMatch();
						setMatch.changeSet(date);
						response.sendRedirect("./match?value=show&date="+ date);
						break;
						}
				case "random":{
					//���� ���� �ڵ�
					String event = request.getParameter("event"); //�������� ������ �� ���� �� �Խ������� ���������� �����.
					RandomMatch rm = new RandomMatch();
					rm.setEvent(event);
					result = rm.getRandomMatch();
					
					if(result.equals("success")) {
						response.sendRedirect("./match?value=show&date="+rm.getDate());
					}
					else {
						//���� ������ ���� ���� ���
						response.sendRedirect("./board?event="+event);
					}
					break;
				}
				case "show":{
					// �� ����
					date = request.getParameter("date");
					String id = (String)request.getSession().getAttribute("id");
					
					if(date!=null) {
						ShowMatch sm = new ShowMatch();
						sm.setDate(date); //�˻��� ��ġ�� ��¥ ����
						result = sm.readDB(); //�ش� ������ DB���� ����
						if(id!=null) {
							sm.comparePMatch(id);
							
						}
						sm.read_KakaoId_DB();
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
							request.setAttribute("kakao_id", sm.getKakaoIdList());
							request.setAttribute("kakao_id_count", sm.getKakaoIdList().length);
							System.out.println(sm.getIs_joined());
							RequestDispatcher dispatch = request.getRequestDispatcher("cb_ShowGameroom.jsp");
							dispatch.forward(request, response);
						}
						else {
							try {
								System.out.println(result);
								throw new Exception("DB�˻� ����");
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
