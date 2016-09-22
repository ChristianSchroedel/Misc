
#ifndef __GAME_H_2015_11_08__
#define __GAME_H_2015_11_08__

#include <chrono>
#include <SFML/Graphics.hpp>

#include "GameLogic.h"
#include "GameView.h"

class ExampleRectangle;

namespace BoredRobots
{
	/**
	 * @brief The Game class
	 * runs the actual game loop
	 */
	class Game
	{
	private:
		bool stopGame;
		sf::RenderWindow& window;
		sf::Clock clock;

		GameLogic* gameLogic;
		GameView* gameView;

		ExampleRectangle *rectangle;

		void run();

	public:
		Game(sf::RenderWindow& window) : stopGame(false), window(window)
		{
		}

		void start();
		void end();
	}; // Game

} // BoredRobots

#endif
