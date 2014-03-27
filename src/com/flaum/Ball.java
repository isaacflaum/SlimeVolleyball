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
 * Ball
 */
public class Ball {
	private int x, y; //x and y coordinates of the ball
	private int xv, yv; //x and y velocities

	/**
	 * Default Constructor initializes ball above first player
	 */
	public Ball() {
		x = 200; //above first player
		y = 400; //in the air
		xv = 0;
		yv = 0;
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
		//check if out of bounds
		if(x < 15) {
			x = 15;
			xv = -xv;
		}
		if(x > 985) {
			x = 985;
			xv = -xv;
		}
		synchronized(this) {
			this.x = x;
		}
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setXV(int xv) {
		
		//max out x velocity at 15
		if(xv < -15)
			xv = -15;
		if(xv > 15)
			xv = 15;
		synchronized(this) {
			this.xv = xv;
		}
	} 

	public void setYV(int yv) {
		
		//max out y velocity at 22
		if(yv < -22)
			yv = -22;
		if(yv > 22)
			yv = 22;
		this.yv = yv;
	}

}