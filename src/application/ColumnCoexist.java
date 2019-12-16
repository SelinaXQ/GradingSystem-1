package application;

import javafx.scene.control.TableColumn;

public class ColumnCoexist {
    TableColumn a,b;
    public ColumnCoexist(TableColumn a,TableColumn b){
        this.a=a;
        this.b=b;
    }
    public void switchVisible(){
        if (a.isVisible()){
            a.setVisible(false);
            b.setVisible(true);
        }
        else
            if(b.isVisible()){
                a.setVisible(true);
                b.setVisible(false);
            }
    }
}
