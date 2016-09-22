#ifndef __EXAMPLE_RECTANGLE_H_2015_11_08__
#define __EXAMPLE_RECTANGLE_H_2015_11_08__

#include "GameObject.h"

namespace BoredRobots
{
	/**
	 * Christian Schrödel - 11.08.2015
	 *
	 * Example rectangle implementation of a game object.
	 */
	class ExampleRectangle : public GameObject
	{
	public:
		ExampleRectangle(float x, float y);

		virtual void render(sf::RenderWindow &window) const;
		virtual void update(float dt);

		void moveTo(sf::Vector2f target);
	};
}

#endif // __EXAMPLE_RECTANGLE_H_2015_11_08__
