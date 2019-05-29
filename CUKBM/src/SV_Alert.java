

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
		
		if(id != null) {
			//id ���� ���� �Ǿ��� ��
			//DB�� alert table���� �ش� ���̵�� select �� ��� ����ϴ� �ڵ�
			Connection conn=null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(new Date());
			
			try {
				Class.forName("com.mysql.jdbc.driver");
				conn = DriverManager.getConnection("jdbc:mysql://locahost:3306/cukbm?serverTimezone", "root", "root123");
				if(conn == null) {
					throw new Exception("�����ͺ��̽� ���� ����");
				}
				//Ȯ������ ���� �޽��� SELECT
				String sql="SELECT * FROM alert WHERE is_checked=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 0);
				ArrayList<String> msg = new ArrayList<String>();
				ArrayList<String> date = new ArrayList<String>();
				rs = pstmt.executeQuery();
				if(rs.next()) {
					msg.add(rs.getString("message"));
					date.add(rs.getString("date"));
				}
				pstmt.close();
				rs.close();
				
				//Ȯ���� �޽����� SELECT
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 1);
				//Ȯ�ε� �޽��� -> c_msg, c_date
				ArrayList<String> c_msg = new ArrayList<String>();
				ArrayList<String> c_date = new ArrayList<String>();
				rs = pstmt.executeQuery();
				if(rs.next()) {
					c_msg.add(rs.getString("message"));
					c_date.add(rs.getString("date"));
				}
				pstmt.close();
				rs.close();

				//�� �ð� �������� ���� ���� �޽������� ���� ���� �޽����� ǥ��
				sql="UPDATE alert SET is_checked=? WHERE date<? AND is_checked=0";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 1);
				pstmt.setString(2, timeStamp);
				
				pstmt.close();
				rs.close();
				
				//Ȯ������ ���� �˸�
				request.setAttribute("message", msg); 
				request.setAttribute("date", date);
				//Ȯ���� �˸�
				request.setAttribute("c_message", c_msg);
				request.setAttribute("c_date", c_date);
				
				
				RequestDispatcher rd = request.getRequestDispatcher("cb_Alert.jsp");
				rd.forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally 
			{	
				try {
					if(conn!=null) conn.close();
					if(pstmt!=null) pstmt.close();
					if(rs!=null) rs.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		else {
			log("SV_Alert**********���޵� param ����");
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
