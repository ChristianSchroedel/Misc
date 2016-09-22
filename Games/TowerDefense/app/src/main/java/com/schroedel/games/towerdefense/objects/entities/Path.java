package com.schroedel.games.towerdefense.objects.entities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Christian Schrödel on 27.09.15.
 *
 * Path entity showing way of enemies.
 */
public class Path extends Entity
{
	/**
	 * Creates new path at given position.
	 *
	 * @param x - x position
	 * @param y - y position
	 * @param w - width
	 * @param h - height
	 * @param bmp - bitmap
	 */
	public Path(int x, int y, int w, int h, Bitmap bmp)
	{
		super(x, y, w, h, bmp);

		paint = new Paint();
		paint.setColor(Color.LTGRAY);
	}
}
