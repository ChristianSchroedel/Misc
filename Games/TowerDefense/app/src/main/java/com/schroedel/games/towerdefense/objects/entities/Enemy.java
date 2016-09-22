package com.schroedel.games.towerdefense.objects.entities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

import com.schroedel.games.towerdefense.graphics.GameView;
import com.schroedel.games.towerdefense.objects.movement.FollowWayPoints;
import com.schroedel.games.towerdefense.serializes.WayPoint;

import java.util.List;

/**
 * Created by Christian Schrödel on 03.05.15.
 *
 * Enemy entity.
 */
public class Enemy extends Entity
{
	private static final int WIDTH = (int) (GameView.xDensityFactor);
	private static final int HEIGHT = (int) (GameView.yDensityFactor);

	private static final double MOVE_SPEED = 100.0;

	// TODO: 11.10.15 Define value of enemies in assets of somewhere else
	public final static int value = 1;

	private int hitpoints;

	/**
	 * Creates new enemy entity.
	 *
	 * @param x - x position
	 * @param y - y position
	 * @param hitpoints - hit points of enemy
	 * @param bmp - bitmap
	 * @param wayPoints - way points
	 * @param listener - Listener receiving callback if enemy reached last way
	 *                   point.
	 */
	public Enemy(
		int x,
		int y,
		int hitpoints,
		Bitmap bmp,
		List<WayPoint> wayPoints,
		FollowWayPoints.OnEndReachedListener listener)
	{
		super(x, y, WIDTH, HEIGHT, bmp);

		this.movement = new FollowWayPoints(wayPoints, listener);
		this.hitpoints = hitpoints;

		this.paint = new Paint();

		movement.setSpeed(MOVE_SPEED);
		movement.start(this);

		paint.setColor(Color.RED);
	}

	/**
	 * Returns remaining hitpoints of enemy.
	 */
	public int getHitpoints()
	{
		return hitpoints;
	}

	/**
	 * Hits enemy with damage and reduces its hitpoints.
	 *
	 * @param damage - damage dealt to enemy
	 */
	public void hit(int damage)
	{
		hitpoints -= damage;
	}
}
