/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License"). You
 * may not use this file except in compliance with the License. You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */ 

package screensframework;

import com.enzo.packagee.DemoGauge;
import com.enzo.packagee.Gauge;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScreensFramework extends Application {
	
	public ScreensFramework() {
		System.out.println("[ScreensFramework Class] Constructor is Called");
	}
    
    public static String screen1ID = "main";
    public static String screen1File = "Screen1.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "Screen2.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "Screen3.fxml";
    Serial_Example serial_obj;				//declare it here so i can be used globaly for the guage listeners
    private EventHandler<MouseEvent> mouseEventHandler;
    
    @Override
    public void start(Stage primaryStage) {
        
    	System.out.println("[ScreensFramework Class] start() is Called");
    	
        /*let not do this 
    	 * Serial_Example serial_obj = new Serial_Example();
    	serial_obj.open_Serial_Port();*/
    	//this can't be called here because i need the Gauge Class objs for all 3 gauges which are in DemoGauge Class
    	//serial_obj.add_Serial_Listener(null, null, null);
    	
    	//ScreensController mainContainer = new ScreensController();
        DemoGauge demoGuage = new DemoGauge();
        ScreensController mainContainer = new ScreensController();			//move it down here cause i want the gauges to get done first
        
        /*Database database_obj = new Database();
        database_obj.connect_to_the_database();
        database_obj.insert_query_to_db("hello4");*/
        
        //So all the screens get loaded into the MainScreen, remember that 
        //what i am doing is loading all the screens at once and putting them in a VBox 
        //not gonna load this screen instead i will be adding the guagues 
        //mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
       
        HBox root_guage = new HBox();
        root_guage.setStyle("-fx-background-color: #CECEF6;");		//"alignment=CENTER;" Doesn't Work 
        root_guage.setPrefHeight(800);
        root_guage.setPrefWidth(800);
        root_guage.setMinHeight(800);
        root_guage.setMinWidth(800);
        root_guage.setMaxWidth(800);
        root_guage.setMinWidth(800);
        
        //didn't work, it didn't center the guages 
        //root_guage.setAlignment(Pos.CENTER);
        
        //doesn;t work
        //root_guage.setAlignment(Pos.BASELINE_CENTER);
        
        //didn't work
        //root_guage.setAlignment(Pos.BOTTOM_CENTER);
        
        /*chnaged to below because i got a red cross for error 
          * root_guage.getChildren().add(demoGuage.getControl());
        root_guage.getChildren().add(demoGuage.getControl1());
        root_guage.getChildren().add(demoGuage.getControl2());*/
        root_guage.getChildren().add(demoGuage.control);
        root_guage.getChildren().add(demoGuage.control1);
        root_guage.getChildren().add(demoGuage.control2);
        
        
        //didn't work, the gauges still on the top
        //root.setAlignment(Pos.CENTER);
        
        //this is where the guages,graphs,user interf and the button apperas
        HBox root = new HBox(root_guage);
        
        //didn't work
        //root.setAlignment(Pos.CENTER);
        
        
        //this is button is at
        HBox root_b = new HBox();
        
        Button b = new Button("<");
        Button b1 = new Button(">");
        
        root_b.getChildren().add(b1);
        root_b.getChildren().add(b);
        
        //this is the overall main layout 
        VBox root_main = new VBox(root_b, root);
        System.out.println("[ScreensFramework Class] Hbox(for buttons) & Hbox(for gauges,graphs) Obj's added to Vbox");
        
        //doesn't work, it does put the guage to center, but when the app state takes me striaght to grpah screen
        //root_main.setAlignment(Pos.CENTER);
        
        //this caused the screens to be displayed as FlowPane one after the other
        //not gonna load this screen instead i will be adding the guagues 
        //root.getChildren().add(mainContainer.getScreen(screen1ID));
        root.getChildren().add(mainContainer.getScreen(screen2ID));
        root.getChildren().add(mainContainer.getScreen(screen3ID));
        
        Scene scene = new Scene(root_main,800,500);
        System.out.println("[ScreensFramework Class] Main VBox added to Scene");
        
        scene.getStylesheets().add("com/enzo/packagee/demo.css");
        
        primaryStage.setScene(scene);
        
        /*primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("CTRL+X"));
        */
        
        listener_for_b(mainContainer, root_guage, b, root_main);
        
        mouseEventHandler = mouseEvent -> handle_Gauges_Mouse_Event(mouseEvent);
        DemoGauge.control.setOnMouseReleased(mouseEventHandler);
        DemoGauge.control1.setOnMouseReleased(mouseEventHandler);
        DemoGauge.control2.setOnMouseReleased(mouseEventHandler);
        
        primaryStage.show();
        
        //calling the serial class at the end 
        serial_obj = new Serial_Example();
        serial_obj.open_Serial_Port();
        serial_obj.add_Serial_Listener();
        
    }

	private void handle_Gauges_Mouse_Event(MouseEvent mouseEvent) {
		System.out.println("[ScreensFramework Class] handle_Gauges_Mouse_Event() is Called");
		
		//i could do DemoGauge.control here, but whatever, didn't want to make DemoGuage global, i think it should be all gud
		if (DemoGauge.getControl() == mouseEvent.getSource()) {
			System.out.println("[ScreensFramework Class] handle_Gauges_Mouse_Event() 1st if statement is Called");
			System.out.println("[ScreensFramework Class] handle_Gauges_Mouse_Event() you RELEASED guage1");
			
			User_Set_Temp_Class.getUser_s_t_c_obj().get_room1_threshold_send_to_arduino(DemoGauge.getControl(), serial_obj);
			
		}else if (DemoGauge.getControl1() == mouseEvent.getSource()) {
			System.out.println("[ScreensFramework Class] handle_Gauges_Mouse_Event() 1st elseif statement is Called");
			System.out.println("[ScreensFramework Class] handle_Gauges_Mouse_Event() you RELEASED guage2");
			
			User_Set_Temp_Class.getUser_s_t_c_obj().get_room2_threshold_send_to_arduino(DemoGauge.getControl1(), serial_obj);
			
		//}else if (DemoGauge.getControl2() == mouseEvent.getSource()) {
		}else {
			System.out.println("[ScreensFramework Class] handle_Gauges_Mouse_Event() 2st elseif statement is Called");
			System.out.println("[ScreensFramework Class] handle_Gauges_Mouse_Event() you RELEASED guage3");
			
			User_Set_Temp_Class.getUser_s_t_c_obj().get_room3_threshold_send_to_arduino(DemoGauge.getControl2(), serial_obj);
			
		}
		
	}

	public void listener_for_b(ScreensController mainContainer,
			HBox root_guage, Button b, VBox root_main) {
		
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
		    	System.out.println("[ScreensFramework Class] setOnAction() handle() is Called for < button");
				
				//TranslateTransition slideUp_1 = new TranslateTransition(Duration.seconds(2), mainContainer.getScreen(screen1ID));
				TranslateTransition slide_left_1 = new TranslateTransition(Duration.seconds(2), root_guage);
				TranslateTransition slide_left_2 = new TranslateTransition(Duration.seconds(2), mainContainer.getScreen(screen2ID));
				TranslateTransition slide_left_3 = new TranslateTransition(Duration.seconds(2), mainContainer.getScreen(screen3ID));
				slide_left_1.setByX(-800);
				slide_left_2.setByX(-800);
				slide_left_3.setByX(-800);
				
				ParallelTransition pt = new ParallelTransition(slide_left_1,slide_left_2,slide_left_3);
			       pt.play();
				
			}
		});
        
        //this is a swipe event if you swipe left on the screen it goes left
        root_main.setOnSwipeLeft(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				TranslateTransition slide_left_1 = new TranslateTransition(Duration.seconds(2), root_guage);
				TranslateTransition slide_left_2 = new TranslateTransition(Duration.seconds(2), mainContainer.getScreen(screen2ID));
				TranslateTransition slide_left_3 = new TranslateTransition(Duration.seconds(2), mainContainer.getScreen(screen3ID));
				slide_left_1.setByX(-800);
				slide_left_2.setByX(-800);
				slide_left_3.setByX(-800);
				
				ParallelTransition pt = new ParallelTransition(slide_left_1,slide_left_2,slide_left_3);
			       pt.play();
				
			}
		});
        
        root_main.setOnSwipeRight(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				TranslateTransition slide_left_1 = new TranslateTransition(Duration.seconds(2), root_guage);
				TranslateTransition slide_left_2 = new TranslateTransition(Duration.seconds(2), mainContainer.getScreen(screen2ID));
				TranslateTransition slide_left_3 = new TranslateTransition(Duration.seconds(2), mainContainer.getScreen(screen3ID));
				slide_left_1.setByX(800);
				slide_left_2.setByX(800);
				slide_left_3.setByX(800);
				
				ParallelTransition pt = new ParallelTransition(slide_left_1,slide_left_2,slide_left_3);
			       pt.play();
			}
		});
	}

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	System.out.println("[ScreensFramework Class] main() is Called");
    	
    	launch(args);
    }

    

}
