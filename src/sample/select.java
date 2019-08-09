package sample;

import Util.DBUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class select {
    public static int selectid(int id) throws SQLException, ClassNotFoundException{
        ResultSet rs;
        String sql = "SELECT liczba_klikniec from WYNIKI where id="+id+";";
            rs = DBUtil.dbExecute(sql);
        int number=0;
        while(rs.next()){
            number = rs.getInt(1);
        }
        return number;
    }
    public static String selectdate(int id) throws SQLException, ClassNotFoundException{
        ResultSet rs;
        String sql = "SELECT data from WYNIKI where id="+id+";";
        try{
            rs = DBUtil.dbExecute(sql);
        }
        catch(SQLException e){
            System.out.println("Nie udalo sie");
            throw e;
        }
        String number = "0";
        while(rs.next()){
            number = rs.getString(1);
        }
        return number;
    }
}
