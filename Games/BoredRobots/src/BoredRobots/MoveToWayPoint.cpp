#include "MoveToWayPoint.h"

#include <cmath>
#include <iostream>

/**
 * Creates new move to way point movement.
 *
 * @param targetPoint - target point to walk to
 */
BoredRobots::MoveToWayPoint::MoveToWayPoint
(BoredRobots::GameObject *object, sf::Vector2f targetPoint)
{
	speed = 1.0f;
	this->object = object;
	this->targetPoint = targetPoint;
	isMoving = false;
}

/**
 * Starts moving to way point.
 */
void BoredRobots::MoveToWayPoint::start()
{
	this->isMoving = true;
}

/**
 * Stops moving to way point.
 */
void BoredRobots::MoveToWayPoint::stop()
{
	this->isMoving = false;
}

/**
 * Performs movement.
 *
 * @param dt - elapsed time since last movement
 */
void BoredRobots::MoveToWayPoint::move(float dt)
{
	if (dt <= 0 || !isMoving)
		return;

	/*
	sf::Vector2f *vector = object->getVector();

	float dx = targetPoint.x - vector->x;
	float dy = targetPoint.y - vector->y;


	// Stop if we already reached our target position
	if (dx == 0 &&
		 dy == 0)
	{
		stop();
		return;
	}

	double alpha = atan(fabs(dx)/fabs(dy));

	double vy = cos(alpha) * speed;
	double vx = sin(alpha) * speed;

	if (dx < 0)
		vx *= -1;

	if (dy < 0)
		vy *= -1;

	vector->x += vx * dt;
	vector->y += vy * dt;
	*/

	// calculcate direction and normalize vector
	sf::Vector2f* objectPosition = object->getVector();
	sf::Vector2f direction =  targetPoint - *objectPosition;
	float directionLength = std::sqrt(direction.x * direction.x + direction.y * direction.y);

	// if object is close enough stop moving, because of floating point errors
	const float SMALLEST_DISTANCE =  0.0001f;
	if(directionLength < SMALLEST_DISTANCE)
	{
		stop();
		return;
	}

	// in the other case move object with set speed
	direction.x = direction.x / directionLength; // normalize vector
	direction.y = direction.y / directionLength;

	// calculate new position
	objectPosition->x = objectPosition->x + direction.x * speed * dt;
	objectPosition->y = objectPosition->y + direction.y * speed * dt;
}

/**
 * Sets movement speed.
 *
 * @param speed - movement speed
 */
void BoredRobots::MoveToWayPoint::setSpeed(float speed)
{
	this->speed = speed;
}

/**
 * Sets target to move to.
 *
 * @param targetPoint - target point
 */
void BoredRobots::MoveToWayPoint::setTarget(sf::Vector2f targetPoint)
{
	this->targetPoint = targetPoint;
}
