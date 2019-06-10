import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CancelMatch {

	
	private String id;
	private String date;
	
	public void setDate(String date) {
		this.date = date;
	}
	public void setId(String id) {
		this.id=id;
	}
	
	public CancelMatch() {
		
	}
	
	public void deleteDB() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root123");
			if(conn == null) {
				throw new Exception("db연결 불가");
			}
//			System.out.println("Create : " +  + );
			
			String sql = "UPDATE match_info SET c_number=c_number-1 WHERE date=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  date);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			sql = "DELETE FROM p_match WHERE id=? AND date=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			pstmt.setString(2,  date);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}
		catch (Exception e) {
	    	
	    }
	    finally {
	    	if ( rs != null ) try{rs.close();}catch(Exception e){}
            if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
            if ( conn != null ) try{conn.close();}catch(Exception e){}
	    }
		
	}
}
