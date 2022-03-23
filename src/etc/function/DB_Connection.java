package etc.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DB_Connection {
	public Connection con = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	public String Key="JXL40bCK2WGOu%2FE1WOGjuALpADt64Wb2mQVwNpxiA0bre%2FV8GozZggM2O01%2FPaTTyNm0A2JahebDf%2FPGwW8jbg%3D%3D";
	public String Train_Key="DrVFgkqXkuQbgQfBPCyTkqsUDXXukiY3pGLkMGgXwCA%2BMZ2XCGAmvQdQtDm8QlqKejostGbcJSHKR0Ru8n8Weg%3D%3D";
	public DB_Connection() {
		try {
			Context init=new InitialContext();
	        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/CUBRIDDS");
            con = ds.getConnection();   
		}catch(Exception ex) {
			System.out.println("DB 접속에러:"+ex);
			return;
		}
	}
}
