#include <iostream>

#include "ExampleRectangle.h"
#include "Game.h"

/**
 * @brief BoredRobots::Game::start
 *  this methods starts the complete game loop, is blocking
 */
void BoredRobots::Game::start()
{
	this->rectangle = new ExampleRectangle(10.f, 10.f);

	gameLogic = new BoredRobots::GameLogic();
	gameLogic->addGameObject(rectangle);

	gameView = new BoredRobots::GameView(gameLogic);

	// initialize timing
	clock.restart();

	while(stopGame == false)
	{
		run();
	}

	delete gameLogic;
	delete gameView;
}

/**
 * @brief BoredRobots::Game::end
 *  ends game loop end exit
 */
void BoredRobots::Game::end()
{
	stopGame = true;
}

/**
 * @brief BoredRobots::Game::run
 *  runs the game loop, is blocking
 */
void BoredRobots::Game::run()
{
	while (window.isOpen())
	{
		// check if user wants to exit
		sf::Event event;
		while (window.pollEvent(event))
		{
			if (event.type == sf::Event::Closed)
			{
				end();
				window.close();
				return;
			}
		}

		// calculate and update time
		float dt = clock.getElapsedTime().asSeconds();
		clock.restart();

		gameLogic->update(dt);
		gameView->render(window);
	}
}
