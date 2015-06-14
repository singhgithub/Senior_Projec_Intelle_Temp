

package screensframework;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import com.sun.javafx.scene.accessibility.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Screen2Controller implements Initializable , ControlledScreen {
	
	ScreensController myController;
	Database database_obj;				//this is not the same obj that i have declared in the serial_Example class
	
	public Screen2Controller() {
		System.out.println("[Screens2Controller Class] Constructor is Called");
		//database_obj = Serial_Example.getDatabase_obj();			//can't put it here cause this class get called before serial_Example, but it might work since its a static ref
	
	}


	// Declaramos el "LineChart" donde pintaremos la funcion
    @FXML private LineChart<Double, Double> graph;
    @FXML private NumberAxis xaxis_id;
    @FXML private NumberAxis yaxis_id;
    XYChart.Series series = new XYChart.Series();
    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    
    //its prints out the systemout in the method and then also this systemout, so comment it out 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("[Screens2Controller Class] initialize() is Called");
        series.setName("Room1");
        series1.setName("Room2");
        series2.setName("Room3");
    	
        /*don't call this here move to the pull method below
         * XYChart.Series series = new XYChart.Series();
        series.setName("Room1");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Room2");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Room3");
        
        Random r = new Random();
        for (int i = 0; i < 40; i++) {   //40 becuase there aren't enough data
        	series.getData().add(new XYChart.Data(i, (65 + ((int)(Math.random()*(80 - 65))))));
        	series1.getData().add(new XYChart.Data(i, (65 + ((int)(Math.random()*(80 - 65))))));
        	series2.getData().add(new XYChart.Data(i, (65 + ((int)(Math.random()*(80 - 65))))));
        	//System.out.println(60 + ((int)(Math.random()*(90 - 60))));
		}
        
        graph.getData().addAll(series, series1, series2);
        System.out.println("[Screens2Controller Class] intialize() Data added to the Graph");*/
    }

    //what this does is allow you to use use the ScreensController class obj in this class (the same object)
	@Override
	public void setScreenParent(ScreensController screenParent) {
		System.out.println("[Screens2Controller Class] setScreenParent() is Called"); 
		myController = screenParent;
	}
	
	//i get an error if i click the graph button twcie about childern douplicate added(pics in screenshot folder), don't worry abt this for nw come back to it to fix
	@FXML
	public void pull_Data_And_Populate_Graph(ActionEvent e){
		System.out.println("[Screens2Controller Class] You Pressed pull_Data_And_Populate_Graph Button "); 
		System.out.println("[Screens2Controller Class] pull_Data_And_Populate_Graph() is Called"); 

		database_obj = Serial_Example.getDatabase_obj();			//this has been moved from the constructor here, cause i been getting null pointer errors 
		
        //System.out.println("[Screens2Controller Class] pull_Data_And_Populate_Graph() - database_obj.pull_Data_For_Room1_Graph()'s Value:  "+ Arrays.toString(database_obj.pull_Data_For_Room1_Graph()));
        
        double[] room1_array = database_obj.pull_Data_For_Room1_Graph();		//redo6 null pointer Error happen here, due to database_obj i think or the way i doing the array
        double[] room2_array = database_obj.pull_Data_For_Room2_Graph();
        double[] room3_array = database_obj.pull_Data_For_Room3_Graph();
        
        Random r = new Random();
        for (int i = 0; i < 40; i++) {   //40 becuase there aren't enough data
        	series.getData().add(new XYChart.Data( i, room1_array[i] ));
        	series1.getData().add(new XYChart.Data( i, room2_array[i] ));
        	series2.getData().add(new XYChart.Data( i, room3_array[i] ));
        	//System.out.println(60 + ((int)(Math.random()*(90 - 60))));
		}
        
        graph.getData().addAll(series, series1, series2);			//got an error for this line about saying " duplicate children added: parent = Group@feaef0[styleClass=plot-content]"
        System.out.println("[Screens2Controller Class] pull_Data_And_Populate_Graph() Data added to the Graph");
	}
}
