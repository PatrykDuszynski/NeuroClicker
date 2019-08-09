package sample;

import Util.DBUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectrows {
    public static int selectcount() throws SQLException, ClassNotFoundException{
        ResultSet rs;
        String sql = "select count(*) as total from WYNIKI;";
           rs = DBUtil.dbExecute(sql);
        int number = 0;
        while(rs.next()){
            number = rs.getInt(1);
        }


        return number;
    }

}
