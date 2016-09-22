#ifndef __MOVEMENT_H_2015_11_08__
#define __MOVEMENT_H_2015_11_08__

#include <SFML/System/Vector2.hpp>

namespace BoredRobots
{
	class Movement
	{
	public:
		virtual void start() = 0;
		virtual void stop() = 0;
		virtual void move(float dt) = 0;
		virtual void setSpeed(float speed) = 0;
		virtual void setTarget(sf::Vector2f targetPoint) = 0;
		virtual ~Movement(){}
	};
}

#endif // __MOVEMENT_H_2015_11_08__

