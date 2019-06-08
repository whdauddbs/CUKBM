

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SV_Alert
 */
@WebServlet("/SV_Alert")
public class SV_Alert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SV_Alert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("SV_Alert : " + id);
		Alert alert = new Alert();
		String result = alert.getAlert(id);
		int msg_cnt = alert.getMsgCnt();
		int c_msg_cnt = alert.getC_msgCnt();
		String[] msg = new String[msg_cnt]; // 확인X 메세지
		String[] c_msg = new String[c_msg_cnt]; // 확인O 메세지
		if(result.equals("success")){
				 msg = alert.getMsg(); 
				 c_msg = alert.getC_msg();
				//확인하지 않은 알림
				request.setAttribute("message", msg); 
				request.setAttribute("msg_cnt", msg_cnt);
				//확인한 알림
				request.setAttribute("c_message", c_msg);
				request.setAttribute("c_msg_cnt", c_msg_cnt);
				
				
				RequestDispatcher rd = request.getRequestDispatcher("cb_Alert.jsp");
				rd.forward(request, response);
		}
		else if(result.equals("fail")){
			log("SV_Alert**********전달된 param 없음");
		}
		else {
			try {
				throw new Exception("DB read 실패");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
