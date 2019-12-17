package application;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import uitable.Overview;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {
    OverviewController overviewController = new OverviewController();
    @FXML
    ListView<String> list;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Overview> overviewList = overviewController.getOverview();
        double[] temp = new double[overviewList.size()];
        double min=100,max=0,mean=0,medium=0;
        int k=0;
        for(Overview i:overviewList){
            if (i.getTotal()<min) min=i.getTotal();
            if (i.getTotal()>max) max=i.getTotal();
            mean+=i.getTotal();
            temp[k]=i.getTotal();
            k++;
        }
        mean=mean/overviewList.size();
        Arrays.sort(temp);
        medium = temp[overviewList.size()/2];
        list.getItems().add("Min "+min);
        list.getItems().add("Max "+max);
        list.getItems().add("Mean "+mean);
        list.getItems().add("Medium "+medium);
    }

}
