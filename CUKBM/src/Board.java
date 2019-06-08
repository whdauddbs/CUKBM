import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;

public class Board {
    private ArrayList<String> m_name = new ArrayList<String>();         // ����
    private ArrayList<String> id = new ArrayList<String>();      // �ۼ���
    private ArrayList<String> date = new ArrayList<String>();             // ��������
    private ArrayList<String> m_date = new ArrayList<String>();             // �������
    private ArrayList<Integer> m_number = new ArrayList<Integer>();             // �����ο�
    private ArrayList<Integer> c_number = new ArrayList<Integer>();           // ���� �ο�
    private ArrayList<Integer> is_set = new ArrayList<Integer>();           // ��ġ Ȯ�� ����
    private ArrayList<String> detail = new ArrayList<String>();           // �� ����
    private ArrayList<Integer> team = new ArrayList<Integer>();           // ��/����
    private ArrayList<String> event = new ArrayList<String>();           // ����
    
    private String value;
    private int pageNum;
    private int board_cnt;
        
    public Board() {}

    
    public void setPageNum(int pageNum) {
    	this.pageNum = pageNum;
    }
    public void setEvent(String event) {
    	this.value = event;
    }
    
    public String[] getTitle() {
         return m_name.toArray(new String[m_name.size()]);
    }
    public String[] getWriter() {
         return id.toArray(new String[id.size()]);
    }
    public String[] getDate() { 
        return date.toArray(new String[date.size()]);
    }
    public String[] getm_date() {
         return m_date.toArray(new String[m_date.size()]);
    }
    public Integer[] getMNumber() {
        return m_number.toArray(new Integer[m_number.size()]);
    }
    public Integer[] getCNumber() {
        return c_number.toArray(new Integer[c_number.size()]);
    }
    public Integer[] getIsSet() {
        return is_set.toArray(new Integer[is_set.size()]);
    }
    public String[] getDetail() {
        return detail.toArray(new String[detail.size()]);
    }
    public Integer[] getIsTeam() {
        return team.toArray(new Integer[team.size()]);
    }
    public String[] getEvent() {
        return event.toArray(new String[event.size()]);
    }
    public int getBoardCnt() {
    	return board_cnt;
    }
    
    public void countDB(int select) {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;
        try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root123");
	        if (conn == null)
	        	throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
	        if(select==2) {
		        sql = "select count(*) from match_info where event=?;";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1,value);
		        rs = pstmt.executeQuery();
		        rs.next();
		        board_cnt = rs.getInt("count(*)"); 
	        }
	        else {
	        	sql = "select count(*) from match_info where event=? and team=?;";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1,value);
		        pstmt.setInt(2, select);
		        rs = pstmt.executeQuery();
		        rs.next();
		        board_cnt = rs.getInt("count(*)"); 
	        }
        }
        catch (Exception e) {
        	System.out.println("���� :"+e.getMessage());
        }
        finally {
        	if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
        }
    }
    
    public String readDB(int select) {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;
        try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC","root","root123");
	        if (conn == null)
	        	throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
	        if(select == 2) {
		        sql = "select * from match_info where event=? order by date desc Limit ?,?";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1,  value);
		        pstmt.setInt(2, (pageNum-1)*10);
		        pstmt.setInt(3, 10);
	        }
	        else {
	        	sql = "select * from match_info where event=? and team=? order by date desc Limit ?,?";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1,  value);
		        pstmt.setInt(2, select);
		        pstmt.setInt(3, (pageNum-1)*10);
		        pstmt.setInt(4, 10);
	        }
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
            	m_name.add(rs.getString("m_name"));
            	id.add(rs.getString("id"));
      		  	m_date.add(rs.getString("m_date"));
      		  	date.add(rs.getString("date"));
      		  	m_number.add(rs.getInt("m_number"));
      		  	c_number.add(rs.getInt("c_number"));
      		  	is_set.add(rs.getInt("is_set"));
      		  	detail.add(rs.getString("detail"));
      		  	team.add(rs.getInt("team"));
      		  	event.add(rs.getString("event"));
            }
	        
	        return "success";
	        
        }
        catch (Exception e) {
        	System.out.println("���� :"+e.getMessage());
        	return "fail";
        }
        finally {
        	if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
        }
    }
}
