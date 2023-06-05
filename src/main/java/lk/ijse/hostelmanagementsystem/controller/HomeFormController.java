package lk.ijse.hostelmanagementsystem.controller;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import lk.ijse.hostelmanagementsystem.service.custom.JoinService;
import lk.ijse.hostelmanagementsystem.service.custom.StudentRoomService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.JoinServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentRoomServiceImpl;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;

public class HomeFormController {


    public Label lbl1;
    public Label lbl2;
    public Label lbl3;
    public Label lbl4;
    public LineChart lineChart;
    private JoinService joinService;
    private StudentRoomService studentRoomService;

    public void initialize(){
        joinService=new JoinServiceImpl();
        studentRoomService=new StudentRoomServiceImpl();
        HashMap<String,Integer> list= joinService.getAvailableRoomCount();

        lbl1.setText(String.valueOf(list.get("R-001")));
        lbl2.setText(String.valueOf(list.get("R-002")));
        lbl3.setText(String.valueOf(list.get("R-003")));
        lbl4.setText(String.valueOf(list.get("R-004")));

        setChart();
    }

    public void setChart(){
        HashMap<String,Double> monthlyIncome = studentRoomService.getMonthlyIncome();
        XYChart.Series dataSeries1 =new XYChart.Series();
        dataSeries1.setName(String.valueOf(LocalDate.now().getYear()));
        Double aDouble1 = monthlyIncome.get("4");
        System.out.println(aDouble1);
        for(int i=0; i<12; i++){
            String ar= Month.of(i+1).getDisplayName(TextStyle.FULL ,new Locale("en"));
            Double aDouble = monthlyIncome.get(String.valueOf(i+1));
            if (aDouble!=null){
                dataSeries1.getData().add(new XYChart.Data(ar,aDouble));
            }else {
                dataSeries1.getData().add(new XYChart.Data(ar, 0.0));
            }
        }

        lineChart.getData().add(dataSeries1);
    }
}
