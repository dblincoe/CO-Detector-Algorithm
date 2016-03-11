package org.Garage;

import org.bulldog.core.platform.Platform;
import org.bulldog.beagleboneblack.BBBNames;
import org.bulldog.core.gpio.AnalogInput;
import org.bulldog.core.platform.Board;

public class DoorSensor{
	private Board board;
	private AnalogInput analogInput;
	
	public DoorSensor() {
		board = Platform.createBoard();
		assignPins();
	}

	private void assignPins() {
		analogInput = board.getPin(BBBNames.AIN5).as(AnalogInput.class);
	}

	public boolean isOpen() {
		if(this.readAnalog() > .1){
			return true;
		} else {
			return false;
		}
	}

	public double readAnalog() {
		return analogInput.read();
	}

}