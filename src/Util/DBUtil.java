package Util;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class DBUtil {
    private static final String JDBC_DRIVER = "org.firebirdsql.jdbc.FBDriver";

    private static Connection connection = null;

    private static final String connStr = "jdbc:firebirdsql://localhost:3050/D:/BAZA.FDB";

    public static void dbConnect() throws SQLException, ClassNotFoundException{
        try{
            Class.forName(JDBC_DRIVER);
        }
        catch (ClassNotFoundException e){
            System.out.println("Where is your Driver");
            e.printStackTrace();
           throw e;
        }

        System.out.println("JDBC driver zarejestrowany");

        try{

            connection = DriverManager.getConnection(connStr,"SYSDBA","masterkey");
        }

        catch(SQLException e){
            System.out.println("Connection to database failed");
            System.exit(0);
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException{
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        catch (Exception e){
            //throw e;
        }
    }

    public static void dbExcecuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException{
        Statement stmt = null;
        try{
            dbConnect();
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        }
        catch(SQLException e){
            System.out.println("Problem occured");
            //throw e;
        }
        finally {
            if(stmt!=null){
                stmt.close();
            }
            dbDisconnect();
        }
    }
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException, SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;

        try{
            dbConnect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        }
        catch (SQLException e){
            System.out.println("Error");
            //throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }
}
