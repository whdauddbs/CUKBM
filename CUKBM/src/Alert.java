import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Alert {

	private ArrayList<String> msg = new ArrayList<String>();
	private ArrayList<String> date = new ArrayList<String>();
	private ArrayList<String> c_msg = new ArrayList<String>();
	private ArrayList<String> c_date = new ArrayList<String>();
	private int msg_cnt;
	private int c_msg_cnt;
	
	public Alert(){
		msg_cnt = 0;
		c_msg_cnt = 0;
	}
	
	public String[] getMsg() {
		return msg.toArray(new String[msg.size()]);
	};
	public String[] getDate() {
		return date.toArray(new String[date.size()]);
	};
	public String[] getC_msg() {
		return c_msg.toArray(new String[c_msg.size()]);
	};
	public String[] getC_date() {
		return c_date.toArray(new String[c_date.size()]);
	};
	public int getMsgCnt() {
		return msg_cnt;
	}
	public int getC_msgCnt() {
		return c_msg_cnt;
	}
	
	public String getAlert(String id) {
		if(id != null) {
			//id 값이 전달 되었을 떄
			//DB의 alert table에서 해당 아이디로 select 한 결과 출력하는 코드
			Connection conn=null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(new Date());
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root123");
				if(conn == null) {
					throw new Exception("데이터베이스 연결 실패");
				}
				//확인하지 않은 메시지 SELECT
				String sql="SELECT * FROM alert WHERE is_checked=? and id=? order by date desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 0);
				pstmt.setString(2, id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					msg.add(rs.getString("message"));
					date.add(rs.getString("date"));
					msg_cnt++;
				}
				pstmt.close();
				rs.close();
				
				//확인한 메시지를 SELECT
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 1);
				pstmt.setString(2, id);
				//확인된 메시지 -> c_msg, c_date
				rs = pstmt.executeQuery();
				while(rs.next()) {
					c_msg.add(rs.getString("message"));
					c_date.add(rs.getString("date"));
					c_msg_cnt++;
				}
				pstmt.close();
				rs.close();

				//현 시간 이전으로 읽지 않은 메시지들을 전부 읽은 메시지로 표시
				sql="UPDATE alert SET is_checked=? WHERE date<? AND is_checked=0 AND id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 1);
				pstmt.setString(2, timeStamp);
				pstmt.setString(3, id);
				pstmt.executeUpdate();
				
				pstmt.close();
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail_db";
			}
			finally 
			{	
				if ( rs != null ) try{rs.close();}catch(Exception e){}
	            if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
	            if ( conn != null ) try{conn.close();}catch(Exception e){}
			}
			return "success";
		}
		else {
			return "fail";
		}
	}
}
