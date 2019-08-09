package sample;

import Util.DBUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;

public class Controller implements Initializable {


    int sum = 0;
    Timer timer;
    int number;
   // int seconds;
    boolean check =true;


    @FXML
    private Pane pane;

    @FXML
    private TextField score;

    @FXML
    private TextField time;



    @FXML
    private ComboBox<String> choose;

    @FXML
    private Button button;

    @FXML
    private LineChart<?, ?> chart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private Button reset;



    @FXML
    void keyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            if(sum==0 && check)
            {//check=true;
                setTimer();
                time.setText("10");
            }
            if(check){
                sum++;
                score.setText("" + sum);}
    }}

 public void setTimer(){
      timer = new Timer();
     timer.schedule(new TimerTask() {
        int seconds=9;

         @Override
         public void run() {
             if (seconds > 0) {
                 time.setText(""+seconds);
                seconds--;
             } else {
                 time.setText("Koniec czasu");
                 timer.cancel();
                 //check = false;
                 button.setDisable(false);
                 score.setDisable(true);
                 time.setDisable(true);
                 seconds=9;
             }
         }
     },  1000,1000);
 }
    XYChart.Series series = new XYChart.Series();
    Calendar calendar = Calendar.getInstance();
    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
    @FXML
    void insertInto(ActionEvent event) throws ClassNotFoundException, SQLException {

        String whatchoosed = choose.getValue();
        String data;
        insert.insertinto(sum, whatchoosed, startDate);
        int hits;
        number= selectrows.selectcount();
       for(int i=1;i<number+1;i++){
           hits = select.selectid(i);
           data = select.selectdate(i);
           series.getData().add(new XYChart.Data(data+"  "+i,hits));
       }

        chart.getData().addAll(series);
        reset.setDisable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            DBUtil.dbConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    @FXML
    void resetProgram(ActionEvent event) throws SQLException, ClassNotFoundException {
        series.getData().clear();
        sum=0;
        button.setDisable(true);
        reset.setDisable(true);
        score.setDisable(false);
        time.setDisable(false);
        score.setText("" + sum);
        DBUtil.dbConnect();
    }

}

