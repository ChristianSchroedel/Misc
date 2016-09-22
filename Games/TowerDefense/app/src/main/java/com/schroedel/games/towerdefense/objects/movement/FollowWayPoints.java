package com.schroedel.games.towerdefense.objects.movement;

import android.graphics.Rect;

import com.schroedel.games.towerdefense.graphics.GameView;
import com.schroedel.games.towerdefense.objects.entities.Entity;
import com.schroedel.games.towerdefense.serializes.WayPoint;

import java.util.List;

/**
 * Created by Christian Schrödel on 25.10.15.
 *
 * Class implementing movement pattern of following fixed way points.
 */
public class FollowWayPoints implements Movement
{
	enum Direction
	{
		LEFT,
		UP,
		RIGHT,
		DOWN
	}

	private double moveSpeed;

	private double vx;
	private double vy;

	private Direction direction;

	private List<WayPoint> wayPoints;
	private int headedWayPoint;

	private OnEndReachedListener listener;

	public interface OnEndReachedListener
	{
		void onEndReached(Entity entity);
	}

	/**
	 * Creates movement pattern of following fixed way points.
	 *
	 * @param wayPoints - way points to follow
	 * @param listener - callback listener when last way point was reached.
	 */
	public FollowWayPoints(
		List<WayPoint> wayPoints,
		OnEndReachedListener listener)
	{
		this.wayPoints = wayPoints;
		this.headedWayPoint = 0;
		this.listener = listener;
	}

	@Override
	public void start(Entity entity)
	{
		// Move to first way point
		moveToWayPoint(entity, 0);
	}

	@Override
	public void move(Entity entity, float dt)
	{
		if (dt < 0)
			return;

		Rect rect = entity.rect;

		int x = rect.left + (int) (vx * dt);
		int y = rect.top + (int) (vy * dt);

		rect.set(x, y, x + rect.width(), y + rect.height());

		if (headedWayPoint >= wayPoints.size())
			return;

		if (hasReachedWayPoint(entity))
			moveToNextWayPoint(entity);
	}

	@Override
	public void setSpeed(double speed)
	{
		this.moveSpeed = speed;
	}

	@Override
	public void stop()
	{
		this.vx = 0;
		this.vy = 0;
		this.moveSpeed = 0;
	}

	/**
	 * Checks if enemy has reached way point it is heading right now.
	 */
	private boolean hasReachedWayPoint(Entity entity)
	{
		Rect rect = entity.rect;

		int centerX = rect.centerX();
		int centerY = rect.centerY();

		int wayPointCenterX = getWayPointCenterX(headedWayPoint);
		int wayPointCenterY = getWayPointCenterY(headedWayPoint);

		if (direction == Direction.LEFT)
		{
			if (centerX <= wayPointCenterX)
				return true;
		}
		else if (direction == Direction.UP)
		{
			if (centerY <= wayPointCenterY)
				return true;
		}
		else if (direction == Direction.DOWN)
		{
			if (centerY >= wayPointCenterY)
				return true;
		}
		else if (direction == Direction.RIGHT)
		{
			if (centerX >= wayPointCenterX)
				return true;
		}

		return false;
	}

	/**
	 * Moves enemy to next way point.
	 */
	private void moveToNextWayPoint(Entity entity)
	{
		int nextWayPoint = headedWayPoint+1;

		// Make sure enemy is at expected position (exactly at way point)
		entity.rect.set(getWayPointRect(headedWayPoint));

		if (nextWayPoint >= wayPoints.size())
		{
			listener.onEndReached(entity);
			return;
		}

		moveToWayPoint(entity, nextWayPoint);
	}

	/**
	 * Moves enemy in direction of given way point.
	 *
	 * @param wayPoint - way point to move to
	 */
	private void moveToWayPoint(Entity entity, int wayPoint)
	{
		Rect rect = entity.rect;
		headedWayPoint = wayPoint;

		int wayPointX = getWayPointCenterX(wayPoint);
		int wayPointY = getWayPointCenterY(wayPoint);

		int dx = rect.centerX() - wayPointX;
		int dy = rect.centerY() - wayPointY;

		if (Math.abs(dx) > Math.abs(dy))
		{
			if (dx > 0)
				moveLeft();
			else
				moveRight();
		}
		else
		{
			if (dy < 0)
				moveDown();
			else
				moveUp();
		}
	}

	/**
	 * Returns x position of way point.
	 *
	 * @param index - index of way point
	 * @return - x position
	 */
	private int getWayPointCenterX(int index)
	{
		WayPoint wayPoint = wayPoints.get(index);

		return wayPoint.x* GameView.xDensityFactor + GameView.xDensityFactor/2;
	}

	/**
	 * Returns y position of way point.
	 *
	 * @param index - index of way point
	 * @return - y position
	 */
	private int getWayPointCenterY(int index)
	{
		WayPoint wayPoint = wayPoints.get(index);

		return wayPoint.y*GameView.yDensityFactor +	GameView.yDensityFactor/2;
	}

	/**
	 * Returns rectangle of way point.
	 *
	 * @param index - index of way point
	 * @return - rectangle
	 */
	private Rect getWayPointRect(int index)
	{
		WayPoint wayPoint = wayPoints.get(index);

		return new Rect(
			wayPoint.x*GameView.xDensityFactor,
			wayPoint.y*GameView.yDensityFactor,
			wayPoint.x*GameView.xDensityFactor + GameView.xDensityFactor,
			wayPoint.y*GameView.yDensityFactor + GameView.yDensityFactor);
	}

	/**
	 * Moves enemy down.
	 */
	public void moveDown()
	{
		if (direction == Direction.DOWN)
			return;

		direction = Direction.DOWN;
		vx = 0;
		vy = moveSpeed;
	}

	/**
	 * Moves enemy up.
	 */
	public void moveUp()
	{
		if (direction == Direction.UP)
			return;

		direction = Direction.UP;
		vx = 0;
		vy = moveSpeed * -1.0;
	}

	/**
	 * Moves enemy to the left.
	 */
	public void moveLeft()
	{
		if (direction == Direction.LEFT)
			return;

		direction = Direction.LEFT;
		vx = moveSpeed * -1.0;
		vy = 0;
	}

	/**
	 * Moves enemy to the right.
	 */
	public void moveRight()
	{
		if (direction == Direction.RIGHT)
			return;

		direction = Direction.RIGHT;
		vx = moveSpeed;
		vy = 0;
	}
}
