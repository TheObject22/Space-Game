package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;
//ADD CONSTRUCTOR LIKE ENEMY CLASS
public class Boss extends GameObject implements EntityB{
//ADD BOSS INFORMATION HERE AFTER SCORE = 100
Random r = new Random();
	
	private int speed= r.nextInt(3)+1;;
	private Game game;
	private Controller c;
	
	private Textures tex;
	Animation anim;
	private int counter=0;
	
	
	public Boss(double x, double y,Textures tex, Controller c, Game game){
		super(x,y);
		this.tex=tex;
		this.c=c;
		this.game=game;
		
		anim=new Animation(5,tex.boss[0],tex.boss[1],tex.boss[2]);
	}
	

	
		
	
	public void tick() {
		// TODO Auto-generated method stub
		y+=speed;
		if(y>Game.HEIGHT*Game.SCALE){
			speed= r.nextInt(3)+1;
			y=-20;
			x=r.nextInt(Game.WIDTH*Game.SCALE-10);
		}
		
		for(int i=0;i<game.ea.size();i++){
			EntityA tempEnt=game.ea.get(i);
		
		if(Physics.Collision(this, tempEnt)){
			c.removeEntity(tempEnt);
			game.setBoss_killed(game.getBoss_killed()+1);
			Game.SCORE+=50;
			counter++;
			if(counter==10){
				c.removeEntity(tempEnt);
				c.removeEntity(this);
			}
		}
		
		}
		anim.runAnimation();
		}
		
		
	



	@Override
	public void render(Graphics g) {
		anim.drawAnimation(g,x,y,0);
		// TODO Auto-generated method stub
		
	}



	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,32,32);
	}



	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}



	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
}

