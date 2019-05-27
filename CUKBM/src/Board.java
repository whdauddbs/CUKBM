import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Board {
    private ArrayList<String> titleList = new ArrayList<String>();         // ����
    private ArrayList<String> writerList = new ArrayList<String>();      // �ۼ���
    private ArrayList<Date> dateList = new ArrayList<Date>();             // ��������
    private ArrayList<Time> timeList = new ArrayList<Time>();           // ����ð�
    private ArrayList<Date> matchDateList = new ArrayList<Date>();             // �������
    private ArrayList<Time> matchTimeList = new ArrayList<Time>();           // ���ð�
    private ArrayList<Integer> mNumber = new ArrayList<Integer>();             // �����ο�
    private ArrayList<Integer> currentNumber = new ArrayList<Integer>();           // ���� �ο�
    private boolean lastPage = false;      // �Խñ� ����� ������ ���������� ����
    
    public Board() {
    }
    public void setTitle(int index, String title) {
         this.titleList.add(index, title);
    }
    public void setWriter(int index, String writer) {
         this.writerList.add(index, writer);
    }
    public void setDate(int index, Date date) {
         this.dateList.add(index, date);
    }
    public void setTime(int index, Time time) {
         this.timeList.add(index, time);
    }
    public void setLastPage(boolean lastPage) {
         this.lastPage = lastPage;
    }
    public Integer[] getSeqNo() {
         return numberList.toArray(new Integer[numberList.size()]);
    }
    public String[] getTitle() {
         return titleList.toArray(new String[titleList.size()]);
    }
    public String[] getWriter() {
         return writerList.toArray(new String[writerList.size()]);
    }
    public Date[] getDate() {
         return dateList.toArray(new Date[dateList.size()]);
    }
    public Time[] getTime() {
         return timeList.toArray(new Time[timeList.size()]);
    }
    public boolean isLastPage() {
         return lastPage;
    }     
    public int getListSize() {   // �Խñ��� ���� �����ϴ� �޼���
         return numberList.size();
    }
}
