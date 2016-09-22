package com.schroedel.games.towerdefense.objects.entities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

import com.schroedel.games.towerdefense.graphics.GameView;
import com.schroedel.games.towerdefense.objects.movement.HomingTarget;

/**
 * Created by Christian Schrödel on 03.05.15.
 *
 * Projectile entity.
 */
public class Projectile extends Entity
{
	public static final int WIDTH = (int) (0.2*GameView.xDensityFactor);
	public static final int HEIGHT = (int) (0.2*GameView.yDensityFactor);

	private static final double PROJECTILE_SPEED = 200.0;

	private int damage;

	/**
	 * Creates new projectile entity.
	 *
	 * @param x - x position
	 * @param y - y position
	 * @param bmp - bitmap
	 * @param enemy - enemy entity homing on
	 * @param damage - damage of projectile
	 */
	public Projectile(
		int x,
		int y,
		Bitmap bmp,
		Enemy enemy,
		int damage,
		HomingTarget.OnTargetReachedListener listener)
	{
		super(x, y, WIDTH, HEIGHT, bmp);

		this.damage = damage;
		this.movement = new HomingTarget(enemy, listener);

		movement.setSpeed(PROJECTILE_SPEED);

		paint = new Paint();
		paint.setColor(Color.GREEN);
	}

	/**
	 * Returns damage of projectile.
	 *
	 * @return - damage
	 */
	public int getDamage()
	{
		return damage;
	}

	/**
	 * Destroys projectile.
	 */
	public void destroy()
	{
		movement.stop();
	}
}
