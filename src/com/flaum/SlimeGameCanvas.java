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
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 * View for Slime Volleyball Game
 */
public class SlimeGameCanvas extends GameCanvas implements CommandListener{

	private SlimeVolleyBallMidlet midlet; //MIDlet to use methods
	protected Graphics graphics; //Graphics to do our drawing
	private Command cmExit, cmRestart; //Commands for CommandListener
	private SlimeGame game; //Game Model Object

	/**
	 * @param suppressKeyEvents should key events be suppressed?
	 * @param m MIDlet controller object
	 */
	public SlimeGameCanvas(boolean suppressKeyEvents, SlimeVolleyBallMidlet m) {
		
		//make mama happy
		super(suppressKeyEvents);

		//get graphics to do our drawing
		graphics = this.getGraphics();
		
		//setup command listener
		setCommandListener(this);
		cmExit = new Command("Exit", Command.EXIT, 1);
		cmRestart = new Command("Restart", Command.OK, 2);
	    	addCommand(cmExit);
	    	addCommand(cmRestart);
	    
	    	midlet = m; //retain reference to MIDlet
	    
	    	//init and start the game
		game = new SlimeGame(this);
		game.start();
	}
	
	/**
	 * Draws the volleyball court and net
	 */
	public void drawCourt() {
		graphics.setColor(0x0000FF);
        	graphics.fillRect(0, 0, getWidth(), (4 * (getHeight() / 2)) / 5);
        	graphics.setColor(0xC0C0C0);
        	graphics.fillRect(0, (4 * (getHeight() / 2)) / 5, getWidth(), (getHeight() / 2) / 5);
        	graphics.setColor(0xFFFFFF);
        	graphics.fillRect(getWidth() / 2 - 2, (7 * (getHeight() / 2)) / 10, 4, (getHeight() / 2) / 10 + 5);
	}
	
	/**
	 * Draws a slime
	 * @param x x value of slime
	 * @param y y value of slime
	 * @param isP1 is this player player 1?
	 * @param isOld is this painting over an old paint? 
	 */
	public void drawSlime(int x, int y, boolean isP1, boolean isOld) {
		int k1 = getWidth() / 10;
        	int j2 = (getHeight() / 2) / 10;
		int i = (x * getWidth()) / 1000 - k1 / 2;
        	int l = (7 * (getHeight() / 2)) / 10 - (y * (getHeight() / 2)) / 1000;
		
		//figure out what to paint
		if(isOld) {
			
	        graphics.setColor(0x0000FF);
	        graphics.fillRect(i, l, k1, j2);
	        return;

		} else if(isP1) {

			graphics.setColor(0xFF0000);

		} else {

			graphics.setColor(0x00FF00);

		}
		
		//draw the Slime
		graphics.fillArc(i, l, k1, 2 * j2, 0, 180);
	}
	
	/**
	 * Draws a ball
	 * @param x x value of ball
	 * @param y y value of ball
	 * @param isOld is this painting over an old paint? 
	 */
	public void drawBall(int x, int y, boolean isOld) {
		int k = (30 * (getHeight() / 2)) / 1000;
        int i = (x * getWidth()) / 1000;
        int j = (4 * (getHeight() / 2)) / 5 - (y * (getHeight() / 2)) / 1000;
        if(isOld) {
        	graphics.setColor(0x0000FF);
        	graphics.fillRect(i - k, j - k, k * 2, k * 2);
        } else {
        	graphics.setColor(0xFFFF00);
        	graphics.fillArc(i - k, j - k, k * 2, k * 2, 0, 360);
        }
	}

	/**
	 * Draws the score
	 * @param s String to display
	 * @param isP1 Is this player player 1?
	 */
	public void drawScore(String s, boolean isP1) {
		int xStart = isP1 ? 0 : getWidth() / 2;
		int yStart = getHeight() / 2;
		graphics.setColor(0xBDA27E);
		graphics.fillRect(xStart, yStart, getWidth() / 2, yStart);
		graphics.setColor(0x000000);
		graphics.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE)); //change to large
    	graphics.drawString(s, xStart + getWidth() / 4, yStart + getHeight() / 5, Graphics.HCENTER | Graphics.BASELINE); //hehehe type casting
	}


	/**
	 * called when command is pressed
	 */
	public void commandAction(Command c, Displayable d)
	  {

		//dummy command to warm up event thread
	    if (c == cmExit) {

	    	midlet.notifyDestroyed();
	    
	    } else if (c == cmRestart) {
	    
	    	midlet.startGame();
	    
	    }
	  
	  }
	
	/**
	 * 	Called when user presses a key
	 *	@param keyCode an integer representing the button pressed
	 */		  
	protected void keyPressed(int keyCode) {
	    String pressed = getKeyName(keyCode);
	    if(pressed.equals("UP")) {
	    
	    	if(game.getP1YV() == 0)
	    		game.setP1YV(31);
	    
	    }

	    if(pressed.equals("LEFT"))
	    	game.setP1XV(-8);
	    if(pressed.equals("RIGHT"))
	    	game.setP1XV(8);
	    
	}
	/**
	* 	Called when user releases a key
	*	@param keyCode an integer representing the button released
	*/		 
	protected void keyReleased(int keyCode) {
		
		//only because I don't wanna switch over a String
		String released = getKeyName(keyCode);
		
		if(released.equals("LEFT")) {
	    
	    	if(game.getP1XV() < 0)
	    		game.setP1XV(0);
		
		}
	    
	    if(released.equals("RIGHT")) {

	    	if(game.getP1XV() > 0)
	    		game.setP1XV(0);
	    
	    }
	  
	  }
}
