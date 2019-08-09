package sample;

import Util.DBUtil;

import java.sql.Date;
import java.sql.SQLException;

public class insert {
    public static void insertinto(int sum, String choose, Date date) throws SQLException, ClassNotFoundException{
        String sql = "insert into WYNIKI(DATA, LICZBA_KLIKNIEC, WYBOR) values('"+date+"','"+sum+"','"+choose+"');";
        try{
            DBUtil.dbExcecuteQuery(sql);
        }
        catch(SQLException e){
            System.out.println("Nie udalo sie");
            throw e;
        }
    }
}