import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;

public class ShowMatch {
	//Ư�� ��ġ�� DB���� �˻��ϰ� �������� Ŭ����
	public ShowMatch() {}
	
	private String date;
	private String m_name, id, m_date, detail, event;
	private Integer m_number, c_number, is_set, team;
	
	public void setDate(String date) { this.date=date; }
	
	public String getM_name() { return this.m_name; }
	public String getId() { return this.id; }
	public String getDate() { return this.date; }
	public String getM_date() { return this.m_date; }
	public Integer getM_number() { return this.m_number; }
	public Integer getC_number() { return this.c_number; }
	public Integer getIs_set() { return this.is_set; }
	public String getDetail() { return this.detail; }
	public Integer getTeam() { return this.team; }
	public String getEvent() { return this.event; }
	
	public String readDB() {

		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try { 
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC","root","root123");
	        if (conn == null)
	        	throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery("select * from match_info where date = '" + date + "';");
	      
	        if (!rs.next()) {
	        	System.out.println("ShowMatch : �˻���� ����");
	        	return "fail";
	        }
	        else {
	        	m_name = rs.getString("m_name");
	        	id = rs.getString("id");
	        	m_date = rs.getString("m_date");
	        	date = rs.getString("date");
	        	m_number = new Integer(rs.getInt("m_number"));
	        	c_number = new Integer(rs.getInt("c_number"));
	        	is_set = new Integer(rs.getInt("is_set"));
	        	detail = rs.getString("detail");
	        	team = new Integer(rs.getInt("team"));
	        	event = rs.getString("event");
	        	
	        	System.out.println("���˻���� Ȯ�� :: showMatch.java :"+m_name);
	        }
	        
	        stmt.close();
	        rs.close();
	        conn.close();
	        
	        return "success";
	        
	    }
	    catch (Exception e) {
	    	return e.getMessage();
	    }
	    finally {
	    	if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( stmt != null ) try{stmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
	    }
	}
	
}
