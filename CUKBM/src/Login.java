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
		
		//DB ���� �� ó��
		Connection conn;
		Statement stmt;
		
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cukbm?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","root123");
			if (conn == null) {
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			} 
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_info WHERE id='"+id+"';"); // ������ �ۼ�
			if(!rs.next()) {
				throw new Exception("�ش��ϴ� ���̵� �����ϴ�.");
			}
			
			String DB_id = rs.getString("id");
			String DB_pw = rs.getString("pw");
			
			//id, pw ��ġ���� Ȯ�� �� ��ġ�ϸ� success, ��ġ���� ������ fail ����
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
