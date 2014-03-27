	/**
	 * @author Isaac Flaum
	 * This program is a MIDlet designed to run on older Java Phones
	 * Copyright (C) 2014  Isaac Flaum
	 *	
	 * This program is free software; you can redistribute it and/or
	 * modify it under the terms of the GNU General Public License
	 * as published by the Free Software Foundation; either version 2
	 * of the License, or (at your option) any later version.
	 *
	 * This program is distributed in the hope that it will be useful,
	 * but WITHOUT ANY WARRANTY; without even the implied warranty of
	 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	 * GNU General Public License for more details.
	 */

package com.flaum;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * Controller for Slime Volleyball
 */
public class SlimeVolleyBallMidlet extends MIDlet {
	private Display display; //display object
	private Canvas canvas; //holds current screen to display
	
	/**
	 * Initialize display
	 */
	public SlimeVolleyBallMidlet() {
		display = Display.getDisplay(this);
	}

	/* (non-Javadoc)
	 * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	 */
	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.microedition.midlet.MIDlet#pauseApp()
	 */
	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	/**
	 * Set display to new instance of Start View
	 */
	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		canvas = new StartCanvas(this);
		display.setCurrent(canvas);
	}
	
	/**
	 * Sets the display to a new instance of the Game View
	 */
	public void startGame() {
		canvas = new SlimeGameCanvas(false, this);
		display.setCurrent(canvas);
	}

}
