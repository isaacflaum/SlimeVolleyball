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

/**
 * A Slime is an instance of a player
 */
public class Slime {
	private int x, y;
	private int xv, yv;
	public boolean isP1;
	
	/**
	 * Initializes slime in starting place
	 * @param isP1 is the slime the first player?
	 */
	public Slime(boolean isP1) {
		if(isP1)
			x = 200; //first player starting pos
		else
			x = 800; //second player starting pos
		y = 0;
		xv = 0;
		yv = 0;
		this.isP1 = isP1;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getXV() {
		synchronized(this) {
			return xv;
		}
	}

	public int getYV() {
		return yv;
	}

	public void setX(int x) {

		//boundary checking
		if(isP1) {
			if(x < 50)
				x = 50;
			if(x > 445)
				x = 445;
		} else {
			if(x > 950)
				x = 950;
			if(x < 555)
				x = 555;
		}
		this.x = x;

	}

	public void setY(int y) {

			if(y < 0) {
				y = 0;
				yv = 0;
			}
		this.y = y;
	
	}

	public void setXV(int xv) {
		synchronized(this) {
			this.xv = xv;
		}
	}

	public void setYV(int yv) {
		this.yv = yv;
	}

}