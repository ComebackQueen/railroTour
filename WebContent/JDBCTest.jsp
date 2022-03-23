<%@ page contentType="text/html; charset=euc-kr"%>

<%@ page import="java.util.*" %>

<%@ page import="java.sql.*" %>

<%@ page import="javax.sql.*" %>

<%@ page import="javax.naming.*" %>

<%

    Connection conn = null;

    PreparedStatement pstmt = null;

    ResultSet rs = null;

    try {

 

        Context initContext = new InitialContext();               

        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/CUBRIDDS");     

              conn = ds.getConnection();                   

              String sql = "select name from member";                                                                                                                                                                                    

              pstmt = conn.prepareStatement(sql);

              rs = pstmt.executeQuery();
			out.println("시작전");
              while(rs.next()) {

                  out.println("name ==> " + rs.getString(1));

                  out.println("<br>");

              }
              out.println("완료");

      } catch ( SQLException e ) {

              e.printStackTrace();

      } catch ( Exception e ) {

              e.printStackTrace();

      } finally {

          try{

                if ( rs != null ) rs.close();

              }catch( Exception e ) {}

          try{

                if ( pstmt != null ) pstmt.close();

              }catch( Exception e ) {}

          try{

                if ( conn != null ) conn.close();

              }catch( Exception e ) {}

      }

%>