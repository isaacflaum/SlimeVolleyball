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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 * View for Main Menu
 */
public class StartCanvas extends GameCanvas implements CommandListener{
	private SlimeVolleyBallMidlet midlet;
	private Command cmStart;
	public StartCanvas(SlimeVolleyBallMidlet m) {
		
		//make the parent class happy
		super(false);
		
		//setup and add the start game command
		setCommandListener(this);
		cmStart = new Command("Start", Command.OK, 1); //Command.OK is right softkey
	    addCommand(cmStart);
	    
	    //so we can use SlimeVolleyBallMidlet's methods
	    midlet = m;
	    
	}
	
	/**
	 * Draws the Start Screen
	 */
	public void paint(Graphics g) {
		
		//blue
		g.setColor(0x0000FF);
		g.fillRect(0, 0, getWidth(), getHeight());
		//white
		g.setColor(0xFFFFFF);
		g.drawString("Slime VolleyBall", getWidth() / 4, getHeight() / 2, Graphics.TOP | Graphics.LEFT);
		
	}
	
	/**
	 * Called whenever button is pressed
	 */
	public void commandAction(Command c, Displayable d) {
		
		//c == cmStart when user presses right soft key
		if (c == cmStart)
	    	midlet.startGame();
		
	}
	
}
