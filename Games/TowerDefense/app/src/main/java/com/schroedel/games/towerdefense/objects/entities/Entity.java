package com.schroedel.games.towerdefense.objects.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.schroedel.games.towerdefense.objects.movement.Movement;

/**
 * Created by Christian Schrödel on 03.05.15.
 *
 * Class representing game entity object.
 */
public abstract class Entity
{
	public Rect rect;

	protected Bitmap bmp;
	protected Paint paint;

	protected Movement movement;

	/**
	 * Creates new game entity at given position.
	 *
	 * @param x - x position
	 * @param y - y position
	 * @param w - width
	 * @param h - height
	 * @param bmp - bitmap
	 */
	public Entity(int x, int y, int w, int h, Bitmap bmp)
	{
		this.rect = new Rect(x, y, x+w, y+h);
		this.bmp = bmp;
	}

	/**
	 * Renders entity using given canvas.
	 *
	 * @param canvas - canvas to draw on
	 */
	public void render(Canvas canvas)
	{
		canvas.drawRect(rect, paint);
	}

	/**
	 * Returns distance to other entity.
	 *
	 * @param other - other entity
	 * @return - distance
	 */
	public int getDistance(Entity other)
	{
		int dx = rect.centerX() - other.rect.centerX();
		int dy = rect.centerY() - other.rect.centerY();

		return (int) Math.sqrt(dx*dx + dy*dy);
	}

	/**
	 * Updates entity object.
	 *
	 * @param dt - elapsed time
	 */
	public void update(float dt)
	{
		if (movement != null)
			movement.move(this, dt);
	}
}
