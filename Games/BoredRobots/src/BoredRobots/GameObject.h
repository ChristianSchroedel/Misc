#ifndef __GAME_OBJECT_H_2015_11_08__
#define __GAME_OBJECT_H_2015_11_08__

#include <SFML/System/Vector2.hpp>
#include <SFML/Graphics/RectangleShape.hpp>
#include <SFML/Graphics/RenderWindow.hpp>

#include "Movement.h"

namespace BoredRobots
{
	/**
	 * Christian Schrödel - 11.08.2015
	 *
	 * Game object class.
	 */
	class GameObject
	{
	public:
		GameObject(float x, float y)
		{
			this->vector = sf::Vector2f(x, y);
			this->shape = NULL;
			this->movement = NULL;
		}

		virtual ~GameObject()
		{
			delete shape;
			delete movement;
		}

		virtual void render(sf::RenderWindow &window) const = 0;
		virtual void update(float dt) = 0;

		sf::Vector2f *getVector()
		{
			return &vector;
		}

	protected:
		sf::Vector2f vector;
		sf::Shape *shape;
		BoredRobots::Movement *movement;
	};
}

#endif // __GAME_OBJECT_H_2015_11_08__
