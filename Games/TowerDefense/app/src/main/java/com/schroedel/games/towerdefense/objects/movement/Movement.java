package com.schroedel.games.towerdefense.objects.movement;

import com.schroedel.games.towerdefense.objects.entities.Entity;

/**
 * Created by Christian Schrödel on 25.10.15.
 *
 * Movement interface.
 */
public interface Movement
{
	/**
	 * Starts moving an entity.
	 *
	 * @param entity - entity
	 */
	void start(Entity entity);

	/**
	 * Moves an entity.
	 *
	 * @param entity - entity
	 * @param dt - elapsed time since last movement
	 */
	void move(Entity entity, float dt);

	/**
	 * Sets movement speed.
	 *
	 * @param speed - movement speed
	 */
	void setSpeed(double speed);

	/**
	 * Stops movement.
	 */
	void stop();
}
