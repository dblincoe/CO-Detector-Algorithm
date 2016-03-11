package org.Garage;

import org.bulldog.beagleboneblack.BBBNames;
import org.bulldog.core.Signal;
import org.bulldog.core.gpio.DigitalOutput;
import org.bulldog.core.platform.Board;
import org.bulldog.core.platform.Platform;
import org.bulldog.core.util.BulldogUtil;
import org.Garage.Mysql;

public class Opener{

	public void open(Mysql mysql){
		Board board = Platform.createBoard();
                try{
                        mysql.garageOpen();
                } catch(Exception e){
                        //Handle errors for Class.forName
                        e.printStackTrace();
                }
                //Set up a digital output
                DigitalOutput output = board.getPin(BBBNames.P9_42).as(DigitalOutput.class);

                //Set a high signal on the output
                output.write(Signal.High);

                //Toggle it
                BulldogUtil.sleepMs(5000);     
                output.toggle();
	}
}