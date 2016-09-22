#ifndef __GAME_VIEW_H_2015_11_08__
#define __GAME_VIEW_H_2015_11_08__

#include "GameLogic.h"

namespace BoredRobots
{

	/**
	 * @brief The GameView class
	 *
	 * according to the model view controller design pattern, this class
	 * implements the view part... some further meaningful comments
	 **/
	class GameView
	{
	private:

		const GameLogic *gameLogic;

	public:
		GameView(GameLogic* gameLogic) : gameLogic(gameLogic){}
		void render(sf::RenderWindow& window)const;

	};
}

#endif
