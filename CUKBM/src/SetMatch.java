import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;

public class SetMatch {
	//매치를 확정하는 클래스
	public SetMatch() {}

	
	public void changeSet(String date) throws ServletException { // set 바꾸고, 알람 쿼리 전송
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(new Date());

        try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC","root","root123");
	        if (conn == null)
	        	throw new Exception("데이터베이스에 연결할 수 없습니다.");
	        stmt = conn.createStatement();
	        stmt.executeQuery("update match_info set is_set=1 where date = " + date + ";");
	        rs = stmt.executeQuery("select * from match_info where date = " + date + ";");
	        if (!rs.next()) {
            }
	        else {
		        String m_name = rs.getString("m_name");
		        rs = stmt.executeQuery("select * from p_match where m_name = " + m_name + ";");
		        while(true) {
		        	if (!rs.next()) {
		            	break;
		            }
		        	stmt.executeQuery(String.format("insert into alert(id, message, date, is_checked) values('%s', '%s', '%s', %s;",
	                        rs.getString("id"), "매치가 확정되었습니다.", timeStamp, 0));
		        }
	        }
        }
        catch (Exception e) {
        	throw new ServletException(e);
        }
        finally {
        	if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( stmt != null ) try{stmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
        }
	return;
	}
}
