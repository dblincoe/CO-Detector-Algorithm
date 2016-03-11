package org.Garage;

import org.bulldog.core.util.BulldogUtil;
import org.Garage.CoSensor;

public class CoCalc{

	public double meanVal(double[] coReadings) {
		double sum = 0.0;								//sum of readings to be divided
		int length = 0;

		for(int i=0; i<coReadings.length; i++) {		//loops reading array
			if(coReadings[i]!= 0.0) {
				sum += coReadings[i];						//adds to sum
				length++;
			}
		}

		return (sum/length);					//computes mean
	}
	
}