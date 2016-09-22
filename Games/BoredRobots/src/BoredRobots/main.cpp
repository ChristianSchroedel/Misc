#include <SFML/Graphics.hpp>

#include "GameObject.h"
#include "Game.h"

#include "ExampleRectangle.h"
int main()
{
	sf::RenderWindow window(sf::VideoMode(300, 300), "BoredRobots-Example");
	BoredRobots::Game myGame(window);
	myGame.start();

	return 0;
}
