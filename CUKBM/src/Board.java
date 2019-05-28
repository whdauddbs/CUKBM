import java.util.ArrayList;

public class Board {
    private ArrayList<String> titleList = new ArrayList<String>();         // Á¦¸ñ
    private ArrayList<String> writerList = new ArrayList<String>();      // ÀÛ¼ºÀÚ
<<<<<<< HEAD
    private ArrayList<String> dateList = new ArrayList<String>();             // ÀúÀåÀÏÀÚ
    private ArrayList<String> matchDateList = new ArrayList<String>();             // °æ±âÀÏÀÚ
    private ArrayList<Integer> mNumberList = new ArrayList<Integer>();             // ¸ğÁıÀÎ¿ø
    private ArrayList<Integer> currentNumberList = new ArrayList<Integer>();           // ÇöÀç ÀÎ¿ø
    private ArrayList<Integer> isSetList = new ArrayList<Integer>();           // ¸ÅÄ¡ È®Á¤ ¿©ºÎ
    private ArrayList<String> detailList = new ArrayList<String>();           // »ó¼¼ ³»¿ë
    private ArrayList<Integer> isTeamList = new ArrayList<Integer>();           // ÆÀ/°³ÀÎ
    private ArrayList<Integer> eventList = new ArrayList<Integer>();           // Á¾¸ñ
    
=======
    private ArrayList<Date> dateList = new ArrayList<Date>();             // ÀúÀåÀÏÀÚ
    private ArrayList<Time> timeList = new ArrayList<Time>();           // ÀúÀå½Ã°¢
    private ArrayList<Date> matchDateList = new ArrayList<Date>();             // °æ±âÀÏÀÚ
    private ArrayList<Time> matchTimeList = new ArrayList<Time>();           // °æ±â½Ã°¢
    private ArrayList<Integer> mNumber = new ArrayList<Integer>();             // ¸ğÁıÀÎ¿ø
    private ArrayList<Integer> currentNumber = new ArrayList<Integer>();           // ÇöÀç ÀÎ¿ø
    private boolean lastPage = false;      // °Ô½Ã±Û ¸ñ·ÏÀÇ ¸¶Áö¸· ÆäÀÌÁöÀÎÁö ¿©ºÎ
>>>>>>> parent of 7394202... ê²Œì‹œíŒìƒì„±(í…ŒìŠ¤íŠ¸2)
    
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
    public int getListSize() {   // °Ô½Ã±ÛÀÇ ¼ö¸¦ ¸®ÅÏÇÏ´Â ¸Ş¼­µå
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
>>>>>>> parent of 7394202... ê²Œì‹œíŒìƒì„±(í…ŒìŠ¤íŠ¸2)
}
