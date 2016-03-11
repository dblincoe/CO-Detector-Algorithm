package org.Garage;

import org.Garage.*;
import org.bulldog.core.util.BulldogUtil;
import java.sql.*;

public class GarageDoor {
	public static void main(String[] args) throws SQLException{
		Mysql mysql = new Mysql();
		Opener open = new Opener();
		open.open(mysql);
		Interval timer = new Interval();
		timer.timer();
	}	
}