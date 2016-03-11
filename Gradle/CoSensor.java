package org.Garage;

import org.bulldog.core.platform.Platform;
import org.bulldog.beagleboneblack.BBBNames;
import org.bulldog.core.gpio.AnalogInput;
import org.bulldog.core.gpio.DigitalInput;
import org.bulldog.core.platform.Board;

public class CoSensor{
	private Board board;
	private AnalogInput analogInput;
	private DigitalInput digitalInput;

	public CoSensor() {
		board = Platform.createBoard();
		assignPins();
	}

	private void assignPins() {
		analogInput = board.getPin(BBBNames.AIN4).as(AnalogInput.class);
		digitalInput = board.getPin(BBBNames.P9_41).as(DigitalInput.class);
	}
	
	public int readDigital(){
		return digitalInput.read().getNumericValue();
	}

	public double readAnalog() {
		return analogInput.read()*5;
	}

}