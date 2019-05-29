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
    private ArrayList<Integer> isSetList = new ArrayList<Integer>();           // 매치 확정 여부
    private ArrayList<String> detailList = new ArrayList<String>();           // 상세 내용
    private ArrayList<Integer> isTeamList = new ArrayList<Integer>();           // 팀/개인
    private ArrayList<Integer> eventList = new ArrayList<Integer>();           // 종목
    private int dataCnt = 0;
    
    
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
    public void setIsSet(int index, Integer isSet) {
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
    public void setDataCnt(int num) {
        this.dataCnt = num;
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
    public int getDataCnt() {
        return dataCnt;
    }
}
