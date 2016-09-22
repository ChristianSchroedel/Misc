#ifndef __MOVE_TO_WAY_POINT_H_2015_12_25__
#define __MOVE_TO_WAY_POINT_H_2015_12_25__

#include "Movement.h"
#include "GameObject.h"

namespace BoredRobots
{
	class MoveToWayPoint : public Movement
	{
	public:
		MoveToWayPoint(BoredRobots::GameObject *object, sf::Vector2f);

		virtual void start();
		virtual void stop();
		virtual void move(float dt);
		virtual void setSpeed(float speed);
		virtual void setTarget(sf::Vector2f targetPoint);

	private:
		BoredRobots::GameObject *object;
		sf::Vector2f targetPoint;

		bool isMoving;
		float speed;
	};
}

#endif // __MOVE_TO_WAY_POINT_H_2015_12_25__
