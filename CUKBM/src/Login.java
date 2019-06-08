import java.sql.*;

public class Login {
	private String id;
	private String pw;
	
	public Login() {}
	public void setID(String id) {
		this.id = id;
	}
	public void setPW(String pw) {
		this.pw = pw;
	}
	public String getLoginResult() {
		
		//DB 연결 후 처리
		Connection conn;
		Statement stmt;
		
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root123");
			if (conn == null) {
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			} 
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_info WHERE id='"+id+"';"); // 쿼리문 작성
			if(!rs.next()) {
				throw new Exception("해당하는 아이디가 없습니다.");
			}
			
			String DB_id = rs.getString("id");
			String DB_pw = rs.getString("pw");
			
			//id, pw 일치여부 확인 후 일치하면 success, 일치하지 않으면 fail 리턴
			if(DB_id!=null) {
				if(DB_pw.equals(pw)) {
					return "success";
				}
				else {
					return "fail";
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return "error";
		
	}
}
