import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;

public class Board {
    private ArrayList<String> titleList = new ArrayList<String>();         // 제목
    private ArrayList<String> writerList = new ArrayList<String>();      // 작성자
    private ArrayList<String> dateList = new ArrayList<String>();             // 저장일자
    private ArrayList<String> matchDateList = new ArrayList<String>();             // 경기일자
    private ArrayList<Integer> mNumberList = new ArrayList<Integer>();             // 모집인원
    private ArrayList<Integer> currentNumberList = new ArrayList<Integer>();           // 현재 인원
    private ArrayList<Integer> isSetList = new ArrayList<Integer>();           // 현재 인원
    private ArrayList<String> detailList = new ArrayList<String>();           // 현재 인원
    private ArrayList<Integer> isTeamList = new ArrayList<Integer>();           // 현재 인원
    private ArrayList<Integer> eventList = new ArrayList<Integer>();           // 현재 인원
    
    
    public Board() {
    }
    public void setTitle(int index, String title) {
         this.titleList.add(index, title);
    }
    public void setWriter(int index, String writer) {
         this.writerList.add(index, writer);
    }
    public void setDate(int index, String date) {
         this.dateList.add(index, date);
    }
    public void setMatchDate(int index, String mDate) {
        this.matchDateList.add(index, mDate);
   }
    public void setMNumber(int index,  int mNumber) {
        this.mNumberList.add(index, mNumber);
   }
    public void setCurrentNumber(int index, int currentNumber) {
        this.currentNumberList.add(index, currentNumber);
   }
    public void setIsSetTitle(int index, Integer isSet) {
        this.isSetList.add(index, isSet);
   }
    public void setDetail(int index, String detail) {
        this.detailList.add(index, detail);
   }
    public void setIsTeam(int index, Integer isTeam) {
        this.isTeamList.add(index, isTeam);
   }
    public void setEvent(int index, Integer event) {
        this.eventList.add(index, event);
   }
    
    public String[] getTitle() {
         return titleList.toArray(new String[titleList.size()]);
    }
    public String[] getWriter() {
         return writerList.toArray(new String[writerList.size()]);
    }
    public String[] getDate() {
        return dateList.toArray(new String[dateList.size()]);
    }
    public String[] getMatchDate() {
         return matchDateList.toArray(new String[matchDateList.size()]);
    }
    public Integer[] getMNumber() {
        return mNumberList.toArray(new Integer[mNumberList.size()]);
    }
    public Integer[] getCurrentNumber() {
        return currentNumberList.toArray(new Integer[currentNumberList.size()]);
    }
    public Integer[] getIsSet() {
        return isSetList.toArray(new Integer[isSetList.size()]);
    }
    public String[] getDetail() {
        return detailList.toArray(new String[detailList.size()]);
    }
    public Integer[] getIsTeam() {
        return isTeamList.toArray(new Integer[isTeamList.size()]);
    }
    public Integer[] getEvent() {
        return eventList.toArray(new Integer[eventList.size()]);
    }
    private void readDB(int page) throws ServletException {
        Connection conn = null;
        Statement stmt = null;
        try {
        	
        	Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm","root","1234");
            if (conn == null)
            	throw new Exception("데이터베이스에 연결할 수 없습니다.");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from match_info order by date desc;");
            
            for (int cnt = 0; cnt < page*10; cnt++) { // 
                if (!rs.next()) {
                	break;
                }
                if(cnt>=(page-1)*10) {
          		  	setTitle(cnt%10, rs.getString("m_name"));
          		  	setWriter(cnt%10, rs.getString("id"));
          	  	}
                 
            }
          
     }
     catch (Exception e) {
           throw new ServletException(e);
     }
     finally {
           try {
                 stmt.close();
           }
          catch (Exception ignored) {
           }
           try {
                 conn.close();
           }
          catch (Exception ignored) {
           }
     }
     return list;
}
}
