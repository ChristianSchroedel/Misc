package com.schroedel.games.towerdefense.objects.movement;

import android.graphics.Rect;

import com.schroedel.games.towerdefense.objects.entities.Entity;

/**
 * Created by Christian Schrödel on 25.10.15.
 *
 * Class implementing movement pattern of homing an entity until it gets
 * reached.
 */
public class HomingTarget implements Movement
{
	private double moveSpeed;

	private Entity target;
	private OnTargetReachedListener listener;

	public interface OnTargetReachedListener
	{
		void onTargetReached(Entity entity, Entity target);
	}

	/**
	 * Creates movement pattern of homing an entity until it gets reached.
	 *
	 * @param target - homing target
	 * @param listener - callback receiver if homing entity was reached
	 */
	public HomingTarget(Entity target, OnTargetReachedListener listener)
	{
		this.target = target;
		this.listener = listener;
	}

	@Override
	public void start(Entity entity)
	{

	}

	@Override
	public void move(Entity entity, float dt)
	{
		if (target == null)
			return;

		Rect rect = entity.rect;

		int dx = target.rect.centerX() - rect.centerX();
		int dy = target.rect.centerY() - rect.centerY();

		double alpha = Math.atan(Math.abs(dx*1.0)/Math.abs(dy*1.0));

		double vy = Math.cos(alpha) * moveSpeed;
		double vx = Math.sin(alpha) * moveSpeed;

		if (dx < 0)
			vx *= -1;

		if (dy < 0)
			vy *= -1;

		int x = rect.left + (int) (vx * dt);
		int y = rect.top + (int) (vy * dt);

		rect.set(x, y, x + rect.width(), y + rect.height());

		if (isTargetReached(entity) &&
			listener != null)
			listener.onTargetReached(entity, target);
	}

	@Override
	public void setSpeed(double speed)
	{
		this.moveSpeed = speed;
	}

	@Override
	public void stop()
	{
		this.target = null;
	}

	/**
	 * Checks if target has been reached by entity.
	 *
	 * @param entity - entity
	 */
	private boolean isTargetReached(Entity entity)
	{
		return entity.rect.intersect(target.rect);
	}
}
