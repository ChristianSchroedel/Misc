package com.schroedel.games.towerdefense.objects.entities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;

import com.schroedel.games.towerdefense.graphics.GameView;
import com.schroedel.games.towerdefense.objects.movement.HomingTarget;

/**
 * Created by Christian Schrödel on 03.05.15.
 *
 * Tower entity.
 */
public class Tower extends Entity
{
	private static final int WIDTH = (int) (GameView.xDensityFactor);
	private static final int HEIGHT = (int) (GameView.yDensityFactor);

	// TODO: 11.10.15 Define tower values in assets file or somewhere else
	public static int value = 5;

	// TODO: 11.10.15 Define damage of towers in assets file or somewhere else
	private int damage = 1;

	private int range = 6;
	private int fireRate = 3000;
	private boolean readyToFire = true;

	private Bitmap projectileBmp;

	private Handler handler;

	private Runnable resetFire = new Runnable()
	{
		@Override
		public void run()
		{
			readyToFire(true);
		}
	};

	/**
	 * Creates new tower entity.
	 *
	 * @param x - x position
	 * @param y - y position
	 * @param bmp - bitmap
	 */
	public Tower(int x, int y, Bitmap bmp, Bitmap projectileBmp)
	{
		super(x, y, WIDTH, HEIGHT, bmp);

		this.projectileBmp = projectileBmp;

		paint = new Paint();
		paint.setColor(Color.BLUE);

		handler = new Handler();
	}

	@Override
	public void update(float dt)
	{

	}

	/**
	 * Shoots a projectile at an enemy.
	 *
	 * @param enemy - enemy to shoot at
	 * @return - projectile homing on enemy
	 */
	public Projectile shoot(
		Enemy enemy,
		HomingTarget.OnTargetReachedListener listener)
	{
		if (!readyToFire)
			return null;

		readyToFire(false);

		int xProj = rect.centerX() - Projectile.WIDTH/2;
		int yProj = rect.centerY() - Projectile.HEIGHT/2;

		handler.postDelayed(resetFire, fireRate);

		return new Projectile(
			xProj,
			yProj,
			projectileBmp,
			enemy,
			damage,
			listener);
	}

	/**
	 * Returns fire state of tower.
	 *
	 * @return - true if tower is ready to fire else false
	 */
	public boolean isReadyToFire()
	{
		return readyToFire;
	}

	/**
	 * Returns fire range of tower.
	 *
	 * @return - fire range
	 */
	public int getRange()
	{
		return range * GameView.xDensityFactor;
	}

	/**
	 * Sets fire state of tower.
	 *
	 * @param ready - true if tower is ready to fire else false
	 */
	private synchronized void readyToFire(boolean ready)
	{
		readyToFire = ready;
	}
}
