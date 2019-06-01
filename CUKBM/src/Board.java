import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;

public class Board {
    private ArrayList<String> m_name = new ArrayList<String>();         // 제목
    private ArrayList<String> id = new ArrayList<String>();      // 작성자
    private ArrayList<String> date = new ArrayList<String>();             // 저장일자
    private ArrayList<String> m_date = new ArrayList<String>();             // 경기일자
    private ArrayList<Integer> m_number = new ArrayList<Integer>();             // 모집인원
    private ArrayList<Integer> c_number = new ArrayList<Integer>();           // 현재 인원
    private ArrayList<Integer> is_set = new ArrayList<Integer>();           // 매치 확정 여부
    private ArrayList<String> detail = new ArrayList<String>();           // 상세 내용
    private ArrayList<Integer> team = new ArrayList<Integer>();           // 팀/개인
    private ArrayList<String> event = new ArrayList<String>();           // 종목
    
    private String value;
    private int pageNum;
    
    public Board() {}

    
    public void setPageNum(int pageNum) {
    	this.pageNum = pageNum;
    }
    public void setEvent(String event) {
    	this.value = event;
    }
    
//    public void setTitle(int index, String title) {
//         this.m_name.add(index, title);
//    }
//    public void setWriter(int index, String writer) {
//         this.id.add(index, writer);
//    }
//    public void setDate(int index, String date) {
//         this.date.add(index, date);
//    }
//    public void setm_date(int index, String mDate) {
//        this.m_date.add(index, mDate);
//   }
//    public void setMNumber(int index,  int mNumber) {
//        this.m_number.add(index, mNumber);
//   }
//    public void setCurrentNumber(int index, int currentNumber) {
//        this.currentNumberList.add(index, currentNumber);
//   }
//    public void setIsSet(int index, Integer isSet) {
//        this.is_set.add(index, isSet);
//   }
//    public void setDetail(int index, String detail) {
//        this.detail.add(index, detail);
//   }
//    public void setIsTeam(int index, Integer isTeam) {
//        this.team.add(index, isTeam);
//   }
//    public void setEvent(int index, String event) {
//        this.event.add(index, event);
//   }
//    public void setDataCnt(int num) {
//        this.dataCnt = num;
//   }
    
    
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
    
    public String readDB() {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?serverTimezone=UTC","root","root123");
	        if (conn == null)
	        	throw new Exception("데이터베이스에 연결할 수 없습니다.");
	        String sql = "select * from match_info where event=? order by date desc Limit ?,?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1,  value);
	        pstmt.setInt(2, (pageNum-1)*10);
	        pstmt.setInt(3, pageNum*10);
	        
	        rs = pstmt.executeQuery();
	        
	        int i=0;
	        while (rs.next()) {
            	m_name.add(rs.getString("m_name"));
      		  	m_date.add(rs.getString("m_date"));
      		  	m_number.add(rs.getInt("m_number"));
      		  	c_number.add(rs.getInt("c_number"));
      		  	is_set.add(rs.getInt("is_set"));
      		  	date.add(rs.getString("date"));
            }
	        
	        return "success";
	        
        }
        catch (Exception e) {
        	System.out.println("실패 :"+e.getMessage());
        	return "fail";
        }
        finally {
        	if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
        }
    }
}
