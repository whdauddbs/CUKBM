import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;

public class SetMatch {
	//��ġ�� Ȯ���ϴ� Ŭ����
	public SetMatch() {}

	
	public void changeSet(String date) throws ServletException { // set �ٲٰ�, �˶� ���� ����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(new Date());

        try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root123");
	        if (conn == null)
	        	throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
	        
	        String sql = "update match_info set is_set=1 where date=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1,  date);
	        pstmt.executeUpdate();
	        pstmt.close();
	        
	        sql = "SELECT p.m_name, p.id FROM p_match p,match_info m WHERE m.date=? AND p.date=m.date";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1,  date);
	        rs = pstmt.executeQuery();
	        ArrayList<String> m_name = new ArrayList<>();
	        ArrayList<String> id = new ArrayList<>();
	        if(!rs.next()) {
	        	throw new Exception("�˻��� ���� ����");
	        }
	        rs.beforeFirst();
	        while(rs.next()) {
	        	m_name.add(rs.getString("m_name"));
	        	id.add(rs.getString("id"));
	        }
	        pstmt.close();
	        rs.close();
	        
	        sql = "insert into alert(id, message, date, is_checked) values(?, ?, ?, 0)";
	        for(int i=0; i<m_name.size() ; i++) {
	        	pstmt = conn.prepareStatement(sql);
	        	pstmt.setString(1, id.get(i));
	        	pstmt.setString(2,  "<" + m_name.get(i) + "> ��ġ�� Ȯ���Ǿ����ϴ�.");
	        	pstmt.setString(3,  timeStamp);
		        pstmt.executeUpdate();
	        }
	        rs.close();
	        pstmt.close();
	        conn.close();
        }
        catch (Exception e) {
        	throw new ServletException(e);
        }
        finally {
        	if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
        }
	}
}
