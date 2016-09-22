
#include "GameView.h"

void BoredRobots::GameView::render(sf::RenderWindow& window)const
{
	const std::list<GameObject*>* gameObjects = gameLogic->getGameObjects();


	window.clear();

	for(std::list<GameObject*>::const_iterator it = gameObjects->cbegin();
		 it != gameObjects->cend(); it++)
	{
		(*it)->render(window);
	}

	window.display();

}
