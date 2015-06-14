package com.enzo.packagee;
/*
 * Copyright (c) 2013 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * 
 * */




import java.util.Random;

import screensframework.Serial_Example;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DemoGauge {
    private static final Random RND       = new Random();
    private static int          noOfNodes = 0;
    //Gauge class is used to create a gauge GUI but it is intialized with GauageBuilder class 
    /*private Gauge               control;
 	private Gauge               control1;
 	private Gauge               control2;*/
	
    public static Gauge               control;
    public static Gauge               control1;
    public static Gauge               control2;
 	
 	private long                lastTimerCall;
    private AnimationTimer      timer;
    private Marker              marker0;


    public DemoGauge() {
		System.out.println("[DemoGauge Class] Constructor is Called");
		
		//Logger log = Logger.getLogger("Gauge");
    	//or below will work too
    	// Logger log = Logger.getLogger(Gauge.class.getName());
    	
    	//log.info("Constructor() is Called");	
    	
		//because i want the guage to be create automatically
    	init();
		
    	/*don;t have to do this here 
    	 * //this will allow me to chnage the values of the guages
    	Serial_Example serial_obj = new Serial_Example();
    	serial_obj.open_Serial_Port();
    	serial_obj.add_Serial_Listener(control ,control1, control2);*/
    	
	}
    
    public void init() {
    	System.out.println("[DemoGauge Class] init() is Called");
    	
        control = GaugeBuilder.create()
                              .prefSize(300, 300)
                              //.startAngle(330)
                              .startAngle(330)			//from what angle the starting blue point will start from 
                              //.angleRange(100)
                                  .minValue(0)
                                   .angleRange(300)			//the angle of the end of the other blue point but first blue point being the angle zero
                     
                              //.minValue(60)			//it threashold acts wierd when i uncoment the line below
                              //.maxValue(100)
                              .maxValue(100)
                              
                              /*.sections(new Section(0, 60),
                                        new Section(60, 80),
                                        new Section(80, 100))
                              */.sections(new Section(0, 60),
                            		    new Section(60, 80),
                                        new Section(80, 100))
                              
                              .majorTickSpace(20)
                              //.majorTickSpace(10)		//the values show on the guage like 10 will only show the 10, 20, 30 etc
                              .plainValue(false)		//this makes the temp vlaue black or gray 
                              .tickLabelOrientation(Gauge.TickLabelOrientation.HORIZONTAL)   	//it is for the orintation of the gauge measuring values on it 
                              .threshold(70)
                              .thresholdVisible(true)
                              .minMeasuredValueVisible(true)
                              .maxMeasuredValueVisible(true)
                              .title("ROOM1")
                              //.unit("F")
                              .build();				//this method is quite big 
        
        control1 = GaugeBuilder.create()
                .prefSize(300, 300)
                //.startAngle(330)
                .startAngle(330)			//from what angle the starting blue point will start from 
                //.angleRange(100)
                .angleRange(300)			//the angle of the end of the other blue point but first blue point being the angle zero
                .minValue(0)
                
                //.minValue(60)			//it threashold acts wierd when i uncoment the line below
                //.maxValue(100)
                .maxValue(100)
                
                /*.sections(new Section(0, 60),
                          new Section(60, 80),
                          new Section(80, 100))
                */.sections(new Section(0, 60),
              		    new Section(60, 80),
                          new Section(80, 100))
                
                .majorTickSpace(20)
                //.majorTickSpace(10)		//the values show on the guage like 10 will only show the 10, 20, 30 etc
                .plainValue(false)		//this makes the temp vlaue black or gray 
                .tickLabelOrientation(Gauge.TickLabelOrientation.HORIZONTAL)   	//it is for the orintation of the gauge measuring values on it 
                .threshold(70)
                .thresholdVisible(true)
                .minMeasuredValueVisible(true)
                .maxMeasuredValueVisible(true)
                .title("ROOM2")
                //.unit("F")
                .build();

        
        control2 = GaugeBuilder.create()
                .prefSize(300, 300)
                //.startAngle(330)
                .startAngle(330)			//from what angle the starting blue point will start from 
                //.angleRange(100)
                .angleRange(300)			//the angle of the end of the other blue point but first blue point being the angle zero
                .minValue(0)
                
                //.minValue(60)			//it threashold acts wierd when i uncoment the line below
                //.maxValue(100)
                .maxValue(100)
                
                /*.sections(new Section(0, 60),
                          new Section(60, 80),
                          new Section(80, 100))
                */.sections(new Section(0, 60),
              		    new Section(60, 80),
                          new Section(80, 100))
                
                .majorTickSpace(20)
                //.majorTickSpace(10)		//the values show on the guage like 10 will only show the 10, 20, 30 etc
                .plainValue(false)		//this makes the temp vlaue black or gray 
                .tickLabelOrientation(Gauge.TickLabelOrientation.HORIZONTAL)   	//it is for the orintation of the gauge measuring values on it 
                .threshold(70)
                .thresholdVisible(true)
                .minMeasuredValueVisible(true)
                .maxMeasuredValueVisible(true)
                .title("ROOM3")
                //.unit("F")
                .build();
        
        
        //control.setStyle("-tick-label-fill: blue;");		//this sets the radical value on the gauge blue
        control.setMinorTickSpace(20);						//change the spacing of the radical values on the guage
        control1.setMinorTickSpace(20);
        control2.setMinorTickSpace(20);
        //control.setHistogramEnabled(true);					//create like round circules on the guage 

        //control.setOnThresholdExceeded(observable -> System.out.println("Threshold exceeded") );
        //control.setOnThresholdUnderrun(observable -> System.out.println("Threshold underrun"));

        //marker0 = new Marker(25);
        //marker0.setOnMarkerExceeded(observable -> System.out.println("Marker exceeded"));
        //marker0.setOnMarkerUnderrun(observable -> System.out.println("Marker underrun"));
        //control.addMarker(marker0);


        lastTimerCall = System.nanoTime() + 500_000_000l;
        
        System.out.println("[DemoGuage Class] AnimationTimer Obj intialized");
        timer = new AnimationTimer() {
        	
            @Override public void handle(long now) {
                if (now > lastTimerCall + 2_000_000_000l) {
                	
                	//the below line are VERY CRUCIAL because they will let me get the gauage temp value and set the gauge temp value
                	//	so i can get the value from arduino i will use the setvalue() and get the value to send it back to arduino i 
                	//	will use getValue() BUT NOTE this i think will not be the threshold value
                    control.setValue(RND.nextDouble() * 100);
                    control1.setValue(RND.nextDouble() * 100);
                    control2.setValue(RND.nextDouble() * 100);
                    
                    System.out.println("[DemoGuage Class] Threshold Value:	"+ control.getThreshold());
                    
                    //System.out.println("Value:   "+control.getValue());
                    
                    lastTimerCall = now;
                }
            }
        };
    }

     public void start() {
    	timer.start();

        System.out.println("[DemoGauge Class] calcNoOfNodes() is about to get Called");
        //calcNoOfNodes(scene.getRoot());
        System.out.println("		"+ noOfNodes + "Nodes in SceneGraph");
        
     }

    public void stop() {
    	System.out.println("[DemoGauge Class] stop() is Called");
    }

  
    private static void calcNoOfNodes(Node node) {
    	
    	//System.out.println("[DemoGauge Class   ] calcNoOfNodes() is Called");
        
    	if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) {
                    calcNoOfNodes(n);
                    System.out.println("[DemoGuage Class] getStyleClass()"+n.getStyleClass().toString());
                }
            }
        }
    }


    //don't need this any more since the gauges object are changed to static, i can access them directly
    //on second though i found out that they are use in teh ScreeFramework calss so leave them uncommented
    public static Gauge getControl() {
		return control;
	}

    public static Gauge getControl1() {
		return control1;
	}

    public static Gauge getControl2() {
		return control2;
	}


}


/*WHATEVER NOTES
 * 	Gauge class from the Enzo lib is intialized and declared in this class
 * 		GaugeSkin class from the Enzo lib is intialized and declared in this class
 * 	GaugeBuilder class from the Enzo lib is intialized and declared in this class
 * 	For some wiered ass reason the print statement or log statemetn don't work in the other classes Gauge, GaugeSkin or GaugeBuilder
 * 		Its not working because the enzo jar file for this project only contains the .class file not the other onces 
 * Gauge.setvalue can be used to set the vlaue of the temp on the guage 
 * 		
 * */
