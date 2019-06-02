

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
		String id = request.getParameter("id");
		Alert alert = new Alert();
		// 최대 알림 개수 정하면 바꿀 부분
		/*
		String[] msg = new String[10]; // 확인X 메세지
		String[] date = new String[10];  
		String[] c_msg = new String[10]; // 확인O 메세지
		String[] c_date = new String[10];
		*/
		String result = alert.getAlert(id);
		if(result.equals("success")){
				/*
				 msg = alert.getMsg(); 
				 date = alert.getDate();
				 c_msg = alert.getC_msg();
				 c_date = alert.getC_date();
				 */
				//확인하지 않은 알림
				request.setAttribute("message", alert.getMsg()); 
				request.setAttribute("date", alert.getDate());
				//확인한 알림
				request.setAttribute("c_message", alert.getC_msg());
				request.setAttribute("c_date", alert.getC_date());
				
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
