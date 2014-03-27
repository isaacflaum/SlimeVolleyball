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
 * Model for Slime Volleyball Game
 */
public class SlimeGame extends Thread {

	private boolean started; //has the game started?
	private SlimeGameCanvas gameCanvas; //need methods from View
	private int p1Score, p2Score; //hold players scores
	private Slime p1, p2; //first and second player
	private Ball ball; //game ball
	private int p1OldX , p1OldY, p2OldX, p2OldY, 
		ballOldX, ballOldY; //old variables to paint over
	
	/**
	   * Constructor initialize game objects
	   * @param canvas is the view associated with the game (model)
	   */
	public SlimeGame(SlimeGameCanvas canvas) {
		started = false;
		gameCanvas = canvas;
		p1 = new Slime(true);
		p2 = new Slime(false);
		ball = new Ball();
		p1Score = 0;
		p2Score = 0;
	}
	
	//setters and getters
	public void setP1XV(int xv) { p1.setXV(xv); }
	public void setP1YV(int yv) { p1.setYV(yv); }
	public int getP1XV() { return p1.getXV(); }
	public int getP1YV() { return p1.getYV(); }

	/**
	  * Game loop
	  */
	public void run() {
		
		if(!started) {
			//draw the court
			gameCanvas.drawCourt();
			//draw the scores
			gameCanvas.drawScore(0 + "", true);
			gameCanvas.drawScore(0 + "", false);
			//now the loop is ready to run
			started = true;
		}
		
		//started is false when someone gets to 7
		while(started) {

			//grab old values
			p1OldX = p1.getX();
			p1OldY = p1.getY();
			p2OldX = p2.getX();
			p2OldY = p2.getY();
			ballOldX = ball.getX();
			ballOldY = ball.getY();
			
			//move player 1
			p1.setX(p1.getX() + p1.getXV());
			if(p1.getYV() != 0) {
				p1.setYV(p1.getYV() - 2); //gravity
				p1.setY(p1.getY() + p1.getYV());
			}








			/**
	   		* @todo implement AI
	   		* ai.makeNextMove(); something like that
	   		*/
			





			//move ball
			ball.setYV(ball.getYV() - 1);
			ball.setY(ball.getY() + ball.getYV());
			ball.setX(ball.getX() + ball.getXV());
			
			//collision detection p1 - lets do some math!
			int xDistance = (ball.getX() - p1.getX()) * 2; //distances
            int yDistance = ball.getY() - p1.getY(); //distances
            int pythag = xDistance * xDistance + yDistance * yDistance; //pythagoreas's theorem
            int xVelocityRelative = ball.getXV() - p1.getXV(); //relative velocities
            int yVelocityRelative = ball.getYV() - p1.getYV(); //relative velocities
            if(yDistance > 0 && pythag < 15625 && pythag > 25)
            {
                int l = (int)Math.sqrt(pythag); //uh oh more pythag
                int j1 = (xDistance * xVelocityRelative + yDistance * yVelocityRelative) / l;
                ball.setX(p1.getX() + (xDistance * 63) / l);
                ball.setY(p1.getY() + (yDistance * 125) / l);
                
                //j1 <= 0 when ball is touching slime
                if(j1 <= 0)
                {
                    ball.setXV(ball.getXV() + (p1.getXV() - (2 * xDistance * j1) / l));
                    ball.setYV(ball.getYV() + (p1.getYV() - (2 * yDistance * j1) / l));
                }
            }
            
            //now collision for p2
            xDistance = (ball.getX() - p2.getX()) * 2; //distances
            yDistance = ball.getY() - p2.getY(); //distances
            pythag = xDistance * xDistance + yDistance * yDistance; //pythagoreas's theorem
            xVelocityRelative = ball.getXV() - p2.getXV(); //relative velocities
            yVelocityRelative = ball.getYV() - p2.getYV(); //relative velocities
            if(yDistance > 0 && pythag < 15625 && pythag > 25)
            {
                int l = (int)Math.sqrt(pythag); //uh oh more pythag
                int j1 = (xDistance * xVelocityRelative + yDistance * yVelocityRelative) / l;
                ball.setX(p2.getX() + (xDistance * 63) / l);
                ball.setY(p2.getY() + (yDistance * 125) / l);
                
                //j1 <= 0 when ball is touching slime
                if(j1 <= 0)
                {
                    ball.setXV(ball.getXV() + (p2.getXV() - (2 * xDistance * j1) / l));
                    ball.setYV(ball.getYV() + (p2.getYV() - (2 * yDistance * j1) / l));
                }
            }

            // now collision detection for the net
            if(ball.getX() > 480 && ball.getX() < 520 && ball.getY() < 140) {
            	
				if(ball.getYV() < 0 && ball.getY() > 130) {
					ball.setYV(ball.getYV() * -1);
					ball.setY(130);
				} else if(ball.getX() < 500) {
					ball.setX(480);
					ball.setXV(ball.getXV() >= 0 ? ball.getXV() * -1 : ball.getXV());
				} else {
					ball.setX(520);
					ball.setXV(ball.getXV() >= 0 ? ball.getXV() * -1 : ball.getXV());
				}
				
			}
			
			//draw old slimes
			gameCanvas.drawSlime(p1OldX, p1OldY, true, true);
			gameCanvas.drawSlime(p2OldX, p2OldY, false, true);
			
			//draw old ball
			gameCanvas.drawBall(ballOldX, ballOldY, true);
			
			
			//draw new slimes
			gameCanvas.drawSlime(p1.getX(), p1.getY(), true, false);
			gameCanvas.drawSlime(p2.getX(), p2.getY(), false, false);
			
			//draw new ball
			gameCanvas.drawBall(ball.getX(), ball.getY(), false);
			
			//ball.getY() < 35 when ball is touching the ground
			if(ball.getY() < 35) {
				//ball.getX() < 500 when p1 screwed up
				if(ball.getX() < 500) {
					
					//redraw court and scoreboard
					gameCanvas.drawCourt();
					gameCanvas.drawScore(++p2Score + "", false);
					
					//start slimes and ball at defaults
					p1.setX(200);
					p1.setY(0);
					p1.setXV(0);
					p1.setYV(0);
					p2.setX(800);
					p2.setY(0);
					p2.setXV(0);
					p2.setYV(0);
					//p2 gets to serve
					ball.setX(800);
					ball.setY(400);
					ball.setXV(0);
					ball.setYV(0);
					
				} else { // else p2 screwed up
					
					//redraw court and scoreboard
					gameCanvas.drawCourt();
					gameCanvas.drawScore(++p1Score + "", true);
					
					//start them at defaults
					p1.setX(200);
					p1.setY(0);
					p1.setXV(0);
					p1.setYV(0);
					p2.setX(800);
					p2.setY(0);
					p2.setXV(0);
					p2.setYV(0);
					//p1 gets to serve
					ball.setX(200);
					ball.setY(400);
					ball.setXV(0);
					ball.setYV(0);
					
				}
				
				//did someone win?
				if(p1Score == 7) {
					gameCanvas.drawScore("P1 WINS!", true);
					started = false;
				}
				if(p2Score == 7) {
					gameCanvas.drawScore("P2 WINS!", false);
					started = false;
				}
				
			}
			
			//sent repaint signal to paint thread
			gameCanvas.repaint();
			
			try {
				
				//50ms sleep time = ~20 frames per second
				SlimeGame.sleep(50L);
				
			} catch (InterruptedException e) {
				
				//this should never happen
				e.printStackTrace();
				started = false;
			
			}
			
		}

	}

}
