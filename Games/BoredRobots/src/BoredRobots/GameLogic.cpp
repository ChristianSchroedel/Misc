#include "GameLogic.h"


/**
 * @brief BoredRobots::GameLogic::GameLogic,
 * default constructor of BoredRobots, currently does nothing
 */
BoredRobots::GameLogic::GameLogic()
{
}

BoredRobots::GameLogic::~GameLogic()
{
	for(std::list<GameObject*>::iterator it = gameObjects.begin(); it != gameObjects.end(); it++)
	{
		delete (*it);
	}
}


/**
 * @brief BoredRobots::GameLogic::update
 *  this method do update all game objects and also internal logic of the game
 *
 * @param float dt, elapsed game time between two update calls
 */
void BoredRobots::GameLogic::update(float dt)
{
	for(std::list<GameObject*>::iterator it = gameObjects.begin(); it != gameObjects.end(); it++)
	{
		(*it)->update(dt);
	}
}

/**
 * @brief BoredRobots::GameLogic::addGameObject
 *  this method takes a pointer to a gameObject, the memory is deallocated by the game logic
 *  class
 * @param GameObject*  gameObject, pointer to an gameObject
 */
void BoredRobots::GameLogic::addGameObject(GameObject* gameObject)
{
	gameObjects.push_back(gameObject);
}

