package org.Garage;

import org.Garage.CoCalc;
import org.Garage.Opener;
import org.Garage.Mysql;
import org.Garage.CoSensor;
import org.Garage.DoorSensor;
import java.sql.*;

public class Interval{
	private boolean continueLoop = true;								//escape loop on exit
	private int timeDelayMs = 5000;										//delay between readings
	private int numOfCalRead = 300000/timeDelayMs;

	private double[] coReadings = new double[numOfCalRead];				//array to store 5 min. of reading values
	private double currentValue;										//current value of readings

	private long currentTime;											//updated time per loop
	private long lastTime;												//the last time the loop ended

	private double ppmLevelTrigger;										//Sets limit for reading
	private long lastGarageOpening = 0;									//last garage reading
	private long garageDoorDelay = 30000;								//min delay between garage door calls
	
	public void timer() throws SQLException{
		CoCalc calc = new CoCalc();											//creates instance of calc class for meanVal and readingSelector
		Opener open = new Opener();
		Mysql mysql = new Mysql();
		DoorSensor door = new DoorSensor();
		CoSensor sensor = new CoSensor();									//initialize CoSensor for using readAnalog

		this.lastTime = System.currentTimeMillis();							//creates a refrence point

		while(this.continueLoop){											//loop for taking values at intervals
			this.currentTime = System.currentTimeMillis();					//take the time at the beginning of the loop
			
			if((this.currentTime - this.lastTime) >= this.timeDelayMs) {			//checks if the delay has passed
				this.lastTime = this.currentTime;									//sets current to last time
				this.currentValue = sensor.readAnalog();
				//mysql.setReadings(5);
				System.out.println(mysql.getTimestamps());

				if(numOfCalRead == 0) {												//Tests whether or not calibration is over
					try {
						mysql.reading(this.currentValue);
					} catch(Exception e) {
                        e.printStackTrace();
                	}

					System.out.println("Taking Values!!");
					System.out.println();
					if(this.currentValue > this.ppmLevelTrigger && !door.isOpen() && (this.currentTime - this.lastGarageOpening) >= this.garageDoorDelay){
						open.open(mysql);																//Opens Garage and logs to mysql
						this.lastGarageOpening = this.currentTime;										//Sets last time garrage opened to prevent closing before it finishes opening
					}
					
				} else {															
					if(this.numOfCalRead == 1){															//this if tests whether calibration is almost over
						this.ppmLevelTrigger = 2*(calc.meanVal(this.coReadings));						//sets ppm trigger based on calibration readings
						System.out.println();
						System.out.println("Level Trigger: " + this.ppmLevelTrigger);
						System.out.println();
					} else {
						this.coReadings[coReadings.length-this.numOfCalRead] = this.currentValue;		//adds value to caalibration array
					}
					this.numOfCalRead--;
				}

			}
			this.currentTime = System.currentTimeMillis();					//resets current time
		}
	}
	
}