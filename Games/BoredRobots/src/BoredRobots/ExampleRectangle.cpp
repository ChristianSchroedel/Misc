#include "ExampleRectangle.h"

#include <SFML/Graphics/RectangleShape.hpp>
#include <SFML/Graphics/Color.hpp>

#include "MoveToWayPoint.h"

#define WIDTH 30.0f
#define HEIGHT 30.0f

/**
 * Creates a new example rectangle.
 *
 * @param x - x position
 * @param y - y position
 */
BoredRobots::ExampleRectangle::ExampleRectangle(float x, float y) :
	GameObject(x, y)
{
	this->shape = new sf::RectangleShape(sf::Vector2f(WIDTH, HEIGHT));
	this->movement = new BoredRobots::MoveToWayPoint(this, sf::Vector2f(100.f, 200.f));

	shape->setPosition(vector);
	shape->setFillColor(sf::Color::Red);

	movement->setSpeed(30.f);
	movement->start();
}

/**
 * Renders game object.
 *
 * @param window - window to render on
 */
void BoredRobots::ExampleRectangle::render(sf::RenderWindow &window) const
{
	window.draw(*shape);
}

/**
 * Updates game object.
 *
 * @param dt - elapsed time since last update
 */
void BoredRobots::ExampleRectangle::update(float dt)
{
	if(movement)
		movement->move(dt);

	shape->setPosition(vector);
}

/**
 * Moves rectangle to new position.
 *
 * @param target - target point to move to
 */
void BoredRobots::ExampleRectangle::moveTo(sf::Vector2f target)
{
	movement->setTarget(target);
	movement->start();
}
