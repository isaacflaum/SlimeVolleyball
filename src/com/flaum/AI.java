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
 * @author Isaac Flaum
 *
 */
public class AI {

	private Slime aiSlime; //the slime who is AI
	private Ball gameBall; //the gameBall
	
	/**
	 * Get AI ready to compete
	 */
	public AI(Slime ai, Ball ball) {

		aiSlime = ai;
		gameBall = ball;

	}

	/**
	 * Move AI in response to ball
	 */
	public void moveAI() {
		System.out.println("stub");
	}

}