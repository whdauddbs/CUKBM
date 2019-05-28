import java.util.ArrayList;

public class Board {
    private ArrayList<String> titleList = new ArrayList<String>();         // 제목
    private ArrayList<String> writerList = new ArrayList<String>();      // 작성자
<<<<<<< HEAD
    private ArrayList<String> dateList = new ArrayList<String>();             // 저장일자
    private ArrayList<String> matchDateList = new ArrayList<String>();             // 경기일자
    private ArrayList<Integer> mNumberList = new ArrayList<Integer>();             // 모집인원
    private ArrayList<Integer> currentNumberList = new ArrayList<Integer>();           // 현재 인원
    private ArrayList<Integer> isSetList = new ArrayList<Integer>();           // 매치 확정 여부
    private ArrayList<String> detailList = new ArrayList<String>();           // 상세 내용
    private ArrayList<Integer> isTeamList = new ArrayList<Integer>();           // 팀/개인
    private ArrayList<Integer> eventList = new ArrayList<Integer>();           // 종목
    
=======
    private ArrayList<Date> dateList = new ArrayList<Date>();             // 저장일자
    private ArrayList<Time> timeList = new ArrayList<Time>();           // 저장시각
    private ArrayList<Date> matchDateList = new ArrayList<Date>();             // 경기일자
    private ArrayList<Time> matchTimeList = new ArrayList<Time>();           // 경기시각
    private ArrayList<Integer> mNumber = new ArrayList<Integer>();             // 모집인원
    private ArrayList<Integer> currentNumber = new ArrayList<Integer>();           // 현재 인원
    private boolean lastPage = false;      // 게시글 목록의 마지막 페이지인지 여부
>>>>>>> parent of 7394202... 寃뚯떆�뙋�깮�꽦(�뀒�뒪�듃2)
    
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
    public int getListSize() {   // 게시글의 수를 리턴하는 메서드
         return numberList.size();
    }
<<<<<<< HEAD
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
=======
>>>>>>> parent of 7394202... 寃뚯떆�뙋�깮�꽦(�뀒�뒪�듃2)
}
